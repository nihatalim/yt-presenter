package tr.com.nihatalim.yt.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.nihatalim.yt.presenter.entity.DownloadProgress;
import tr.com.nihatalim.yt.core.enums.ProgressStatus;

@Repository
public interface DownloadProgressRepository extends JpaRepository<DownloadProgress, Long> {
    @Modifying
    @Query("update DownloadProgress dp set dp.progressStatus =:status where dp.downloadProgressId=:progressId")
    void updateDownloadProgressStatusByProgressId(@Param("status") ProgressStatus progressStatus, @Param("progressId") Long progressId);

}
