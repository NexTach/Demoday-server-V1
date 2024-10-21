package class4.demoday.domain.marker.component

import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.entity.MarkerTypes
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component

@Component
class MarkerFinderByType(private val markerRepository: MarkerRepository) {
    fun findMarkersByType(type: MarkerTypes): List<Marker> {
        return markerRepository.findByMarkerType(type).takeIf { it.isNotEmpty() }
            ?: throw NoMarkersFoundException("No markers found with type $type")
    }
}