package bangwool.server.service;

import bangwool.server.domain.Member;
import bangwool.server.dto.request.AuthLoginRequest;
import bangwool.server.dto.response.TokenResponse;
import bangwool.server.exception.badreqeust.PasswordMismatchException;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public TokenResponse login(AuthLoginRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundMemberException::new);
        validatePassword(findMember, request.getPassword());

        String token = issueToken(findMember);

        return TokenResponse.from(token);
    }

    private void validatePassword(final Member findMember, final String password) {
        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            throw new PasswordMismatchException();
        }
    }

    private String issueToken(final Member findMember) {
        return jwtTokenProvider.createToken(findMember.getId());
    }

}
