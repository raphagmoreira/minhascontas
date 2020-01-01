package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.domain.exception.EntityNotFoundException;
import br.com.minhascontas.infra.persistence.query.Query;
import br.com.minhascontas.infra.persistence.query.Sorter;
import br.com.minhascontas.infra.persistence.query.impl.CategoriaFilter;
import br.com.minhascontas.infra.persistence.query.impl.TipoPagamentoFilter;
import br.com.minhascontas.infra.persistence.repository.CategoriaRepository;
import br.com.minhascontas.infra.persistence.repository.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rapha on 31/12/2019.
 */
@Service
public class TipoPagamentoService implements Serializable {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public List<TipoPagamento> find(TipoPagamentoFilter tipoPagamentoFilter,
                                    String sortProperty,
                                    Sorter.Direction sortDirection,
                                    Long page) {
        //Constrói a query para a entidade Pessoa
        final Query<TipoPagamento> query = Query.<TipoPagamento>builder()
                .filter(tipoPagamentoFilter)
                .sort(Sorter.<TipoPagamento>by(sortProperty).direction(sortDirection))
                .page(page)
                .build();

        return tipoPagamentoRepository.find(query);
    }

    public TipoPagamento findById(Long id) {
        return tipoPagamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(TipoPagamento tipoPagamento) {
        tipoPagamentoRepository.create(tipoPagamento);
    }

    @Transactional
    public void update(TipoPagamento tipoPagamento) {
        tipoPagamentoRepository.update(tipoPagamento);
    }

    @Transactional
    public void remove(Long idTipoPagamento) {
        tipoPagamentoRepository.removeById(idTipoPagamento);
    }

}
