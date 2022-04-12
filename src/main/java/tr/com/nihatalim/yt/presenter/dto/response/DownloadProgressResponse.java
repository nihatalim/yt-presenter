package tr.com.nihatalim.yt.presenter.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.nihatalim.yt.core.enums.ContentType;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;

import java.util.Date;

@Getter
@Setter
public class DownloadProgressResponse {
    private Long downloadProgressId;
    private String userId;
    private Long contentId;
    private Long contentDetailId;
    private String contentName;
    private ContentType contentType;
    private String extension;
    private String youtubeUrl;
    private String storageUrl;
    private ProgressStatus progressStatus;
    private Date updatedDate;
    private Date createdDate;
}
