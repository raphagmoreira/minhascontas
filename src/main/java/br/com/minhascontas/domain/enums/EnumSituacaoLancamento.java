package br.com.minhascontas.domain.enums;

import java.io.Serializable;
import java.util.stream.Stream;

public enum EnumSituacaoLancamento implements Serializable {

    PENDENTE(1, "Pendente"),
    PAGO(2, "Pago");

    private final int id;
    private final String descricao;

    EnumSituacaoLancamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumSituacaoLancamento getById(int id) {
        return Stream.of(EnumSituacaoLancamento.values())
                .filter(e -> id == e.getId()).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumSituacaoLancamento.class, String.valueOf(id)));
    }

    public static EnumSituacaoLancamento getByDescricao(String descricao) {
        if (descricao == null){
            return null;
        }
        return Stream.of(EnumSituacaoLancamento.values())
                .filter(e -> e.getDescricao().trim().toUpperCase().equals(descricao.trim().toUpperCase())).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumSituacaoLancamento.class, String.valueOf(descricao)));
    }


    public int getId() {
        return id;
    }

}
