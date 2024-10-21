package class4.demoday.domain.marker.component

import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.global.exception.NoMarkersFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoordinateValidation(
    private val markerRepository: MarkerRepository
) {
    private val X_MIN = 124.0
    private val X_MAX = 132.0
    private val Y_MIN = 33.0
    private val Y_MAX = 43.0
    private fun isValidCoordinate(xCoordinate: Double, yCoordinate: Double): Boolean {
        return (xCoordinate in X_MIN..X_MAX && yCoordinate in Y_MIN..Y_MAX)
    }

    @Transactional(readOnly = true)
    fun findMarkersByCoordinates(xMin: Double, xMax: Double, yMin: Double, yMax: Double): List<Marker> {
        if (!isValidCoordinate(xMin, yMin) || !isValidCoordinate(xMax, yMax)) {
            throw IllegalArgumentException("Invalid coordinates")
        }
        val markers = markerRepository.findAllByCoordinates(xMin, xMax, yMin, yMax)
        if (markers.isEmpty()) {
            throw NoMarkersFoundException("No markers found")
        }
        return markers
    }
}