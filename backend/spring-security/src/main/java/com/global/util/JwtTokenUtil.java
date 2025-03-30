// package com.global.util;
//
// import java.nio.charset.StandardCharsets;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
//
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
//
// @Component
// public class JwtTokenUtil {
//
// 	@Value("${jwt.secret}")
// 	private String secret;
//
// 	@Value("${jwt.expiration}")
// 	private long expiration;
//
// 	// JWT 토큰에서 사용자 이름 추출
// 	public String getUsernameFromToken(String token) {
// 		return getClaimFromToken(token, Claims::getSubject);
// 	}
//
// 	// 토큰에서 정보 추출
// 	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
// 		final Claims claims = getAllClaimsFromToken(token);
// 		return claimsResolver.apply(claims);
// 	}
//
// 	// 비밀키를 사용해 토큰에서 정보 추출
// 	private Claims getAllClaimsFromToken(String token) {
// 		return Jwts.parserBuilder()
// 			.setSigningKey(getSigningKey())
// 			.build()
// 			.parseClaimsJws(token)
// 			.getBody();
// 	}
//
// 	private Key getSigningKey() {
// 		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
// 		return Keys.hmacShaKeyFor(keyBytes);
// 	}
//
// 	// 토큰 만료 확인
// 	public Boolean isTokenExpired(String token) {
// 		final Date expiration = getExpirationDateFromToken(token);
// 		return expiration.before(new Date());
// 	}
//
// 	// 토큰에서 만료 날짜 추출
// 	public Date getExpirationDateFromToken(String token) {
// 		return getClaimFromToken(token, Claims::getExpiration);
// 	}
//
// 	// 사용자에 대한 토큰 생성
// 	public String generateToken(UserDetails userDetails) {
// 		Map<String, Object> claims = new HashMap<>();
// 		return doGenerateToken(claims, userDetails.getUsername());
// 	}
//
// 	private String doGenerateToken(Map<String, Object> claims, String subject) {
// 		return Jwts.builder()
// 			.setClaims(claims)
// 			.setSubject(subject)
// 			.setIssuedAt(new Date(System.currentTimeMillis()))
// 			.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
// 			.signWith(getSigningKey(), SignatureAlgorithm.HS512)
// 			.compact();
// 	}
//
// 	// 토큰 검증
// 	public Boolean validateToken(String token, UserDetails userDetails) {
// 		final String username = getUsernameFromToken(token);
// 		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
// 	}
// }
