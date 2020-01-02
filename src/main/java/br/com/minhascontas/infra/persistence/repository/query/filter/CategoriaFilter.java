package br.com.minhascontas.infra.persistence.repository.query.filter;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.infra.persistence.repository.query.Filter;

/**
 * Created by Rapha on 31/12/2019.
 */
public class CategoriaFilter implements Filter<Categoria> {

    private Long id;
    private String descricao;

    public CategoriaFilter(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
