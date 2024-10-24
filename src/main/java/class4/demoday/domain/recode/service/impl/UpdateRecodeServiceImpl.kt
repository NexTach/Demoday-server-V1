package class4.demoday.domain.recode.service.impl

import class4.demoday.domain.recode.component.RecodePatch
import class4.demoday.domain.recode.entity.Recode
import class4.demoday.domain.recode.service.UpdateRecodeService
import class4.demoday.global.member.service.GetMemberinfoService
import class4.demoday.global.security.cipher.EncryptionUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class UpdateRecodeServiceImpl(
    private val getMemberinfoService: GetMemberinfoService,
    private val recodePatch: RecodePatch
): UpdateRecodeService {
    override fun updateRecode(request: HttpServletRequest, time: Int?, distance: Int?): MutableList<Recode> {
        val recodeList = recodePatch.patchRecode(getMemberinfoService.getMemberByToken(request), time, distance)
        recodeList[0].userId.email = EncryptionUtils.decrypt(recodeList[0].userId.email)
        return recodeList
    }
}