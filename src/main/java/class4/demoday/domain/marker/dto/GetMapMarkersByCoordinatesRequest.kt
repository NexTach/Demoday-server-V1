package class4.demoday.domain.marker.dto

import jakarta.validation.constraints.NotNull

data class GetMapMarkersByCoordinatesRequest(
    @field:NotNull val xMin: Double,
    @field:NotNull val xMax: Double,
    @field:NotNull val yMin: Double,
    @field:NotNull val yMax: Double
)