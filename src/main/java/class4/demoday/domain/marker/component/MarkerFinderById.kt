package class4.demoday.domain.marker.component

import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component

@Component
class MarkerFinderById(private val markerRepository: MarkerRepository) {
    fun findMarkerById(id: Long): Marker {
        return markerRepository.findById(id).orElseThrow { NoMarkersFoundException("Marker not found") }
    }
}