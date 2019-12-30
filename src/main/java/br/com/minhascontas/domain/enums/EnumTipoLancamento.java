package br.com.minhascontas.domain.enums;

import java.io.Serializable;
import java.util.stream.Stream;

public enum EnumTipoLancamento implements Serializable {

    RECEITA(1, "Receita"),
    DESPESA(2, "Despesa");

    private final int id;
    private final String descricao;

    EnumTipoLancamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumTipoLancamento getById(int id) {
        return Stream.of(EnumTipoLancamento.values())
                .filter(e -> id == e.getId()).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumTipoLancamento.class, String.valueOf(id)));
    }

    public static EnumTipoLancamento getByDescricao(String descricao) {
        if (descricao == null){
            return null;
        }
        return Stream.of(EnumTipoLancamento.values())
                .filter(e -> e.getDescricao().trim().toUpperCase().equals(descricao.trim().toUpperCase())).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumTipoLancamento.class, String.valueOf(descricao)));
    }


    public int getId() {
        return id;
    }

}
