package bangwool.server.controller;

import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<MemberSignUpResponse> signUp(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        MemberSignUpResponse memberSignUpResponse = memberService.save(memberSignUpRequest);

        return ResponseEntity.ok(memberSignUpResponse);
    }

}
