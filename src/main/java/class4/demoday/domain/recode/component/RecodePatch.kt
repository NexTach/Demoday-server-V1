package class4.demoday.domain.recode.component

import class4.demoday.domain.recode.entity.Recode
import class4.demoday.domain.recode.entity.RecodeType
import class4.demoday.domain.recode.repository.RecodeRepository
import class4.demoday.global.exception.RecodeListSizeException
import class4.demoday.global.member.entity.Member
import class4.demoday.global.security.cipher.EncryptionUtils
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class RecodePatch(private val recodeRepository: RecodeRepository) {
    fun patchRecode(member: Member, time: Int? = null, distance: Int? = null): MutableList<Recode> {
        val recodeList = recodeRepository.findByUserId(member)
        if (recodeList.size != 2) {
            throw RecodeListSizeException("Recode list size is not 2")
        }
        val firstRecode = recodeList[0]
        val secondRecode = recodeList[1]
        if (firstRecode.recodeType == RecodeType.TIME && time != null) {
            firstRecode.value = time
        } else if (firstRecode.recodeType == RecodeType.DISTANCE && distance != null) {
            firstRecode.value = distance
        }
        if (secondRecode.recodeType == RecodeType.TIME && time != null) {
            secondRecode.value = time
        } else if (secondRecode.recodeType == RecodeType.DISTANCE && distance != null) {
            secondRecode.value = distance
        }
        val saveMember = recodeRepository.saveAll(recodeList)
        return saveMember
    }
}