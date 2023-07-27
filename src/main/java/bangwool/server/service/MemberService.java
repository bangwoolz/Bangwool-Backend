package bangwool.server.service;


import bangwool.server.domain.Member;
import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.exception.badreqeust.DuplicateEmailException;
import bangwool.server.exception.badreqeust.DuplicateNickNameException;
import bangwool.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse save(MemberSignUpRequest memberSignUpRequest) {
        final String PARSER = "VALUES";
        String encodedPassword = passwordEncoder.encode(memberSignUpRequest.getPassword());


        Member member = Member.builder()
                .name(memberSignUpRequest.getName())
                .email(memberSignUpRequest.getEmail())
                .nickname(memberSignUpRequest.getNickname())
                .password(encodedPassword)
                .build();

        try {
            memberRepository.save(member);
        } catch(DataIntegrityViolationException ex) {
            String errorMessage = ex.getMessage().split(PARSER)[0];
            if (errorMessage.contains("EMAIL")) {
                throw new DuplicateEmailException();
            }
            if (errorMessage.contains("NICKNAME")) {
                throw new DuplicateNickNameException();
            }
        }

        return new MemberSignUpResponse(member.getId());
    }
}
