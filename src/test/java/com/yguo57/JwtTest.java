package com.yguo57;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "zhangsan");

        // Generate GWT
        String token = JWT.create()
                .withClaim("user", claims) // add payload
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // add expire time
                .sign(Algorithm.HMAC256("yguo57"));// set token

        System.out.println(token);
    }
}
