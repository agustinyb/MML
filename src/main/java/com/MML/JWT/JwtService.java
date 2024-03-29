package com.MML.JWT;

import com.MML.User.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

private static final String SECRET_KEY="36461351354DSF4563465465SF65465SFD465465SFD4654654654SF4897841321";
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(),user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
   byte [] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
   return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromToken(String token) {
    return null;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
 final String username=getUserNameFromToken(token);
 return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
}

public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
}

private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
}
private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
}




}
