package bangwool.server.controller;


import bangwool.server.api.ApiResponse;
import bangwool.server.api.ApiStatus;
import bangwool.server.domain.Member;
import bangwool.server.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
@RestController
@Slf4j
public class SignUpController {


    private final HttpHeaders headers;
    @Autowired
    private MemberService memberService;

    public SignUpController() {
        final String MEDIA_TYPE_APPLICATION = "application";
        final String MEDIA_TYPE_JSON = "json";

        headers = new HttpHeaders();
        headers.setContentType(new MediaType(MEDIA_TYPE_APPLICATION, MEDIA_TYPE_JSON, StandardCharsets.UTF_8));
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody Member member) {

        final String SIGN_UP_SUCCESS = "signup ok";
        ApiResponse apiResponse = memberService.save(member);

        if(apiResponse != null) {
            return new ResponseEntity<>(apiResponse, headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ApiResponse.success(SIGN_UP_SUCCESS), headers, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> processValidationError(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ApiResponse apiResponse = ApiResponse.error(ApiStatus.REGEX_ERROR, errorMessage);
        return new ResponseEntity<>(apiResponse, headers, HttpStatus.BAD_REQUEST);
    }

}
