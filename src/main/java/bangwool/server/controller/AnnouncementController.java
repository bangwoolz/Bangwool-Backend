package bangwool.server.controller;

import bangwool.server.dto.response.AnnouncementsResponse;
import bangwool.server.security.auth.LoginUserId;
import bangwool.server.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Announcement", description = "공지사항")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/all")
    @Operation(summary = "공지사항 조회")
    public ResponseEntity<AnnouncementsResponse> getAnnouncements(@LoginUserId Long memberId) {
        AnnouncementsResponse response = new AnnouncementsResponse(announcementService.getAnnouncements(memberId));
        return ResponseEntity.ok(response);
    }

}
