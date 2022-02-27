package tr.com.nihatalim.yt.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.nihatalim.yt.presenter.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
