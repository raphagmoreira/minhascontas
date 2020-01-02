package br.com.minhascontas.domain.query.filter;

import br.com.minhascontas.domain.entity.Lancamento;
import br.com.minhascontas.domain.enums.EnumPeriodicidade;
import br.com.minhascontas.domain.enums.EnumSituacaoLancamento;
import br.com.minhascontas.domain.enums.EnumTipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Rapha on 31/12/2019.
 */
public class LancamentoFilter implements Filter<Lancamento> {

    private Long id;
    private String descricao;
    private EnumTipoLancamento tipoLancamento;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private EnumSituacaoLancamento situacao;
    private EnumPeriodicidade periodicidade;
    private Integer quantidadePeriodo;
    private Long idCategoria;
    private Long idTipoPagamento;

    public LancamentoFilter(Long id,
                            String descricao,
                            EnumTipoLancamento tipoLancamento,
                            BigDecimal valor,
                            LocalDate dataVencimento,
                            EnumSituacaoLancamento situacao,
                            EnumPeriodicidade periodicidade,
                            Integer quantidadePeriodo,
                            Long idCategoria,
                            Long idTipoPagamento) {
        this.id = id;
        this.descricao = descricao;
        this.tipoLancamento = tipoLancamento;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
        this.periodicidade = periodicidade;
        this.quantidadePeriodo = quantidadePeriodo;
        this.idCategoria = idCategoria;
        this.idTipoPagamento = idTipoPagamento;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public EnumTipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public EnumSituacaoLancamento getSituacao() {
        return situacao;
    }

    public EnumPeriodicidade getPeriodicidade() {
        return periodicidade;
    }

    public Integer getQuantidadePeriodo() {
        return quantidadePeriodo;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdTipoPagamento() {
        return idTipoPagamento;
    }
}
