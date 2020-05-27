package br.com.minhascontas.domain.entity;

import com.querydsl.core.annotations.QueryProjection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORIA")
@SequenceGenerator(allocationSize = 1, name = "seqCategoria", sequenceName = "SEQ_CATEGORIA")
public class Categoria implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "seqCategoria", strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "DESCRICAO")
    private String descricao;

    public Categoria() {}

    @QueryProjection
    public Categoria(Long id) {
        this.id = id;
    }

    @QueryProjection
    public Categoria(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

}
