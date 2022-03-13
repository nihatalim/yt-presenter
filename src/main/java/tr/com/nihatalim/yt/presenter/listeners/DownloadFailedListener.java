package tr.com.nihatalim.yt.presenter.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.core.dto.DownloadProgressDto;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;
import tr.com.nihatalim.yt.core.enums.TopicEnum;
import tr.com.nihatalim.yt.presenter.repository.ContentRepository;
import tr.com.nihatalim.yt.presenter.repository.DownloadProgressRepository;
import tr.com.nihatalim.yt.presenter.service.DistributionService;
import tr.com.nihatalim.yt.presenter.util.YoutubeUrlUtil;

import javax.transaction.Transactional;

@Slf4j
@Service
public class DownloadFailedListener {

    private final DistributionService distributionService;
    private final DownloadProgressRepository downloadProgressRepository;
    private final ContentRepository contentRepository;

    public DownloadFailedListener(DistributionService distributionService, DownloadProgressRepository downloadProgressRepository, ContentRepository contentRepository) {
        this.distributionService = distributionService;
        this.downloadProgressRepository = downloadProgressRepository;
        this.contentRepository = contentRepository;
    }

    @KafkaListener(topics = {"DOWNLOAD_FAILED_EVENT"})
    @Transactional
    public void downloadFailed(ConsumerRecord<Long, DownloadProgressDto> record) {
        final DownloadProgressDto downloadProgressDto = record.value();

        log.info("[DOWNLOAD_FAILED_EVENT] Download failed for youtubeUrl: {} operated by user: {} requested for contentType: {} and extension: {}", downloadProgressDto.getYoutubeUrl(), downloadProgressDto.getUserId(), downloadProgressDto.getContentType().getValue(), downloadProgressDto.getExtension());

        downloadProgressRepository.findById(downloadProgressDto.getDownloadProgressId())
            .ifPresent(downloadProgress -> {
                downloadProgress.setContentName(downloadProgressDto.getContentName());
                downloadProgress.setProgressStatus(ProgressStatus.DOWNLOAD_FAILED);
                downloadProgressRepository.save(downloadProgress);
            });

        contentRepository.findContentByYoutubeId(YoutubeUrlUtil.getYoutubeIdFromYoutubeUrl(downloadProgressDto.getYoutubeUrl()))
            .ifPresent(content -> {
                content.setContentName(downloadProgressDto.getContentName());
                contentRepository.save(content);
                log.info("Content name has been changed as: {}", downloadProgressDto.getContentName());
            });

        downloadProgressDto.setProgressStatus(ProgressStatus.DOWNLOAD_FAILED);
        distributionService.send(TopicEnum.DOWNLOAD_PROGRESS_COMPLETED_EVENT, downloadProgressDto);
    }
}
