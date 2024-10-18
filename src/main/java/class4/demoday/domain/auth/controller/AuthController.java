package class4.demoday.domain.auth.controller;

import class4.demoday.domain.auth.dto.request.SignInRequest;
import class4.demoday.domain.auth.dto.request.SignUpRequest;
import class4.demoday.domain.auth.dto.response.RefreshResponse;
import class4.demoday.domain.auth.dto.response.SignUpResponse;
import class4.demoday.domain.auth.service.RefreshService;
import class4.demoday.domain.auth.service.SignInService;
import class4.demoday.domain.auth.service.SignUpService;
import class4.demoday.domain.auth.service.SignoutService;
import class4.demoday.global.security.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Auth", description = "인증 관련 API")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final RefreshService refreshService;
    private final SignoutService signoutService;

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return signInService.signIn(signInRequest);
    }

    @Operation(summary = "토큰 갱신", description = "리프레시 토큰을 이용하여 토큰을 갱신합니다.")
    @PatchMapping("/refresh")
    public RefreshResponse refresh(@RequestHeader("Refreshtoken") String refreshToken) {
        return refreshService.refresh(refreshToken);
    }

    @Operation(summary = "로그아웃", description = "로그아웃을 진행합니다.")
    @DeleteMapping("/signout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signout(HttpServletRequest request) {
        signoutService.signout(request);
    }
}