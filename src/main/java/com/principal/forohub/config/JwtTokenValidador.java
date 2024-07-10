package com.principal.forohub.config;

import java.io.IOException;
import java.util.Collection;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.principal.forohub.helpers.GeneratedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenValidador extends OncePerRequestFilter  {

    private GeneratedJWT generatedJWT;

    public JwtTokenValidador(GeneratedJWT generatedJWT) {
        this.generatedJWT = generatedJWT;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            
            String jwtToken = token.substring(7);
            DecodedJWT decodedJWT = generatedJWT.validarToken(jwtToken);
            String usuario = generatedJWT.extractUsuario(decodedJWT);
            String rol = generatedJWT.claimEspecifico("rol", decodedJWT).asString();
            Collection<? extends GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(rol);
            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, roles);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }

    
    


}
