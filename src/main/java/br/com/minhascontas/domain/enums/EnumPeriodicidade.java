package br.com.minhascontas.domain.enums;

import java.io.Serializable;
import java.util.stream.Stream;

public enum EnumPeriodicidade implements Serializable {

    DIARIO(1, "Diário"),
    SEMANAL(2, "Semanal"),
    MENSAL(3, "Mensal"),
    ANUAL(4, "Anual");

    private final int id;
    private final String descricao;

    EnumPeriodicidade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumPeriodicidade getById(int id) {
        return Stream.of(EnumPeriodicidade.values())
                .filter(e -> id == e.getId()).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumPeriodicidade.class, String.valueOf(id)));
    }

    public static EnumPeriodicidade getByDescricao(String descricao) {
        if (descricao == null){
            return null;
        }
        return Stream.of(EnumPeriodicidade.values())
                .filter(e -> e.getDescricao().trim().toUpperCase().equals(descricao.trim().toUpperCase())).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumPeriodicidade.class, String.valueOf(descricao)));
    }


    public int getId() {
        return id;
    }

}
