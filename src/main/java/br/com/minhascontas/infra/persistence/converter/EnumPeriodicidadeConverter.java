package br.com.minhascontas.infra.persistence.converter;

import br.com.minhascontas.domain.enums.EnumPeriodicidade;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnumPeriodicidadeConverter implements AttributeConverter<EnumPeriodicidade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumPeriodicidade periodicidade) {
        return periodicidade == null ? null : periodicidade.getId();
    }

    @Override
    public EnumPeriodicidade convertToEntityAttribute(Integer idPeriodicidade) {
        return idPeriodicidade == null ? null : EnumPeriodicidade.getById(idPeriodicidade);
    }

}