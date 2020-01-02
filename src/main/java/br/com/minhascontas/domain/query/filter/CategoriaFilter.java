package br.com.minhascontas.domain.query.filter;

import br.com.minhascontas.domain.entity.Categoria;

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
