package com.example.demo.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	private final String secretKey="dbunjtgbhajnjvndvuvdvdvjsdvdufyevlinjgbschsclmckkjcbsbcjhacb";

	private final long TOKEN_EXPIRY_DURATION=5*60000;
	
	
	private SecretKey getSecretKey() {
	byte[]	keyBytes=Decoders.BASE64.decode(secretKey);
	return Keys.hmacShaKeyFor(keyBytes);
	}
   public String createToken(String email) {
	   String token=null;
	   token=Jwts.builder()
			   .subject(email)
			   .issuedAt(new Date(System.currentTimeMillis()))
			   .expiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRY_DURATION))
			   .signWith(getSecretKey()).compact();
	   return token;
   }
   
   public String getUserIdFromToken(String token) {
	   return Jwts.parser().verifyWith(getSecretKey())
			   . build()
			   .parseSignedClaims(token)
			   .getPayload().getSubject();
   }
   
   private boolean isTokenExpired(String token) {
	   Date expiryDate=Jwts.parser().verifyWith(getSecretKey())
			   . build()
			   .parseSignedClaims(token)
			   .getPayload().getExpiration();
	   return expiryDate.after(new Date());
   }
   
   public boolean validateToken(String token,String requestedUserId) {
	   String userIDFromToken=getUserIdFromToken(token);
	   return userIDFromToken.equalsIgnoreCase(requestedUserId) && isTokenExpired(token);
   }

}
