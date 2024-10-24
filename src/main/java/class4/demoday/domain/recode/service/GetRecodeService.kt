package class4.demoday.domain.recode.service

import class4.demoday.domain.recode.entity.Recode
import jakarta.servlet.http.HttpServletRequest

interface GetRecodeService {
    fun getRecode(request:HttpServletRequest): List<Recode>
}