package bangwool.server.service;


import bangwool.server.domain.Member;
import bangwool.server.domain.Ranking;
import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.response.ExistResponse;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.dto.response.PasswordChangeResponse;
import bangwool.server.exception.badreqeust.DuplicateException;
import bangwool.server.dto.response.MypageResponse;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberSignUpResponse save(MemberSignUpRequest memberSignUpRequest) {
        String encodedPassword = passwordEncoder.encode(memberSignUpRequest.getPassword());

            Member member = Member.builder()
                .name(memberSignUpRequest.getName())
                .email(memberSignUpRequest.getEmail())
                .nickname(memberSignUpRequest.getNickname())
                .password(encodedPassword)
                .build();

            Ranking ranking = Ranking.builder()
                .dayWorkedMinute(0)
                .weekWorkedMinute(0)
                .member(member)
                .build();


            if(memberRepository.existsByEmailAndNickname(member.getEmail(),member.getNickname())) {
                throw new DuplicateException();
            }
            memberRepository.save(member);
            rankingRepository.save(ranking);

            return new MemberSignUpResponse(member.getId());

    }

    @Transactional(readOnly = true)
    public ExistResponse isExistMemberByEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            return new ExistResponse(true);
        }
        return new ExistResponse(false);
    }

    @Transactional(readOnly = true)
    public ExistResponse isExistMemberByNickname(String nickname) {
        if (memberRepository.findByNickname(nickname).isPresent()) {
            return new ExistResponse(true);
        }
        return new ExistResponse(false);
    }

    @Transactional(readOnly = true)
    public MypageResponse findMypage(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(NotFoundMemberException::new);
        return new MypageResponse(member.getEmail(), member.getName() ,member.getNickname(), member.getProfile());
    }

    public PasswordChangeResponse changePassword(Long id, String password){
        String encodedPassword = passwordEncoder.encode(password);
        memberRepository.updatePasswordById(id, encodedPassword);
        return new PasswordChangeResponse(true);
    }

}
