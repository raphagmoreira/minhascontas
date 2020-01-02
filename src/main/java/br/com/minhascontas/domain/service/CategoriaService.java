package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.infra.persistence.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Rapha on 31/12/2019.
 */
@Service
public class CategoriaService extends ServiceAbstract<Categoria,Long> {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        super(categoriaRepository);
        this.categoriaRepository = categoriaRepository;
    }

    public Page<Categoria> find(Filter<Categoria> filter, Pageable pageable) {
        return categoriaRepository.find(filter,pageable);
    }

}
