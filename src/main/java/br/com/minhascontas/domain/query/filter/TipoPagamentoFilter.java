package br.com.minhascontas.domain.query.filter;

import br.com.minhascontas.domain.entity.TipoPagamento;

/**
 * Created by Rapha on 31/12/2019.
 */
public class TipoPagamentoFilter implements Filter<TipoPagamento> {

    private Long id;
    private String descricao;

    public TipoPagamentoFilter(Long id, String descricao) {
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
