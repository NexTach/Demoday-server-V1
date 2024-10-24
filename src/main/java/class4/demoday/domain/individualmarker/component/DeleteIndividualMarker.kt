package class4.demoday.domain.individualmarker.component

import class4.demoday.domain.individualmarker.repository.IndividualMarkerRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class DeleteIndividualMarker(private val individualMarkerRepository: IndividualMarkerRepository) {
    fun deleteIndividualMarker(marker_id: Long) {
        individualMarkerRepository.deleteById(marker_id)
    }
}