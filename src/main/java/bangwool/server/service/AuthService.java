package bangwool.server.service;

import bangwool.server.exception.notfound.NotFoundEmailException;
import bangwool.server.exception.notfound.NotFoundPasswordException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.dto.MemberLoginRequest;
import bangwool.server.dto.MemberLoginResponse;
import bangwool.server.util.JwtTokenProvider;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private long memberId;

    public MemberLoginResponse login(MemberLoginRequest loginRequest){

        //1. 이메일 유효성 확인
        try {
            memberId = memberRepository.getUserIdByEmail(loginRequest.getEmail());
        } catch(NoResultException e){
            throw new NotFoundEmailException();
        }

        //2. 비밀번호 일치 확인
        validatePassword(loginRequest.getPassword(), memberId);

        //3. jwt갱신 & loginResponse반환
        String jwt = jwtTokenProvider.createToken(loginRequest.getEmail(), memberId);
        MemberLoginResponse loginResponse = new MemberLoginResponse(memberId, jwt);
        return loginResponse;
    }

    private void validatePassword(String password, long memberId) {
        String encodedPassword = memberRepository.getPasswordByUserId(memberId);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new NotFoundPasswordException();
        }
    }

}
