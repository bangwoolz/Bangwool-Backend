package bangwool.server;

import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.request.PpomodoroRequest;
import bangwool.server.dto.request.WorkRequest;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.dto.response.PpomodoroResponse;
import bangwool.server.dto.response.RankingResponses;
import bangwool.server.dto.response.WorkTodayResponse;
import bangwool.server.service.MemberService;
import bangwool.server.service.PpomodoroService;
import bangwool.server.service.RankingService;
import bangwool.server.service.WorkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ServerApplicationTests {

//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private WorkService workService;
//
//    @Autowired
//    private PpomodoroService ppomodoroService;
//
//    @Autowired
//    private RankingService rankingService;
//    @Test
//    void RankingTest() {
//
//        //given
//        MemberSignUpResponse memberSignUpResponse = memberService.save(new MemberSignUpRequest("abc@abc.com", "abc", "abc", "Asdf123!@#"));
//        MemberSignUpResponse memberSignUpResponse2 = memberService.save(new MemberSignUpRequest("abc2@abc.com", "abc2", "abc2", "Asdf123!@#"));
//        MemberSignUpResponse memberSignUpResponse3 = memberService.save(new MemberSignUpRequest("abc3@abc.com", "abc3", "abc3", "Asdf123!@#"));
//        PpomodoroResponse ppomodoroResponse = ppomodoroService.save(memberSignUpResponse.getId(), new PpomodoroRequest("abc", "red", 0, 15, 1));
//        PpomodoroResponse ppomodoroResponse2 = ppomodoroService.save(memberSignUpResponse2.getId(), new PpomodoroRequest("abc2", "red", 0, 15, 1));
//        PpomodoroResponse ppomodoroResponse3 = ppomodoroService.save(memberSignUpResponse3.getId(), new PpomodoroRequest("abc2", "red", 0, 15, 1));
//
//        //when
//        workService.save(memberSignUpResponse.getId(), ppomodoroResponse.getId(), new WorkRequest(0, 15));
//        workService.save(memberSignUpResponse.getId(), ppomodoroResponse.getId(), new WorkRequest(0, 15));
//        workService.save(memberSignUpResponse.getId(), ppomodoroResponse.getId(), new WorkRequest(0, 15));
//        workService.save(memberSignUpResponse3.getId(), ppomodoroResponse3.getId(), new WorkRequest(0, 15));
//
//        //then
//        rankingService.updateRanking();
//        System.out.println("asdfasdf");
//        rankingService.findAll().forEach(it ->
//                System.out.println("id : " + it.getId() + " member id : " + it.getMember().getId() +" day worked minute : " + it.getDayWorkedMinute() + " week worked minute : " + it.getWeekWorkedMinute())
//        );
//        RankingResponses rankingResponses = rankingService.getDayRanking();
//        rankingResponses.getRankingResponses().forEach(it ->
//                System.out.println(" nickname : " + it.getNickname() + " worked minute : " + it.getWorkedMinute())
//        );
//    }

}
