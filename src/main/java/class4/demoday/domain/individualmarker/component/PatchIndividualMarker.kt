package class4.demoday.domain.individualmarker.component

import class4.demoday.domain.individualmarker.entity.IndividualMarker
import class4.demoday.domain.individualmarker.repository.IndividualMarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PatchIndividualMarker(private val individualMarkerRepository: IndividualMarkerRepository) {
    fun patchIndividualMarker(id: Long, name: String): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.name = name
        return individualMarkerRepository.save(marker)
    }

    fun patchIndividualMarker(id: Long, x: Double, y: Double): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.x = x
        marker.y = y
        return individualMarkerRepository.save(marker)
    }

    fun patchIndividualMarker(id: Long, name: String, x: Double, y: Double): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.name = name
        marker.x = x
        marker.y = y
        return individualMarkerRepository.save(marker)
    }

    fun patchIndividualMarker(id: Long, name: String, x: Double): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.name = name
        marker.x = x
        return individualMarkerRepository.save(marker)
    }

    fun patchIndividualMarker(id: Long, name: String, y: Float): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.name = name
        marker.y = y.toDouble()
        return individualMarkerRepository.save(marker)
    }

    fun patchIndividualMarker(id: Long, x: Double): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.x = x
        return individualMarkerRepository.save(marker)
    }

    fun patchIndividualMarker(id: Long, y: Float): IndividualMarker {
        val marker = individualMarkerRepository.findById(id)
            .orElseThrow { throw NoMarkersFoundException("No markers found with id $id") }
        marker.y = y.toDouble()
        return individualMarkerRepository.save(marker)
    }
}