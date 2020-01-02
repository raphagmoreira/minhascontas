package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.entity.QTipoPagamento;
import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.domain.query.filter.TipoPagamentoFilter;
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
public class TipoPagamentoRepository extends RepositoryAbstract<TipoPagamento, Long> {

    @Override
    public Page<TipoPagamento> find(Filter<TipoPagamento> filter, Pageable pageable) {
        TipoPagamentoFilter tipoPagamentoFilter = (TipoPagamentoFilter) filter;

        return new PageImpl<>(
                createQueryFind(tipoPagamentoFilter,pageable).fetch(),
                pageable,
                count(tipoPagamentoFilter));
    }

    private JPAQuery<TipoPagamento> createQueryFind(TipoPagamentoFilter filter, Pageable pageable) {
        QTipoPagamento qTipoPagamento = QTipoPagamento.tipoPagamento;
        final PathBuilder<TipoPagamento> path = new PathBuilder<>(TipoPagamento.class, "tipoPagamento");

        final JPAQuery<TipoPagamento> jpaQuery = new JPAQueryFactory(entityManager)
                .select(
                        QTipoPagamento.create(
                                qTipoPagamento.id,
                                qTipoPagamento.descricao
                        )
                )
                .from(qTipoPagamento);

        if (Objects.nonNull(filter)) {
            if (Objects.nonNull(filter.getId())) {
                jpaQuery.where(qTipoPagamento.id.eq(filter.getId()));
            }

            if (Objects.nonNull(filter.getDescricao())) {
                jpaQuery.where(qTipoPagamento.descricao.likeIgnoreCase("%" + filter.getDescricao() + "%"));
            }
        }

        return orderAndPaging(jpaQuery,pageable,path);
    }

    @Override
    public long count(Filter<TipoPagamento> filter) {
        return createQueryFind((TipoPagamentoFilter) filter,null).fetchCount();
    }
}
