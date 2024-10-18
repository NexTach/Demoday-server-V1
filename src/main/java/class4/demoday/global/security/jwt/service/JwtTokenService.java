package class4.demoday.global.security.jwt.service;

import class4.demoday.domain.auth.dto.response.RefreshResponse;
import class4.demoday.global.exception.ExpiredRefreshTokenException;
import class4.demoday.global.exception.ExpiredTokenException;
import class4.demoday.global.exception.InvalidTokenException;
import class4.demoday.global.exception.InvalidTokenFormatException;
import class4.demoday.global.member.entity.Member;
import class4.demoday.global.member.repository.MemberRepository;
import class4.demoday.global.security.jwt.dto.TokenResponse;
import class4.demoday.global.security.jwt.entity.RefreshToken;
import class4.demoday.global.security.jwt.repository.RefreshTokenRepository;
import class4.demoday.global.security.roles.Roles;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static class4.demoday.global.security.jwt.filter.JwtFilter.AUTHORIZATION_HEADER;
import static class4.demoday.global.security.jwt.filter.JwtFilter.BEARER_PREFIX;

@Component
public class JwtTokenService {
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_TIME = 60L * 30 * 4;
    private static final long REFRESH_TOKEN_TIME = 60L * 60 * 24 * 7;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;

    public JwtTokenService(RefreshTokenRepository refreshTokenRepository, MemberRepository memberRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberRepository = memberRepository;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Serializable getKey() {
        return key;
    }

    public TokenResponse generateTokenDto(String username, Roles roles) {
        String accessToken = generateAccessToken(username, roles);
        String refreshToken = generateRefreshToken(username);
        RefreshToken refreshTokenEntity = new RefreshToken(
                UUID.randomUUID().toString(),
                refreshToken,
                username,
                LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME)
        );
        refreshTokenRepository.save(refreshTokenEntity);
        return new TokenResponse(
                accessToken,
                refreshToken,
                LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME),
                LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME),
                roles
        );
    }

    private String generateAccessToken(String subject, Roles roles) {
        Date expiration = new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME * 1000);
        return Jwts.builder().
                setSubject(subject).
                claim(AUTHORITIES_KEY, roles).
                setIssuedAt(new Date()).
                setExpiration(expiration).
                signWith(key, SignatureAlgorithm.HS256).
                compact();
    }

    private String generateRefreshToken(String username) {
        Date refreshTokenExpiresIn = new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME * 1000);
        return Jwts.builder().
                setSubject(username).
                setExpiration(refreshTokenExpiresIn).
                signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("Token expired");
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Invalid token");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        throw new InvalidTokenFormatException("Invalid token format");
    }

    public String resolveToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }
        throw new InvalidTokenFormatException("Invalid token format");
    }

    public Long getExpiration(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        return expiration.getTime();
    }

    public RefreshResponse reissueToken(String refreshToken) {
        RefreshToken storedToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new InvalidTokenException("Invalid refresh token"));
        if (storedToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredRefreshTokenException("Refresh token expired");
        }
        Member member = memberRepository.findByPhoneNumber(storedToken.getUsername());
        if (member == null) {
            throw new InvalidTokenException("User not found");
        }
        String newAccessToken = generateAccessToken(member.getPhoneNumber(), member.getRole());
        return new RefreshResponse(
                newAccessToken,
                LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME),
                member.getRole()
        );
    }
    public void invalidateRefreshToken(String username) {
        RefreshToken storedToken = refreshTokenRepository.findByUsername(username);
        if (storedToken != null) {
            refreshTokenRepository.delete(storedToken);
        }
    }
}