package tr.com.nihatalim.yt.presenter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Content extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENT_ID")
    private Long contentId;

    @Column(name = "CONTENT_NAME")
    private String contentName;

    @Column(name = "YOUTUBE_URL")
    private String youtubeUrl;

    @Column(name = "YOUTUBE_ID", unique = true)
    private String youtubeId;
}
