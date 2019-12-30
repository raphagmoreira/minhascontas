package br.com.minhascontas.infra.persistence.converter;

import br.com.minhascontas.domain.enums.EnumTipoLancamento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnumTipoLancamentoConverter implements AttributeConverter<EnumTipoLancamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumTipoLancamento tipoLancamento) {
        return tipoLancamento == null ? null : tipoLancamento.getId();
    }

    @Override
    public EnumTipoLancamento convertToEntityAttribute(Integer idTipoLancamento) {
        return idTipoLancamento == null ? null : EnumTipoLancamento.getById(idTipoLancamento);
    }

}