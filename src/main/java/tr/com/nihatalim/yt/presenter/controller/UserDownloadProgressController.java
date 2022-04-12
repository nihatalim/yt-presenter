package tr.com.nihatalim.yt.presenter.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.nihatalim.yt.presenter.dto.response.DownloadProgressResponse;
import tr.com.nihatalim.yt.presenter.service.DownloadProgressService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/progress")
public class UserDownloadProgressController {
    private final DownloadProgressService downloadProgressService;

    public UserDownloadProgressController(DownloadProgressService downloadProgressService) {
        this.downloadProgressService = downloadProgressService;
    }

    @GetMapping
    public List<DownloadProgressResponse> getProgress(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user = ((User) authentication.getPrincipal());

        return downloadProgressService.getDownloadProgressList(user.getUsername(), PageRequest.of(page, size));
    }
}
