package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.infra.persistence.repository.query.Filter;
import br.com.minhascontas.infra.persistence.repository.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Rapha on 31/12/2019.
 */
@Service
public class TipoPagamentoService extends ServiceAbstract<TipoPagamento,Long> {

    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    public TipoPagamentoService(TipoPagamentoRepository tipoPagamentoRepository) {
        super(tipoPagamentoRepository);
        this.tipoPagamentoRepository = tipoPagamentoRepository;
    }

    public Page<TipoPagamento> find(Filter<TipoPagamento> filter, Pageable pageable) {
        return tipoPagamentoRepository.find(filter,pageable);
    }

}
