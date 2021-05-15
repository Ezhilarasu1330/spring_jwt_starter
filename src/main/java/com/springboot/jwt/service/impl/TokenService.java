package com.springboot.jwt.service.impl;

import com.springboot.jwt.DataVO.BaseVO;
import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.entity.UserProfile;
import com.springboot.jwt.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenService implements ITokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiry}")
    private long expiry;

    private final Integer passwordStrength = 12;

    @Override
    public String generateToken(final UserProfile userProfile) {
        final Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", userProfile.getUserId());
        claims.put("expiryHash", userProfile.getExpiryHash());
        return this.createToken(claims, userProfile);
    }

    @Override
    public BaseVO validateToken(final UserProfileVO userProfileVO) {
        final String emailId = this.extractUserName(userProfileVO);
        this.isTokenExpired(userProfileVO);
        final Claims claims = this.extractAllClaims(userProfileVO);
        userProfileVO.setUserId(Long.valueOf(claims.get("userId").toString()));
        userProfileVO.setExpiryHash(claims.get("expiryHash").toString());
        userProfileVO.setEmailId(emailId);
        return userProfileVO;
    }

    private String extractUserName(final UserProfileVO userProfileVO) {
        return this.extractClaim(userProfileVO, Claims::getSubject);
    }

    private Date extractExpiration(final UserProfileVO userProfileVO) {
        return this.extractClaim(userProfileVO, Claims::getExpiration);
    }

    private <T> T extractClaim(final UserProfileVO userProfileVO, final Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(userProfileVO);
        return claimsResolver.apply(claims);
    }

    private String createToken(final Map<String, Object> claims, final UserProfile userProfile) {
        return Jwts.builder().setClaims(claims).setSubject(userProfile.getEmailId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.expiry))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private Claims extractAllClaims(final UserProfileVO userProfileVO) {
        return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(userProfileVO.getToken()).getBody();
    }

    private Boolean isTokenExpired(final UserProfileVO userProfileVO) {
        return this.extractExpiration(userProfileVO).before(new Date());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(this.passwordStrength);
    }
}
