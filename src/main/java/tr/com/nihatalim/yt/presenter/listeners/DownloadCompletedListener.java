package tr.com.nihatalim.yt.presenter.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.core.dto.DownloadProgressDto;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;
import tr.com.nihatalim.yt.core.enums.TopicEnum;
import tr.com.nihatalim.yt.presenter.entity.ContentDetail;
import tr.com.nihatalim.yt.presenter.repository.ContentDetailRepository;
import tr.com.nihatalim.yt.presenter.repository.ContentRepository;
import tr.com.nihatalim.yt.presenter.repository.DownloadProgressRepository;
import tr.com.nihatalim.yt.presenter.service.DistributionService;
import tr.com.nihatalim.yt.presenter.util.YoutubeUrlUtil;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class DownloadCompletedListener {
    private final DistributionService distributionService;
    private final ContentDetailRepository contentDetailRepository;
    private final DownloadProgressRepository downloadProgressRepository;
    private final ContentRepository contentRepository;

    public DownloadCompletedListener(DistributionService distributionService, ContentDetailRepository contentDetailRepository, DownloadProgressRepository downloadProgressRepository, ContentRepository contentRepository) {
        this.distributionService = distributionService;
        this.contentDetailRepository = contentDetailRepository;
        this.downloadProgressRepository = downloadProgressRepository;
        this.contentRepository = contentRepository;
    }

    @KafkaListener(topics = "${app.kafka.topics.download-completed-event}")
    @Transactional
    public void downloadCompleted(ConsumerRecord<Long, DownloadProgressDto> record) {
        final DownloadProgressDto downloadProgressDto = record.value();

        log.info("[DOWNLOAD_COMPLETED_EVENT] Download completed for youtubeUrl: {} operated by user: {} requested for contentType: {} and extension: {}", downloadProgressDto.getYoutubeUrl(), downloadProgressDto.getUserId(), downloadProgressDto.getContentType().getValue(), downloadProgressDto.getExtension());

        downloadProgressRepository.findById(downloadProgressDto.getDownloadProgressId())
            .ifPresent(downloadProgress -> {
                downloadProgress.setContentName(downloadProgressDto.getContentName());
                downloadProgress.setProgressStatus(ProgressStatus.COMPLETED);
                downloadProgress.setStorageUrl(downloadProgressDto.getStorageUrl());
                downloadProgressRepository.save(downloadProgress);
            });

        final ContentDetail contentDetail = createContentDetailIfNotPresent(downloadProgressDto);

        contentRepository.findContentByYoutubeId(YoutubeUrlUtil.getYoutubeIdFromYoutubeUrl(downloadProgressDto.getYoutubeUrl()))
            .ifPresent(content -> {
                content.setContentName(downloadProgressDto.getContentName());
                contentRepository.save(content);
                log.info("Content name has been changed as: {}", downloadProgressDto.getContentName());
            });

        downloadProgressDto.setContentDetailId(contentDetail.getContentDetailId());
        downloadProgressDto.setProgressStatus(ProgressStatus.COMPLETED);

        distributionService.send(TopicEnum.DOWNLOAD_PROGRESS_COMPLETED_EVENT, downloadProgressDto);
    }

    private ContentDetail createContentDetailIfNotPresent(DownloadProgressDto downloadProgressDto) {
        final Optional<ContentDetail> contentDetail = contentDetailRepository.findContentDetailByContentIdAndContentTypeAndExtension(downloadProgressDto.getContentId(), downloadProgressDto.getContentType(), downloadProgressDto.getExtension());

        if (contentDetail.isEmpty()) {
            final ContentDetail cd = new ContentDetail();
            cd.setContentId(downloadProgressDto.getContentId());
            cd.setContentType(downloadProgressDto.getContentType());
            cd.setExtension(downloadProgressDto.getStorageExtension());
            cd.setStorageUrl(downloadProgressDto.getStorageUrl());
            return contentDetailRepository.saveAndFlush(cd);
        }

        return contentDetail.get();
    }
}
