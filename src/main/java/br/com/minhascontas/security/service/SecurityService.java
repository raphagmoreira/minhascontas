package br.com.minhascontas.security.service;

import br.com.minhascontas.domain.dto.TokenDTO;
import br.com.minhascontas.domain.exception.UserNotFoundException;
import br.com.minhascontas.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.Objects;

@Service
@Component
public class SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public TokenDTO login(br.com.minhascontas.domain.entity.Authentication authentication) throws Exception {

        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getUsername());

            if (Objects.nonNull(userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, authentication.getPassword(), userDetails.getAuthorities()
                );

                Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

                if (auth.isAuthenticated()) {
                    return TokenAuthenticationService.addAuthentication(auth);
                } else {
                    throw new AuthenticationException();
                }

            } else {
                throw new UserNotFoundException();
            }

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
