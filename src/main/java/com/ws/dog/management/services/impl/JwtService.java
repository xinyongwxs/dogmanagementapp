package com.ws.dog.management.services.impl;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ws.dog.management.constants.JwtConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.RsaProvider;

@Service
public class JwtService {
	private static final Logger log = LoggerFactory.getLogger(JwtService.class);
	
	private static final int RSA_KEY_LENGTH = 1024;
	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.RS512;
	
	@SuppressWarnings("unused")
    private PublicKey publicKey;
    private PrivateKey privateKey;
    
	private void generateKeyPair() {
	    KeyPair kp = RsaProvider.generateKeyPair(RSA_KEY_LENGTH);
	    publicKey = kp.getPublic();
        privateKey = kp.getPrivate();
	}
	
	public JwtService() {
		generateKeyPair();
	}
	
	public String generateToken(String email, Integer timeoutMin) {
	    SignatureAlgorithm signatureAlgorithm = SIGNATURE_ALGORITHM;
	 
	    JwtBuilder builder = Jwts.builder()
	    		.claim(JwtConstants.EMAIL, email)
	            .signWith(signatureAlgorithm, privateKey);
	 
	    long nowMillis = System.currentTimeMillis();
	    long ttlMillis = timeoutMin * 60 * 1000;
	    if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
	    }
	 
	    return builder.compact();
	}
	
	public Claims parseToken(String token) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
		try {
		    Claims claims = Jwts.parser()
		    	.setSigningKey(privateKey)
		 	    .parseClaimsJws(token).getBody();
		 	return claims;
		} catch (Exception e) {
			log.warn("Jwt parse token failed: {} ", e.getMessage());
		}
		return null;
	}
}
