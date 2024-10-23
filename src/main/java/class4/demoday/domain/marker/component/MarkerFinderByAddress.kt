package class4.demoday.domain.marker.component

import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component

@Component
class MarkerFinderByAddress(private val markerRepository: MarkerRepository) {
    fun findMarkersByAddress(address: String): List<Marker> {
        val markers = markerRepository.findByAddress(address)
            ?: throw NoMarkersFoundException("No markers found with address: $address")
        if (markers.isEmpty()) {
            throw NoMarkersFoundException("No markers found with address: $address")
        }
        return markers
    }
}