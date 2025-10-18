package yegam.placeservice.global.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yegam.placeservice.global.exception.CustomException;
import yegam.placeservice.global.exception.GlobalErrorCode;

import java.security.Key;
import java.util.Base64;

@Slf4j
@Component
public class JwtProvider {

  private final Key key;

  public JwtProvider(@Value("${spring.jwt.secret}") String secretKey) {
    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  /** JWT 유효성 검증 */
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

  /** JWT에서 사용자 ID 추출 (subject 기준) */
  public Long extractUserId(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();

    // subject가 문자열 형태로 저장되므로 Long으로 변환
    String subject = claims.getSubject();
    return Long.parseLong(subject);
  }

  /** JWT에서 이메일 추출 (선택사항) */
  public String extractEmail(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("email", String.class);
  }
}
