package class4.demoday.domain.auth.service.Impl;

import class4.demoday.domain.auth.component.EffectivenessCheck;
import class4.demoday.domain.auth.component.MemberSave;
import class4.demoday.domain.auth.dto.request.SignRequest;
import class4.demoday.domain.auth.dto.response.SignUpResponse;
import class4.demoday.domain.auth.service.SignUpService;
import class4.demoday.global.component.PhoneNumberFormatter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final EffectivenessCheck effectivenessCheck;
    private final MemberSave memberSave;
    private final PhoneNumberFormatter phoneNumberFormatter;

    @NotNull
    @Override
    public SignUpResponse signUp(@NotNull SignRequest signRequest) {
        String formattedPhoneNumber = signRequest.getPhoneNumber();
        if (!phoneNumberFormatter.formatCheck(formattedPhoneNumber)) {
            formattedPhoneNumber = phoneNumberFormatter.e164Format(formattedPhoneNumber);
        }
        effectivenessCheck.checkMemberEffective(formattedPhoneNumber);
        return memberSave.saveMember(new SignRequest(
                formattedPhoneNumber,
                signRequest.getPassword()
        ));
    }
}