package tr.com.nihatalim.yt.presenter.converter;

import org.springframework.stereotype.Component;
import tr.com.nihatalim.yt.presenter.dto.response.DownloadProgressResponse;
import tr.com.nihatalim.yt.presenter.entity.DownloadProgress;

@Component
public class DownloadProgressResponseConverter {
    public DownloadProgressResponse convert(DownloadProgress downloadProgress) {
        final DownloadProgressResponse downloadProgressDto = new DownloadProgressResponse();
        downloadProgressDto.setDownloadProgressId(downloadProgress.getDownloadProgressId());
        downloadProgressDto.setUserId(downloadProgress.getUserId());
        downloadProgressDto.setContentId(downloadProgress.getContentId());
        downloadProgressDto.setContentName(downloadProgress.getContentName());
        downloadProgressDto.setContentType(downloadProgress.getContentType());
        downloadProgressDto.setExtension(downloadProgress.getExtension());
        downloadProgressDto.setYoutubeUrl(downloadProgress.getYoutubeUrl());
        downloadProgressDto.setStorageUrl(downloadProgress.getStorageUrl());
        downloadProgressDto.setProgressStatus(downloadProgress.getProgressStatus());
        downloadProgressDto.setCreatedDate(downloadProgress.getCreatedDate());
        downloadProgressDto.setUpdatedDate(downloadProgress.getUpdatedDate());
        return downloadProgressDto;
    }
}
