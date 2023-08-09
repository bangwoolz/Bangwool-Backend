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

    @Autowired
    private MemberService memberService;

    @Autowired
    private WorkService workService;

    @Autowired
    private PpomodoroService ppomodoroService;

    @Autowired
    private RankingService rankingService;
    @Test
    void RankingTest() {

        //given
        MemberSignUpResponse memberSignUpResponse = memberService.save(new MemberSignUpRequest("abc@abc.com", "abc", "abc", "Asdf123!@#"));
        MemberSignUpResponse memberSignUpResponse2 = memberService.save(new MemberSignUpRequest("abc2@abc.com", "abc2", "abc2", "Asdf123!@#"));
        PpomodoroResponse ppomodoroResponse = ppomodoroService.save(memberSignUpResponse.getId(), new PpomodoroRequest("abc", "red", 0, 15, 1));
        PpomodoroResponse ppomodoroResponse2 = ppomodoroService.save(memberSignUpResponse.getId(), new PpomodoroRequest("abc2", "red", 0, 15, 1));

        //when
        workService.save(memberSignUpResponse.getId(), ppomodoroResponse.getId(), new WorkRequest(0, 15));
        workService.save(memberSignUpResponse.getId(), ppomodoroResponse.getId(), new WorkRequest(0, 15));
        workService.save(memberSignUpResponse.getId(), ppomodoroResponse.getId(), new WorkRequest(0, 15));
        workService.save(memberSignUpResponse2.getId(), ppomodoroResponse2.getId(), new WorkRequest(0, 15));

        //then
        rankingService.updateRanking();
        RankingResponses rankingResponses = rankingService.getDayRanking();
        Assertions.assertThat(rankingResponses.getRankingResponses().size()).isEqualTo(2);
    }

}
