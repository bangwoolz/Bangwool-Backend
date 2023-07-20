package bangwool.server.service;

import bangwool.server.dao.MemberDao;
import bangwool.server.dto.PostLoginRequest;
import bangwool.server.dto.PostLoginResponse;
import bangwool.server.util.JwtTokenProvider;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static bangwool.server.exception.RegexException.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private long memberId;

    public PostLoginResponse login(PostLoginRequest loginRequest){

        //1. 이메일 유효성 확인
        try {
            memberId = memberDao.getUserIdByEmail(loginRequest.getEmail());
        } catch(NoResultException e){
            throw new NoResultException (EMAIL_NOTFOUND_EXCEPTION);
        }

        //2. 비밀번호 일치 확인
        validatePassword(loginRequest.getPassword(), memberId);

        //3. jwt갱신 & loginResponse반환
        String jwt = jwtTokenProvider.createToken(loginRequest.getEmail(), memberId);
        PostLoginResponse loginResponse = new PostLoginResponse(memberId, jwt);
        return loginResponse;
    }

    private void validatePassword(String password, long memberId) {
        String encodedPassword = memberDao.getPasswordByUserId(memberId);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new NoResultException (PASSWORD_NOTFOUND_EXCEPTION);
        }
    }

}
