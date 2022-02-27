package com.ttice.Util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static long time = 1000*60*60*24;//失效间隔
    private static String signatuer = "admin";

    public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","admin")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signatuer)
                .compact();
        return jwtToken;
    }
    public static boolean checkToken(String token){
        if (token == null) {
            return false;
        }
        try {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signatuer).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
