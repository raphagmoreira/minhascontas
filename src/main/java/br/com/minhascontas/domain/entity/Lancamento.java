package br.com.minhascontas.domain.entity;

import br.com.minhascontas.domain.enums.EnumPeriodicidade;
import br.com.minhascontas.domain.enums.EnumSituacaoLancamento;
import br.com.minhascontas.domain.enums.EnumTipoLancamento;
import br.com.minhascontas.infra.persistence.converter.EnumPeriodicidadeConverter;
import br.com.minhascontas.infra.persistence.converter.EnumSituacaoLancamentoConverter;
import br.com.minhascontas.infra.persistence.converter.EnumTipoLancamentoConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

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
    private Double valor;

    @NotNull
    @Column(name = "DATA_LANCAMENTO")
    private Date dataVencimento;

    @Column(name = "FK_SITUACAO")
    @Convert(converter = EnumSituacaoLancamentoConverter.class)
    private EnumSituacaoLancamento situacao;

    @Column(name = "FK_PERIODICIDADE")
    @Convert(converter = EnumPeriodicidadeConverter.class)
    private EnumPeriodicidade periodicidade;

    @NotNull
    @Column(name = "QUANTIDADE_PERIODO")
    private Integer quantidadePeriodo;

    public Lancamento() {}

}
