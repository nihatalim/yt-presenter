package tr.com.nihatalim.yt.presenter.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.core.dto.DownloadProgressDto;
import tr.com.nihatalim.yt.presenter.converter.DownloadProgressConverter;
import tr.com.nihatalim.yt.presenter.entity.AppUser;
import tr.com.nihatalim.yt.presenter.entity.DownloadProgress;
import tr.com.nihatalim.yt.presenter.repository.DownloadProgressPagingRepository;
import tr.com.nihatalim.yt.presenter.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadProgressService {
    private final UserRepository userRepository;
    private final DownloadProgressPagingRepository downloadProgressRepository;
    private final DownloadProgressConverter downloadProgressConverter;

    public DownloadProgressService(UserRepository userRepository, DownloadProgressPagingRepository downloadProgressRepository, DownloadProgressConverter downloadProgressConverter) {
        this.userRepository = userRepository;
        this.downloadProgressRepository = downloadProgressRepository;
        this.downloadProgressConverter = downloadProgressConverter;
    }

    public List<DownloadProgressDto> getDownloadProgressList(String mail, Pageable pageable) {
        final AppUser appUser = userRepository.findByMail(mail).orElseThrow();

        final List<DownloadProgress> downloadProgresses = downloadProgressRepository.getDownloadProgressesByUserIdOrderByCreatedDateDesc(String.valueOf(appUser.getUserId()), pageable);

        return downloadProgresses.stream()
                .map(downloadProgressConverter::convert)
                .collect(Collectors.toList());
    }

}
