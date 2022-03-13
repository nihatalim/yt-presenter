package tr.com.nihatalim.yt.presenter.converter;

import org.springframework.stereotype.Component;
import tr.com.nihatalim.yt.core.dto.DownloadProgressDto;
import tr.com.nihatalim.yt.presenter.entity.DownloadProgress;

@Component
public class DownloadProgressConverter {
    public DownloadProgressDto convert(DownloadProgress downloadProgress) {
        final DownloadProgressDto downloadProgressDto = new DownloadProgressDto();

        downloadProgressDto.setDownloadProgressId(downloadProgress.getDownloadProgressId());
        downloadProgressDto.setUserId(downloadProgress.getUserId());
        downloadProgressDto.setContentId(downloadProgress.getContentId());
        downloadProgressDto.setContentName(downloadProgress.getContentName());
        downloadProgressDto.setContentType(downloadProgress.getContentType());
        downloadProgressDto.setExtension(downloadProgress.getExtension());
        downloadProgressDto.setYoutubeUrl(downloadProgress.getYoutubeUrl());
        downloadProgressDto.setStorageUrl(downloadProgress.getStorageUrl());
        downloadProgressDto.setProgressStatus(downloadProgress.getProgressStatus());

        return downloadProgressDto;
    }
}
