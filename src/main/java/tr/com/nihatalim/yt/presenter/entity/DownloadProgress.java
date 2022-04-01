package tr.com.nihatalim.yt.presenter.entity;

import lombok.Getter;
import lombok.Setter;
import tr.com.nihatalim.yt.core.enums.ContentType;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DownloadProgress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOWNLOAD_PROGRESS_ID")
    private Long downloadProgressId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CONTENT_ID")
    private Long contentId;

    @Column(name = "CONTENT_NAME")
    private String contentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;

    @Column(name = "YOUTUBE_URL")
    private String youtubeUrl;

    @Column(name = "EXTENSION")
    private String extension;

    @Column(name = "STORAGE_URL")
    private String storageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ProgressStatus progressStatus;
}
