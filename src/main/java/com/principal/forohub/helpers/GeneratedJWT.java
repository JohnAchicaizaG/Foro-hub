package com.principal.forohub.helpers;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class GeneratedJWT {

    @Value("${spring.security.token.password}")
    private String clave;

    @Value("${spring.security.token.user}")
    private String creador;

    public DecodedJWT validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(clave);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer(creador)
                    // reusable verifier instance
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalido");
        }
    }

    public String crearToken(Authentication authentication) {

        String usuario = authentication.getName();

        String rol = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));


            Algorithm algorithm = Algorithm.HMAC256(clave);
            String token = JWT.create()
                    .withIssuer(creador)
                    .withSubject(usuario)
                    .withJWTId(UUID.randomUUID().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis()+100000))
                    .withIssuedAt(new Date())
                    .withClaim("rol", rol)
                    .sign(algorithm);
        return token;
    }



    public String extractUsuario(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim claimEspecifico(String claim, DecodedJWT decodedJWT){
        return decodedJWT.getClaim(claim);
    }


}
