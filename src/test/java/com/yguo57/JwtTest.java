package com.yguo57;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "michaelflin");

        // Generate GWT
        String token = JWT.create()
                .withClaim("user", claims) // add payload
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // add expire time
                .sign(Algorithm.HMAC256("yguo57"));// set token

        System.out.println(token);
    }

    @Test
    public void testParse() {
        // Mimic user passing token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Im1pY2hhZWxmbGluIn0sImV4cCI6MTcwNzczMDc5MX0." +
                "roIvY3rL8LKtGNGlJP6zO2goQP2KfhEAPwQc3JvC6Pw";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("yguo57")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token); //verify token
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        // Verification would fail id changing head, payload, or expired
    }
}
