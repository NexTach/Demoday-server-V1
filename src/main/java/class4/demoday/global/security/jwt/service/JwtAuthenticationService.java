package class4.demoday.global.security.jwt.service;

import class4.demoday.global.member.service.MemberDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtAuthenticationService {
    private final MemberDetailsService memberDetailsService;
    private final JwtTokenService jwtTokenService;

    public JwtAuthenticationService(MemberDetailsService memberDetailsService, JwtTokenService jwtTokenService) {
        this.memberDetailsService = memberDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    public Authentication getAuthentication(String token) {
        Key key = (Key) jwtTokenService.getKey();
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        UserDetails userDetails = memberDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}