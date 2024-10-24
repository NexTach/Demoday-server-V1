package class4.demoday.domain.recode.dto

import jakarta.validation.constraints.NotNull

data class UpdateRecodeRequest(
    val time: Int? = null,
    val distance: Int? = null
)