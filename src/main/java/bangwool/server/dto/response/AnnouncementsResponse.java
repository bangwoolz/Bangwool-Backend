package bangwool.server.dto.response;

import bangwool.server.domain.Announcement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
public class AnnouncementsResponse {
    private List<Announcement> announcementList;
}
