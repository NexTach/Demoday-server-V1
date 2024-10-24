package class4.demoday.domain.recode.component

import class4.demoday.domain.recode.entity.Recode
import class4.demoday.domain.recode.repository.RecodeRepository
import class4.demoday.global.member.entity.Member
import class4.demoday.global.security.cipher.EncryptionUtils
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RecodeInquiry(private val recodeRepository: RecodeRepository) {
    @Transactional
    fun getRecodeByUser(user: Member): List<Recode> {
        val recodeList = recodeRepository.findByUserId(user)
        recodeList[0].userId.email = EncryptionUtils.decrypt(recodeList[0].userId.email)
        return recodeList
    }
}