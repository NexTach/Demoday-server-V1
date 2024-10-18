package class4.demoday.domain.auth.controller;

import class4.demoday.domain.auth.dto.request.SignInRequest;
import class4.demoday.domain.auth.dto.request.SignUpRequest;
import class4.demoday.domain.auth.dto.response.RefreshResponse;
import class4.demoday.domain.auth.dto.response.SignUpResponse;
import class4.demoday.domain.auth.service.RefreshService;
import class4.demoday.domain.auth.service.SignInService;
import class4.demoday.domain.auth.service.SignUpService;
import class4.demoday.global.security.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Auth", description = "인증 관련 API")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final RefreshService refreshService;

    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return signInService.signIn(signInRequest);
    }

    @PatchMapping("/refresh")
    public RefreshResponse refresh(@RequestHeader("Refreshtoken") String refreshToken) {
        return refreshService.refresh(refreshToken);
    }

    @DeleteMapping("/signout")

}