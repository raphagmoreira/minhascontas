package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.Lancamento;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.infra.persistence.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Rapha on 31/12/2019.
 */
@Service
public class LancamentoService extends ServiceAbstract<Lancamento,Long> {

    private LancamentoRepository lancamentoRepository;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        super(lancamentoRepository);
        this.lancamentoRepository = lancamentoRepository;
    }

    public Page<Lancamento> find(Filter<Lancamento> filter, Pageable pageable) {
        return lancamentoRepository.find(filter,pageable);
    }

}
