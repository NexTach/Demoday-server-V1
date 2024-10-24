package class4.demoday.domain.recode.controller

import class4.demoday.domain.recode.dto.UpdateRecodeRequest
import class4.demoday.domain.recode.entity.Recode
import class4.demoday.domain.recode.service.GetRecodeService
import class4.demoday.domain.recode.service.UpdateRecodeService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Recode", description = "사용자 주간기록 API")
@RestController
@RequestMapping("/api/v1/recode")
class RecodeController(
    private val getRecodeService: GetRecodeService,
    private val updateRecodeService: UpdateRecodeService
) {
    @GetMapping
    fun getRecode(request: HttpServletRequest): List<Recode> {
        return getRecodeService.getRecode(request)
    }

    @PatchMapping
    fun updateRecode(request: HttpServletRequest, @RequestBody updateDto: UpdateRecodeRequest): List<Recode> {
        return updateRecodeService.updateRecode(request, updateDto.time, updateDto.distance)
    }
}