package com.spring.hdb.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;


@Service
public class JWTService {
	
	@Value("${jwt.secret.key}")
	String secret;
	
	//public Key key = Keys.hmacShaKeyFor(secret.getBytes());
	
	public String GenerateToken(UserDetails user) throws WeakKeyException, InvalidKeyException {
		
		Claims claims = Jwts.claims();;
		claims.put("username", user.getUsername());
		claims.put("password", user.getPassword());
		claims.put("authorities", user.getAuthorities());
		
		return Jwts.builder()
		.setSubject(user.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + 100*60*60*60))
		.setIssuer("Sanat Dey")
		.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
		.compact();	
	}
	
	public Claims extractClaims(String token) {

			return Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
					.build()
					.parseClaimsJws(token)
					.getBody();

	}
	
	public Claims extractAllClaims(String token) {

		return Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();
}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
	
	public boolean isTokenExpired(String token) {
		
		Date exp = extractClaims(token).getExpiration();
		Date now = new Date(System.currentTimeMillis());
		return exp.before(now);
	}
	
	public String ValidateToken(String token) {
		String tusername = extractClaims(token).getSubject();

		if(tusername != null) {
			
			if(!isTokenExpired(token)) {
				return tusername;
				
			}else {
				return "-1";
			}
		}

		return null;
	}

}
