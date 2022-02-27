package ttice.springshop;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class JWT {

    private long time = 1000*60*60*24;
    private String signatuer = "admin";

    //生成jwt
    @org.junit.Test
    public void jwt(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","tom")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signatuer)
                .compact();
        System.out.println(jwtToken);
    }

    //解密
    @org.junit.Test
    public void parse(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NDIwOTIzNzcsImp0aSI6IjhkNjNmMjljLTc1NzItNGVkMS1iMGYzLTUzMGFhNzRkODcxMiJ9.u-cEd9O-c75cNNgaEZJu7zn9aQCLpoJvKZJKw_JocGQ";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signatuer).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());

    }

}
