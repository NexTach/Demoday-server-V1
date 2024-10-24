package class4.demoday.domain.recode.component

import class4.demoday.domain.recode.repository.RecodeRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class WeeklyInitializationRecode(
    private val recodeRepository: RecodeRepository
) {
    @Scheduled(cron = "0 0 0 * * MON")
    @Transactional
    fun resetRecodeValues() {
        val allRecodes = recodeRepository.findAll()
        for (recode in allRecodes) {
            recode.value = 0
        }
        recodeRepository.saveAll(allRecodes)
    }
}