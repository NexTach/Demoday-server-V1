package class4.demoday.domain.marker.service.Impl

import class4.demoday.domain.marker.component.CoordinateValidation
import class4.demoday.domain.marker.component.GetAllMarkers
import class4.demoday.domain.marker.component.MarkerFinderById
import class4.demoday.domain.marker.component.MarkerFinderByType
import class4.demoday.domain.marker.entity.Marker
import class4.demoday.domain.marker.entity.MarkerTypes
import class4.demoday.domain.marker.repository.MarkerRepository
import class4.demoday.domain.marker.service.MarkerService
import org.springframework.stereotype.Service

@Service
class MarkerServiceImpl(
    private val getAllMarkers: GetAllMarkers,
    private val markerFinderById: MarkerFinderById,
    private val markerFinderByType: MarkerFinderByType,
    private val coordinateValidation: CoordinateValidation
): MarkerService {
    override fun getAllMapMarkers(): List<Marker> {
        return getAllMarkers.getAllMarkers();
    }

    override fun getMapMarkerById(id: Long): Marker {
        return markerFinderById.findMarkerById(id)
    }

    override fun getMapMarkersByType(type: MarkerTypes): List<Marker> {
        return markerFinderByType.findMarkersByType(type)
    }

    override fun getMapMarkersByCoordinates(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double
    ): List<Marker> {
        return coordinateValidation.findMarkersByCoordinates(xMin, xMax, yMin, yMax)
    }
}