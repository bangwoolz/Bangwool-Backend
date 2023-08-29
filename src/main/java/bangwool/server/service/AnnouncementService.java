package bangwool.server.service;

import bangwool.server.domain.Announcement;
import bangwool.server.dto.response.AnnouncementsResponse;
import bangwool.server.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<Announcement> getAnnouncements(){
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncement(Long id){
        return announcementRepository.findById(id).get();
    }


}
