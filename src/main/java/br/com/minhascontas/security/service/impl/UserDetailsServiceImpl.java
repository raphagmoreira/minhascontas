package br.com.minhascontas.security.service.impl;

import br.com.minhascontas.domain.entity.Usuario;
import br.com.minhascontas.domain.exception.UserNotFoundException;
import br.com.minhascontas.infra.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UserNotFoundException {
        try {
            Usuario usuario = usuarioRepository.findByUser(login);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            return User.withUsername(usuario.getUser()).password(usuario.getPassword()).authorities(grantedAuthorities).build();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException();
        }
    }

}