package tr.com.nihatalim.yt.presenter.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tr.com.nihatalim.yt.presenter.entity.DownloadProgress;

import java.util.List;

@Repository
public interface DownloadProgressPagingRepository extends PagingAndSortingRepository<DownloadProgress, Long> {
    List<DownloadProgress> getDownloadProgressesByUserIdOrderByCreatedDateDesc(String userId, Pageable pageable);
}
