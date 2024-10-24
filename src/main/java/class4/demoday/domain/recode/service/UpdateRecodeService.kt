package class4.demoday.domain.recode.service

import class4.demoday.domain.recode.entity.Recode
import jakarta.servlet.http.HttpServletRequest

interface UpdateRecodeService {
    fun updateRecode(request: HttpServletRequest, time: Int?, distance: Int?): MutableList<Recode>
}