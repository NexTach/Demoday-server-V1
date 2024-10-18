package class4.demoday.global.security.jwt.filter

import class4.demoday.global.exception.ExpiredTokenException
import class4.demoday.global.exception.InvalidTokenException
import class4.demoday.global.exception.InvalidTokenFormatException
import class4.demoday.global.exception.dto.enums.Status
import class4.demoday.global.exception.dto.response.ErrorResponse
import class4.demoday.global.security.jwt.service.JwtAuthenticationService
import class4.demoday.global.security.jwt.service.JwtTokenService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException


class JwtFilter(jwtTokenService: JwtTokenService, jwtAuthenticationService: JwtAuthenticationService) :
    OncePerRequestFilter() {
    private val jwtTokenService: JwtTokenService = jwtTokenService
    private val jwtAuthenticationService: JwtAuthenticationService = jwtAuthenticationService

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestURI = request.requestURI
        if (
            requestURI.startsWith("/api/v1/auth/signup") ||
            requestURI.startsWith("/api/v1/auth/signin") ||
            requestURI.startsWith("/api/v1/auth/reissue")
        ) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            val jwt: String = jwtTokenService.resolveToken(request)
            if (StringUtils.hasText(jwt) && jwtTokenService.validateToken(jwt)) {
                val authentication: Authentication = jwtAuthenticationService.getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication
            }
            filterChain.doFilter(request, response)
        } catch (e: InvalidTokenFormatException) {
            setErrorResponse(response, "Invalid token format")
        } catch (e: InvalidTokenException) {
            setErrorResponse(response, "Invalid token")
        } catch (e: ExpiredTokenException) {
            setErrorResponse(response, "Expired token")
        }
    }

    @Throws(IOException::class)
    private fun setErrorResponse(response: HttpServletResponse, errorMessage: String) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        val errorResponse: ErrorResponse = ErrorResponse(403, errorMessage, Status.ERROR)
        val objectMapper = ObjectMapper()
        val responseBody = objectMapper.writeValueAsString(errorResponse)
        response.writer.write(responseBody)
    }

    companion object {
        const val AUTHORIZATION_HEADER: String = "Authorization"
        const val BEARER_PREFIX: String = "Bearer "
    }
}