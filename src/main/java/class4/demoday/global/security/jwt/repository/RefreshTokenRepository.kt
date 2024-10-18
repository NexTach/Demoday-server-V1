package class4.demoday.global.security.jwt.repository

import class4.demoday.global.security.jwt.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
    fun findByUsername(username: String): RefreshToken?
}