package bangwool.server.dto.response;

import lombok.AllArgsConstructor;

public class AnnouncementResponse {
    private String title;
    private String description;
    private String createDate;
    private String updateDate;

    public AnnouncementResponse(String title, String description, String createDate, String updateDate) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
