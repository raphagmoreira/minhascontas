package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.exception.EntityNotFoundException;
import br.com.minhascontas.infra.persistence.query.Query;
import br.com.minhascontas.infra.persistence.query.Sorter;
import br.com.minhascontas.infra.persistence.query.impl.CategoriaFilter;
import br.com.minhascontas.infra.persistence.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rapha on 31/12/2019.
 */
@Service
public class CategoriaService implements Serializable {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> find(CategoriaFilter categoriaFilter,
                                String sortProperty,
                                Sorter.Direction sortDirection,
                                Long page) {
        //Constrói a query para a entidade Pessoa
        final Query<Categoria> query = Query.<Categoria>builder()
                .filter(categoriaFilter)
                .sort(Sorter.<Categoria>by(sortProperty).direction(sortDirection))
                .page(page)
                .build();

        return categoriaRepository.find(query);
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Categoria categoria) {
        categoriaRepository.create(categoria);
    }

    @Transactional
    public void update(Categoria categoria) {
        categoriaRepository.update(categoria);
    }

    @Transactional
    public void remove(Long idCategoria) {
        categoriaRepository.removeById(idCategoria);
    }

}
