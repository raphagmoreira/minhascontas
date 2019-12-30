package br.com.minhascontas.domain.entity;

import br.com.minhascontas.domain.enums.EnumTipoLancamento;
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
    private EnumTipoLancamento enumTipoLancamento;

    @NotNull
    @Column(name = "VALOR")
    private Double valor;

    @NotNull
    @Column(name = "DATA_LANCAMENTO")
    private Date dataVencimento;

    public Lancamento() {}

}
