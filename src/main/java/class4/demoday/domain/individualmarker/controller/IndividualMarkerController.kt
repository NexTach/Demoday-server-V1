package class4.demoday.domain.individualmarker.controller

import class4.demoday.domain.individualmarker.dto.request.PatchIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.request.SetIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import class4.demoday.domain.individualmarker.service.DeleteIndividualMarkerService
import class4.demoday.domain.individualmarker.service.GetIndividualMarkerService
import class4.demoday.domain.individualmarker.service.PatchIndividualMarkerService
import class4.demoday.domain.individualmarker.service.SetIndividualMarkerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "IndividualMarker", description = "개인마커 API")
@RequestMapping("/api/v1/individual-marker")
class IndividualMarkerController(
    private val getIndividualMarkerService: GetIndividualMarkerService,
    private val setIndividualMarkerService: SetIndividualMarkerService,
    private val patchIndividualMarkerService: PatchIndividualMarkerService,
    private val deleteIndividualMarkerService: DeleteIndividualMarkerService
) {
    @Operation(summary = "개인마커 조회", description = "개인마커를 조회합니다")
    @GetMapping
    fun getIndividualMarker(request: HttpServletRequest): List<GetIndividualMarkerResponse> {
        return getIndividualMarkerService.getIndividualMarker(request)
    }

    @Operation(summary = "개인마커 등록", description = "개인마커를 등록합니다")
    @PostMapping
    fun postIndividualMarker(
        request: HttpServletRequest,
        @RequestBody marker: SetIndividualMarkerRequest
    ): GetIndividualMarkerResponse {
        return setIndividualMarkerService.setIndividualMarker(request, marker)
    }

    @Operation(summary = "개인마커 수정", description = "개인마커를 수정합니다")
    @PatchMapping("/{id}")
    fun patchIndividualMarker(
        @PathVariable id: Long,
        request: HttpServletRequest,
        @RequestBody marker: PatchIndividualMarkerRequest
    ): GetIndividualMarkerResponse {
        return patchIndividualMarkerService.patchIndividualMarker(id, request, marker)
    }

    @Operation(summary = "개인마커 삭제", description = "개인마커를 삭제합니다")
    @DeleteMapping("/{id}")
    fun deleteIndividualMarker(@PathVariable id: Long, request: HttpServletRequest) {
        deleteIndividualMarkerService.deleteIndividualMarker(id, request)
    }
}