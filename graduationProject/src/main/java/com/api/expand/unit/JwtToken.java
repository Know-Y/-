package com.api.expand.unit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.*;

public class JwtToken {
    public static String SECRET = "knowovo";
    public String createToken(String id,String name,String phone) throws Exception{
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,1);
        Date expiresDate = nowTime.getTime();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String token = JWT.create().withHeader(map)
                .withClaim("id",id)
                .withClaim("name",name)
                .withClaim("phone",phone)
                .withExpiresAt(expiresDate)
                .withIssuedAt(iatDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }
}
