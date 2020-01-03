package br.com.minhascontas.security.service;

import br.com.minhascontas.domain.dto.TokenDTO;
import br.com.minhascontas.domain.exception.UserNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import javax.security.sasl.AuthenticationException;

public interface SecurityService {

    TokenDTO login(String username, String password, Long userId) throws BadCredentialsException,
            AuthenticationException, UserNotFoundException, Exception;

}
