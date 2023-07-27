package bangwool.server.controller;

import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

}
