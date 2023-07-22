package bangwool.server.service;


import bangwool.server.domain.Member;
import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.exception.badreqeust.DuplicateEmailException;
import bangwool.server.exception.badreqeust.DuplicateNickNameException;
import bangwool.server.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberSignUpResponse save(MemberSignUpRequest memberSignUpRequest) {
        final String PARSER = "VALUES";

        Member member = Member.builder()
                .name(memberSignUpRequest.getName())
                .email(memberSignUpRequest.getEmail())
                .nickname(memberSignUpRequest.getNickname())
                .password(memberSignUpRequest.getPassword())
                .build();

        try {
            memberRepository.save(member);
        } catch(DataIntegrityViolationException ex) {
            String errorMessage = ex.getMessage().split(PARSER)[0];
            if (errorMessage.contains(Member.EMAIL)) {
                throw new DuplicateEmailException();
            }
            if (errorMessage.contains(Member.NICKNAME)) {
                throw new DuplicateNickNameException();
            }
        }

        return new MemberSignUpResponse(member.getId());
    }
}
