package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.entity.*;
import br.com.minhascontas.domain.query.filter.CategoriaFilter;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.domain.query.filter.LancamentoFilter;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * Created by Rapha on 31/12/2019.
 */
@Repository
public class LancamentoRepository extends RepositoryAbstract<Lancamento, Long> {

    @Override
    public Page<Lancamento> find(Filter<Lancamento> filter, Pageable pageable) {
        LancamentoFilter lancamentoFilter = (LancamentoFilter) filter;

        return new PageImpl<>(
                createQueryFind(lancamentoFilter,pageable).fetch(),
                pageable,
                count(lancamentoFilter));
    }

    private JPAQuery<Lancamento> createQueryFind(LancamentoFilter filter, Pageable pageable) {
        QLancamento qLancamento = QLancamento.lancamento;
        QCategoria qCategoria = QCategoria.categoria;
        QTipoPagamento qTipoPagamento = QTipoPagamento.tipoPagamento;

        final PathBuilder<Lancamento> path = new PathBuilder<>(Lancamento.class, "lancamento");

        final JPAQuery<Lancamento> jpaQuery = new JPAQueryFactory(entityManager)
                .select(
                        QLancamento.create(
                                qLancamento.id,
                                qLancamento.descricao,
                                qLancamento.valor,
                                qLancamento.dataVencimento,
                                qLancamento.situacao,
                                qLancamento.periodicidade,
                                qLancamento.quantidadePeriodo,
                                QCategoria.create(
                                        qCategoria.id,
                                        qCategoria.descricao
                                ),
                                QTipoPagamento.create(
                                        qTipoPagamento.id,
                                        qTipoPagamento.descricao
                                )
                        )
                )
                .from(qLancamento)
                .join(qLancamento.categoria, qCategoria)
                .join(qLancamento.tipoPagamento, qTipoPagamento);

        if (Objects.nonNull(filter)) {
            if (Objects.nonNull(filter.getId())) {
                jpaQuery.where(qLancamento.id.eq(filter.getId()));
            }

            if (Objects.nonNull(filter.getDescricao())) {
                jpaQuery.where(qLancamento.descricao.likeIgnoreCase("%" + filter.getDescricao() + "%"));
            }

            if (Objects.nonNull(filter.getValor())) {
                jpaQuery.where(qLancamento.valor.eq(filter.getValor()));
            }

            if (Objects.nonNull(filter.getDataVencimento())) {
                jpaQuery.where(qLancamento.dataVencimento.eq(filter.getDataVencimento()));
            }

            if (Objects.nonNull(filter.getSituacao())) {
                jpaQuery.where(qLancamento.situacao.eq(filter.getSituacao()));
            }

            if (Objects.nonNull(filter.getPeriodicidade())) {
                jpaQuery.where(qLancamento.periodicidade.eq(filter.getPeriodicidade()));
            }

            if (Objects.nonNull(filter.getQuantidadePeriodo())) {
                jpaQuery.where(qLancamento.quantidadePeriodo.eq(filter.getQuantidadePeriodo()));
            }

            if (Objects.nonNull(filter.getIdCategoria())) {
                jpaQuery.where(qCategoria.id.eq(filter.getIdCategoria()));
            }

            if (Objects.nonNull(filter.getIdTipoPagamento())) {
                jpaQuery.where(qTipoPagamento.id.eq(filter.getIdTipoPagamento()));
            }
        }

        return orderAndPaging(jpaQuery,pageable,path);
    }

    @Override
    public long count(Filter<Lancamento> filter) {
        return createQueryFind((LancamentoFilter) filter,null).fetchCount();
    }
}
