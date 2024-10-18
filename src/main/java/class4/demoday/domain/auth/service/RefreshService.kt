package class4.demoday.domain.auth.service

import class4.demoday.domain.auth.dto.response.RefreshResponse

interface RefreshService {
    fun refresh(refreshToken: String): RefreshResponse
}