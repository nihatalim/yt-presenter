package tr.com.nihatalim.yt.presenter.entity;

import lombok.Getter;
import lombok.Setter;
import tr.com.nihatalim.yt.presenter.enums.ContentType;

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
public class ContentDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENT_DETAIL_ID")
    private Long contentDetailId;

    @Column(name = "CONTENT_ID", nullable = false)
    private Long contentId;

    @Column(name = "EXTENSION")
    private String extension;

    @Column(name = "STORAGE_URL")
    private String storageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;
}
