package yegam.userservice.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yegam.userservice.global.exception.CustomException;
import yegam.userservice.global.exception.GlobalErrorCode;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

  private final Key key;
  private final long accessTokenExpireTime;
  private final long refreshTokenExpireTime;

  public JwtProvider(
      @Value("${spring.jwt.secret}") String secretKey,
      @Value("${spring.jwt.access-token-expire-time}") long accessTokenExpireTime,
      @Value("${spring.jwt.refresh-token-expire-time}") long refreshTokenExpireTime
  ) {
    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.accessTokenExpireTime = accessTokenExpireTime;
    this.refreshTokenExpireTime = refreshTokenExpireTime;
  }

  /* Access Token 생성 */
  public String createAccessToken(String userId, String email, String role) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + accessTokenExpireTime);

    return Jwts.builder()
        .setSubject(userId)                     // sub: userId
        .claim("email", email)                  // 추가 claim
        .claim("role", role)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  /* Refresh Token 생성 */
  public String createRefreshToken(String userId) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + refreshTokenExpireTime);

    return Jwts.builder()
        .setSubject(userId)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  /*JWT 유효성 검증 */
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      throw new CustomException(GlobalErrorCode.JWT_EXPIRED);
    } catch (JwtException | IllegalArgumentException e) {
      throw new CustomException(GlobalErrorCode.JWT_INVALID);
    }
  }

  /* 남은 유효 시간(ms) */
  public long getRemainingTime(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return claims.getExpiration().getTime() - System.currentTimeMillis();
    } catch (ExpiredJwtException e) {
      return 0;
    }
  }

  /* 사용자 ID 추출 */
  public String extractUserId(String token) {
    return parseClaims(token).getSubject();
  }

  /* 이메일 추출 */
  public String extractEmail(String token) {
    return parseClaims(token).get("email", String.class);
  }

  /* role 추출 (선택) */
  public String extractRole(String token) {
    return parseClaims(token).get("role", String.class);
  }

  /* 내부 Claims 파서 */
  private Claims parseClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
