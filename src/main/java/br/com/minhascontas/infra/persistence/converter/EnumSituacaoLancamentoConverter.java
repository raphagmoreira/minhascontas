package br.com.minhascontas.infra.persistence.converter;

import br.com.minhascontas.domain.enums.EnumSituacaoLancamento;
import br.com.minhascontas.domain.enums.EnumTipoLancamento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnumSituacaoLancamentoConverter implements AttributeConverter<EnumSituacaoLancamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumSituacaoLancamento situacaoLancamento) {
        return situacaoLancamento == null ? null : situacaoLancamento.getId();
    }

    @Override
    public EnumSituacaoLancamento convertToEntityAttribute(Integer idSituacaoLancamento) {
        return idSituacaoLancamento == null ? null : EnumSituacaoLancamento.getById(idSituacaoLancamento);
    }

}