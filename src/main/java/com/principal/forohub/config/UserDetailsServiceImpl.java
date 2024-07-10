package com.principal.forohub.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.principal.forohub.controllers.LoginRequestDTO;
import com.principal.forohub.controllers.LoginResponseDTO;
import com.principal.forohub.entities.UsuarioEntity;
import com.principal.forohub.repositories.UserRepository;
import com.principal.forohub.helpers.GeneratedJWT;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GeneratedJWT generatedJWT;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = userRepository.findByNombre(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Collection<? extends GrantedAuthority> authority = Arrays.asList(new SimpleGrantedAuthority("ROLE_".concat(usuarioEntity.getPerfilEntity().getNombre())));
        return new User(username, usuarioEntity.getContrasena(), authority);
    }

    public LoginResponseDTO login(LoginRequestDTO login) {
        String usuario = login.getUsuario();
        String contrasenia = login.getContrasenia();
        Authentication authentication = autenticar(usuario, contrasenia);
        String token = generatedJWT.crearToken(authentication);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(usuario, token);
        return loginResponseDTO;
    }

    private Authentication autenticar(String usuario, String contrasenia) {        
        UserDetails user = loadUserByUsername(usuario);
        if (!passwordEncoder.matches(contrasenia ,user.getPassword())) {
            throw new BadCredentialsException("Hay un error en las credenciales otorgadas");
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

}
