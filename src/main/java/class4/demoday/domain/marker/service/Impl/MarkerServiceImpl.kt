package class4.demoday.domain.marker.service.Impl

import class4.demoday.domain.marker.component.*
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
    private val coordinateValidation: CoordinateValidation,
    private val markerFinderByAddress: MarkerFinderByAddress,
    private val markerFinderByTypeAndCoordinates: MarkerFinderByCoordinateAndType
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

    override fun getMapMarkersByAddress(address: String): List<Marker> {
        return markerFinderByAddress.findMarkersByAddress(address)
    }

    override fun getMapMarkersByTypeAndCoordinates(
        type: MarkerTypes,
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double
    ): List<Marker> {
        return markerFinderByTypeAndCoordinates.findMarkersByCoordinateAndType(xMin, xMax, yMin, yMax, type)
    }
}