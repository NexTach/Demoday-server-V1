package class4.demoday.domain.marker.component

import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component

@Component
class GetAllMarkers(private val markerRepository: MarkerRepository) {
    fun getAllMarkers(): List<Marker> =
        markerRepository.findAll().takeIf { it.isNotEmpty() }
            ?: throw NoMarkersFoundException("No markers found")
}