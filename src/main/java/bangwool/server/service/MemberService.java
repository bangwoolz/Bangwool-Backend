package bangwool.server.service;


import bangwool.server.api.ApiResponse;
import bangwool.server.api.ApiStatus;
import bangwool.server.domain.Member;
import bangwool.server.exception.RegexException;
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

    public ApiResponse save(Member member) {

        final String PARSER = "VALUES";
        final String ERROR_CODE_NULL = "23502";

        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException ex) {
            ApiResponse apiResponse;
            String errorMessage = ex.getMessage().split(PARSER)[0];
            if(errorMessage.contains(ERROR_CODE_NULL)) {
                apiResponse = ApiResponse.error(ApiStatus.NULL_ERROR, RegexException.NULL_EXCEPTION);
            } else if (errorMessage.contains(Member.EMAIL)) {
                apiResponse = ApiResponse.error(ApiStatus.DUPLICATE_EMAIL, RegexException.EMAIL_DUPLICATE_EXCEPTION);
            } else if (errorMessage.contains(Member.NICKNAME)) {
                apiResponse = ApiResponse.error(ApiStatus.DUPLICATE_NICKNAME, RegexException.NICKNAME_DUPLICATE_EXCEPTION);
            } else {
                apiResponse = ApiResponse.error(ApiStatus.SYSTEM_ERROR, ex.getMessage());
            }
            return apiResponse;
        }
        return null;
    }
}
