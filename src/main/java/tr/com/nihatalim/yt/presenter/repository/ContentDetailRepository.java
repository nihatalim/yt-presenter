package tr.com.nihatalim.yt.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.nihatalim.yt.presenter.entity.ContentDetail;
import tr.com.nihatalim.yt.core.enums.ContentType;

import java.util.Optional;

@Repository
public interface ContentDetailRepository extends JpaRepository<ContentDetail, Long> {
    Optional<ContentDetail> findContentDetailByContentIdAndContentTypeAndExtension(Long contentId, ContentType contentType, String extension);
}
