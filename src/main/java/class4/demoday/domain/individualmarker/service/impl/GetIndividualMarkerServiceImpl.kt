package class4.demoday.domain.individualmarker.service.impl

import class4.demoday.domain.individualmarker.component.GenerateIndividualMarkerDto
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import class4.demoday.domain.individualmarker.service.GetIndividualMarkerService
import class4.demoday.global.member.service.GetMemberinfoService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class GetIndividualMarkerServiceImpl(
    private val generateIndividualMarkerDto: GenerateIndividualMarkerDto,
    private val getMemberinfoService: GetMemberinfoService
): GetIndividualMarkerService {
    override fun getIndividualMarker(request: HttpServletRequest): List<GetIndividualMarkerResponse> {
        return generateIndividualMarkerDto.generateIndividualMarkerDto(getMemberinfoService.getMemberByToken(request))
    }
}