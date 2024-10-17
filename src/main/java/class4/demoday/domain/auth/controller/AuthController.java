package class4.demoday.domain.auth.controller;

import class4.demoday.domain.auth.dto.request.SignUpRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Auth", description = "인증 관련 API")
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signup")
    public SignUpRequest signUp(SignUpRequest signUpRequest) {
        return signUpRequest;
    }
}