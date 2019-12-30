package br.com.minhascontas.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_PAGAMENTO")
@SequenceGenerator(allocationSize = 1, name = "seqTipoPagamento", sequenceName = "SEQ_TIPO_PAGAMENTO")
public class TipoPagamento implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "seqTipoPagamento", strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "DESCRICAO")
    private String descricao;

    public TipoPagamento() {}

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

}
