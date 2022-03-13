package tr.com.nihatalim.yt.presenter.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.core.dto.DownloadProgressDto;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;
import tr.com.nihatalim.yt.core.enums.TopicEnum;
import tr.com.nihatalim.yt.presenter.repository.DownloadProgressRepository;
import tr.com.nihatalim.yt.presenter.service.DistributionService;

import javax.transaction.Transactional;

@Slf4j
@Service
public class DownloadProgressCompletedListener {

    private final DistributionService distributionService;
    private final DownloadProgressRepository downloadProgressRepository;

    public DownloadProgressCompletedListener(DistributionService distributionService, DownloadProgressRepository downloadProgressRepository) {
        this.distributionService = distributionService;
        this.downloadProgressRepository = downloadProgressRepository;
    }

    @KafkaListener(topics = {"DOWNLOAD_PROGRESS_COMPLETED_EVENT"})
    @Transactional
    public void completed(ConsumerRecord<Long, DownloadProgressDto> record) {
        final DownloadProgressDto downloadProgressDto = record.value();

        log.info("[DOWNLOAD_PROGRESS_COMPLETED_EVENT] Download completed for youtubeUrl: {} operated by user: {} requested for contentType: {} and extension: {}", downloadProgressDto.getYoutubeUrl(), downloadProgressDto.getUserId(), downloadProgressDto.getContentType().getValue(), downloadProgressDto.getExtension());
    }
}
