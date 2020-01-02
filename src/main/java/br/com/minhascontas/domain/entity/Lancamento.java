package br.com.minhascontas.domain.entity;

import br.com.minhascontas.domain.enums.EnumPeriodicidade;
import br.com.minhascontas.domain.enums.EnumSituacaoLancamento;
import br.com.minhascontas.domain.enums.EnumTipoLancamento;
import br.com.minhascontas.infra.persistence.converter.EnumPeriodicidadeConverter;
import br.com.minhascontas.infra.persistence.converter.EnumSituacaoLancamentoConverter;
import br.com.minhascontas.infra.persistence.converter.EnumTipoLancamentoConverter;
import com.querydsl.core.annotations.QueryProjection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LANCAMENTO")
@SequenceGenerator(allocationSize = 1, name = "seqLancamento", sequenceName = "SEQ_LANCAMENTO")
public class Lancamento implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "seqLancamento", strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 500)
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "FK_TIPO_LANCAMENTO")
    @Convert(converter = EnumTipoLancamentoConverter.class)
    private EnumTipoLancamento tipoLancamento;

    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;

    @NotNull
    @Column(name = "DATA_LANCAMENTO")
    private LocalDate dataVencimento;

    @Column(name = "FK_SITUACAO")
    @Convert(converter = EnumSituacaoLancamentoConverter.class)
    private EnumSituacaoLancamento situacao;

    @Column(name = "FK_PERIODICIDADE")
    @Convert(converter = EnumPeriodicidadeConverter.class)
    private EnumPeriodicidade periodicidade;

    @NotNull
    @Column(name = "QUANTIDADE_PERIODO")
    private Integer quantidadePeriodo;

    @NotNull
    @Column(name = "PARCELA")
    private Integer parcela;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CATEGORIA")
    private Categoria categoria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_TIPO_PAGAMENTO")
    private TipoPagamento tipoPagamento;

    public Lancamento() {}

    @QueryProjection
    public Lancamento(Long id) {
        this.id = id;
    }

    @QueryProjection
    public Lancamento(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @QueryProjection
    public Lancamento(Long id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    @QueryProjection
    public Lancamento(Long id, String descricao, BigDecimal valor, LocalDate dataVencimento) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
    }

    @QueryProjection
    public Lancamento(Long id, String descricao, BigDecimal valor, LocalDate dataVencimento, EnumSituacaoLancamento situacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
    }

    @QueryProjection
    public Lancamento(Long id,
                      String descricao,
                      BigDecimal valor,
                      LocalDate dataVencimento,
                      EnumSituacaoLancamento situacao,
                      EnumPeriodicidade periodicidade) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
        this.periodicidade = periodicidade;
    }

    @QueryProjection
    public Lancamento(Long id,
                      String descricao,
                      BigDecimal valor,
                      LocalDate dataVencimento,
                      EnumSituacaoLancamento situacao,
                      EnumPeriodicidade periodicidade,
                      Integer quantidadePeriodo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
        this.periodicidade = periodicidade;
        this.quantidadePeriodo = quantidadePeriodo;
    }

    @QueryProjection
    public Lancamento(Long id,
                      String descricao,
                      BigDecimal valor,
                      LocalDate dataVencimento,
                      EnumSituacaoLancamento situacao,
                      EnumPeriodicidade periodicidade,
                      Integer quantidadePeriodo,
                      Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
        this.periodicidade = periodicidade;
        this.quantidadePeriodo = quantidadePeriodo;
        this.categoria = categoria;
    }

    @QueryProjection
    public Lancamento(Long id,
                      String descricao,
                      BigDecimal valor,
                      LocalDate dataVencimento,
                      EnumSituacaoLancamento situacao,
                      EnumPeriodicidade periodicidade,
                      Integer quantidadePeriodo,
                      Categoria categoria,
                      TipoPagamento tipoPagamento) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
        this.periodicidade = periodicidade;
        this.quantidadePeriodo = quantidadePeriodo;
        this.categoria = categoria;
        this.tipoPagamento = tipoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EnumTipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(EnumTipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public EnumSituacaoLancamento getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacaoLancamento situacao) {
        this.situacao = situacao;
    }

    public EnumPeriodicidade getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(EnumPeriodicidade periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Integer getQuantidadePeriodo() {
        return quantidadePeriodo;
    }

    public void setQuantidadePeriodo(Integer quantidadePeriodo) {
        this.quantidadePeriodo = quantidadePeriodo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }
}
