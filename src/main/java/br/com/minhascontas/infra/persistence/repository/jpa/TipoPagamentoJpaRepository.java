package br.com.minhascontas.infra.persistence.repository.jpa;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.QCategoria;
import br.com.minhascontas.domain.entity.QTipoPagamento;
import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.infra.persistence.query.Query;
import br.com.minhascontas.infra.persistence.query.impl.CategoriaFilter;
import br.com.minhascontas.infra.persistence.query.impl.TipoPagamentoFilter;
import br.com.minhascontas.infra.persistence.repository.CategoriaRepository;
import br.com.minhascontas.infra.persistence.repository.TipoPagamentoRepository;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by Rapha on 31/12/2019.
 */
@Repository
public class TipoPagamentoJpaRepository extends AbstractJpaRepository<TipoPagamento, Long> implements TipoPagamentoRepository {
    @Override
    public List<TipoPagamento> find(Query<TipoPagamento> query) {
        return this.createQuery(query, true).fetch();
    }

    private JPAQuery<TipoPagamento> createQuery(Query<TipoPagamento> query, Boolean sortAndPaging) {
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

        if (Objects.nonNull(query)) {
            if (query.getFilter() != null && query.getFilter() instanceof TipoPagamentoFilter) {
                final TipoPagamentoFilter filter = (TipoPagamentoFilter) query.getFilter();

                if (Objects.nonNull(filter.getId())) {
                    jpaQuery.where(qTipoPagamento.id.eq(filter.getId()));
                }

                if (Objects.nonNull(filter.getDescricao())) {
                    jpaQuery.where(qTipoPagamento.descricao.likeIgnoreCase("%" + filter.getDescricao() + "%"));
                }
            }
        }

        //Utilizado para paginar
        if (sortAndPaging) {
            if (query.getSorter() != null && StringUtils.isNotBlank(query.getSorter().getProperty())) {
                if (query.getSorter().isDesc()) {
                    jpaQuery.orderBy(path.getString(query.getSorter().getProperty()).desc());
                } else {
                    jpaQuery.orderBy(path.getString(query.getSorter().getProperty()).asc());
                }
            } else {
                jpaQuery.orderBy(path.getString("id").asc());
            }

            // page
            jpaQuery.offset(query.getPage());

            // limit
            jpaQuery.limit(query.getLimit());
        }

        return jpaQuery;
    }

    @Override
    public long count(Query<TipoPagamento> query) {
        return this.createQuery(query, false).fetchCount();
    }
}
