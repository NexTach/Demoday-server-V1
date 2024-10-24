package class4.demoday.domain.individualmarker.dto.response

data class GetIndividualMarkerResponse(
    val id: Long?,
    val name: String,
    val x: Double,
    val y: Double,
    val ownerName: String?,
    val ownerEmail: String,
)