package tr.com.nihatalim.yt.presenter.dto.request;

import lombok.Getter;
import lombok.Setter;
import tr.com.nihatalim.yt.core.enums.ContentType;
import tr.com.nihatalim.yt.core.enums.DownloadType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StartDownloadRequest {
    @NotNull
    private String userId;

    @NotNull
    private String youtubeUrl;

    private DownloadType downloadType;
    private ContentType contentType;
    private String extension;

    public StartDownloadRequest() {
        this.contentType = ContentType.AUDIO;
        this.extension = "mp3";
        this.downloadType = DownloadType.SINGLE;
    }
}
