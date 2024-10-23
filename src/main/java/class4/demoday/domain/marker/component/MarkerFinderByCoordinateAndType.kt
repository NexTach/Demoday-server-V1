package class4.demoday.domain.marker.component

import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.entity.MarkerTypes
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component

@Component
class MarkerFinderByCoordinateAndType(private val markerRepository: MarkerRepository) {
    private val X_MIN = 124.0
    private val X_MAX = 132.0
    private val Y_MIN = 33.0
    private val Y_MAX = 43.0
    private fun isValidCoordinate(xCoordinate: Double, yCoordinate: Double): Boolean {
        return (xCoordinate in X_MIN..X_MAX && yCoordinate in Y_MIN..Y_MAX)
    }

    fun findMarkersByCoordinateAndType(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double,
        type: MarkerTypes
    ): List<Marker> {
        val markers = markerRepository.findByMarkerTypeAndXBetweenAndYBetween(type, xMin, xMax, yMin, yMax)
            ?: throw NoMarkersFoundException("No markers found with type $type and coordinates between ($xMin, $yMin) and ($xMax, $yMax)")
        if (markers.isEmpty()) {
            throw NoMarkersFoundException("No markers found with type $type and coordinates between ($xMin, $yMin) and ($xMax, $yMax)")
        }
        return markers
    }
}