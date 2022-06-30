package com.blog.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// contains method for generating token
// validate token
// isExpired
// Util Class for JWT  
@Component
public class JwtUtil {

	private String SECRET_KEY = "secret";
	
	public JwtUtil() {
		System.out.println("hi iam constructor of jwttuil");
	}

	// retrieve user name from the token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	// retrieve expiration date from the jwt token
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will nedd the secret key
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	// check if the token is expired
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// generate token for user based on their username.
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	
// while creating the token ---
	//1 . Define claims of the token, like Issuer, expiration, subject, and
	//2. Sign the JWT using hte HS512 algorithm and secret key.
	//3. acc. to JWS compact serialization(https://tools.jetf.org/html/draft...
	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
