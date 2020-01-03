package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.Usuario;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.infra.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Rapha on 08/08/2019.
 */
@Service
public class UsuarioService extends ServiceAbstract<Usuario,Long> {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Usuario> find(Filter<Usuario> filter, Pageable pageable) {
        return usuarioRepository.find(filter,pageable);
    }

    public Usuario findByUser(String user) {
        return usuarioRepository.findByUser(user);
    }

}
