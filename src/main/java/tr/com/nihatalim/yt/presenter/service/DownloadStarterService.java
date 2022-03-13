package tr.com.nihatalim.yt.presenter.service;

import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.core.enums.DownloadType;
import tr.com.nihatalim.yt.presenter.converter.DownloadProgressConverter;
import tr.com.nihatalim.yt.core.dto.DownloadProgressDto;
import tr.com.nihatalim.yt.presenter.dto.request.StartDownloadRequest;
import tr.com.nihatalim.yt.presenter.entity.Content;
import tr.com.nihatalim.yt.presenter.entity.ContentDetail;
import tr.com.nihatalim.yt.presenter.entity.DownloadProgress;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;
import tr.com.nihatalim.yt.core.enums.TopicEnum;
import tr.com.nihatalim.yt.presenter.repository.ContentDetailRepository;
import tr.com.nihatalim.yt.presenter.repository.ContentRepository;
import tr.com.nihatalim.yt.presenter.repository.DownloadProgressRepository;
import tr.com.nihatalim.yt.presenter.util.YoutubeUrlUtil;

import javax.transaction.Transactional;

@Service
public class DownloadStarterService {
    private final DistributionService distributionService;
    private final ContentRepository contentRepository;
    private final ContentDetailRepository contentDetailRepository;
    private final DownloadProgressRepository downloadProgressRepository;
    private final DownloadProgressConverter downloadProgressConverter;

    public DownloadStarterService(DistributionService distributionService, ContentRepository contentRepository, ContentDetailRepository contentDetailRepository, DownloadProgressRepository downloadProgressRepository, DownloadProgressConverter downloadProgressConverter) {
        this.distributionService = distributionService;
        this.contentRepository = contentRepository;
        this.contentDetailRepository = contentDetailRepository;
        this.downloadProgressRepository = downloadProgressRepository;
        this.downloadProgressConverter = downloadProgressConverter;
    }

    @Transactional
    public void startDownload(StartDownloadRequest request) {
        final String youtubeVideoId = YoutubeUrlUtil.getYoutubeIdFromYoutubeUrl(request.getYoutubeUrl());

        if (request.getDownloadType() == DownloadType.SINGLE) {
            request.setYoutubeUrl(getSingleYoutubeUrl(youtubeVideoId));
        }

        Content content = contentRepository.findContentByYoutubeId(youtubeVideoId).orElse(null);

        ContentDetail contentDetail = content != null ? contentDetailRepository
            .findContentDetailByContentIdAndContentTypeAndExtension(content.getContentId(), request.getContentType(), request.getExtension())
            .orElse(null) : null;

        DownloadProgress downloadProgress = null;
        DownloadProgressDto downloadProgressDto = null;

        final boolean contentAlreadyDownloaded = contentDetail != null;
        final boolean contentAnyDownloadedBefore = content == null;

        if (contentAlreadyDownloaded) {
            downloadProgress = createDownloadProgress(content, request, ProgressStatus.COMPLETED);

            downloadProgressDto = downloadProgressConverter.convert(downloadProgress);
            downloadProgressDto.setStorageExtension(request.getExtension());
        }

        if (contentAnyDownloadedBefore) {
            content = createContent(request);
        }

        downloadProgress = createDownloadProgress(content, request, ProgressStatus.DOWNLOADING);

        downloadProgressDto = downloadProgressConverter.convert(downloadProgress);

        sendDistribution(downloadProgressDto);
    }

    private void sendDistribution(DownloadProgressDto downloadProgressDto) {
        final TopicEnum topic = downloadProgressDto.getProgressStatus() == ProgressStatus.COMPLETED ?
            TopicEnum.DOWNLOAD_PROGRESS_COMPLETED_EVENT : TopicEnum.DOWNLOAD_EVENT;

        distributionService.send(topic, downloadProgressDto);
    }

    private Content createContent(StartDownloadRequest request) {
        Content content = new Content();
        content.setYoutubeId(YoutubeUrlUtil.getYoutubeIdFromYoutubeUrl(request.getYoutubeUrl()));
        content.setYoutubeUrl(request.getYoutubeUrl());
        return contentRepository.saveAndFlush(content);
    }

    private DownloadProgress createDownloadProgress(Content content, StartDownloadRequest request, ProgressStatus progressStatus) {
        final DownloadProgress downloadProgress = new DownloadProgress();

        downloadProgress.setContentId(content.getContentId());
        downloadProgress.setContentType(request.getContentType());
        downloadProgress.setYoutubeUrl(request.getYoutubeUrl());
        downloadProgress.setExtension(request.getExtension());
        downloadProgress.setUserId(request.getUserId());
        downloadProgress.setProgressStatus(progressStatus);

        return downloadProgressRepository.saveAndFlush(downloadProgress);
    }

    private String getSingleYoutubeUrl(String youtubeId) {
        return "https://www.youtube.com/watch?v=" + youtubeId;
    }
}
