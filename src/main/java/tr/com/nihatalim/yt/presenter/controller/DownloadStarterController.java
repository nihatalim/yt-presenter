package tr.com.nihatalim.yt.presenter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.nihatalim.yt.presenter.dto.request.StartDownloadRequest;
import tr.com.nihatalim.yt.presenter.service.DownloadStarterService;

@RestController
@RequestMapping(value = "/api/download")
public class DownloadStarterController {
    private final DownloadStarterService downloadStarterService;

    public DownloadStarterController(DownloadStarterService downloadStarterService) {
        this.downloadStarterService = downloadStarterService;
    }

    @PostMapping
    public void start(@RequestBody StartDownloadRequest request) {
        downloadStarterService.startDownload(request);
    }
}
