package bangwool.server.controller;

import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.response.*;
import bangwool.server.security.auth.LoginUserId;
import bangwool.server.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Members", description = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping
    public ResponseEntity<MemberSignUpResponse> signUp(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        MemberSignUpResponse memberSignUpResponse = memberService.save(memberSignUpRequest);

        return ResponseEntity.ok(memberSignUpResponse);
    }

    @Operation(summary = "메일 중복 확인")
    @GetMapping("/exist/email")
    public ResponseEntity<ExistResponse> validMemberByEmail(@RequestParam String email) {
        return ResponseEntity.ok(memberService.isExistMemberByEmail(email));
    }

    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/exist/nickname")
    public ResponseEntity<ExistResponse> validMemberByNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(memberService.isExistMemberByNickname(nickname));
    }

    @Operation(summary = "마이페이지 조회")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/mypage")
    public ResponseEntity<MypageResponse> getMypage(@LoginUserId Long memberId){
        MypageResponse mypageResponse = memberService.findMypage(memberId);
        return ResponseEntity.ok(mypageResponse);
    }
    @Operation(summary = "비밀번호 변경")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/chagepassword")
    public ResponseEntity<PasswordChangeResponse> changePassword(@LoginUserId Long memberId, String password){
        PasswordChangeResponse passwordChangeResponse = memberService.changePassword(memberId, password);
        return ResponseEntity.ok(passwordChangeResponse);
    }

    @Operation(summary = "회원 탈퇴")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/signout")
    public ResponseEntity<SignoutResponse> signOut(@LoginUserId Long memberId){
        SignoutResponse signoutResponse = memberService.signOut(memberId);
        return ResponseEntity.ok(signoutResponse);
    }

}
