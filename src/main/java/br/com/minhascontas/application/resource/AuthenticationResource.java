package br.com.minhascontas.application.resource;

import br.com.minhascontas.domain.dto.TokenDTO;
import br.com.minhascontas.domain.entity.Authentication;
import br.com.minhascontas.domain.entity.Usuario;
import br.com.minhascontas.domain.service.UsuarioService;
import br.com.minhascontas.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationResource {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SecurityService securityService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> authentication(@RequestBody Authentication authentication) throws Exception {
        return ResponseEntity.ok(
                securityService.login(authentication)
        );
    }

}
