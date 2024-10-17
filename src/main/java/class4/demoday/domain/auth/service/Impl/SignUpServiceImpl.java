package class4.demoday.domain.auth.service.Impl;

import class4.demoday.domain.auth.component.EffectivenessCheck;
import class4.demoday.domain.auth.dto.request.SignUpRequest;
import class4.demoday.domain.auth.dto.response.SignUpResponse;
import class4.demoday.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final EffectivenessCheck effectivenessCheck;

    @NotNull
    @Override
    public SignUpResponse signUp(@NotNull SignUpRequest signUpRequest) {
        effectivenessCheck.checkMemberEffective(signUpRequest.getPhoneNumber());
        return new SignUpResponse();
    }
}