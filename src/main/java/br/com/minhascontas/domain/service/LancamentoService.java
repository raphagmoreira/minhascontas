package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.Lancamento;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.infra.persistence.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

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

    @Transactional
    public Lancamento createLancamento(Lancamento lancamento) {
        BigDecimal valorParcela = lancamento.getValor().divide(new BigDecimal(lancamento.getQuantidadePeriodo()));

        for (Integer i = 0; i < lancamento.getQuantidadePeriodo(); i++) {
            Lancamento lancamentoAux = new Lancamento();

            lancamentoAux.setDescricao(lancamento.getDescricao());
            lancamentoAux.setTipoLancamento(lancamento.getTipoLancamento());
            lancamentoAux.setValor(valorParcela);
            lancamentoAux.setDataVencimento(lancamento.getDataVencimento().plusMonths(i));
            lancamentoAux.setSituacao(lancamento.getSituacao());
            lancamentoAux.setPeriodicidade(lancamento.getPeriodicidade());
            lancamentoAux.setQuantidadePeriodo(lancamento.getQuantidadePeriodo());
            lancamentoAux.setParcela(i + 1);
            lancamentoAux.setCategoria(lancamento.getCategoria());
            lancamentoAux.setTipoPagamento(lancamento.getTipoPagamento());

            lancamentoRepository.create(lancamentoAux);
        }

        return lancamento;
    }

}
