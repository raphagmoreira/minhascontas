package br.com.minhascontas.infra.persistence.repository.jpa;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.QCategoria;
import br.com.minhascontas.infra.persistence.query.Query;
import br.com.minhascontas.infra.persistence.query.impl.CategoriaFilter;
import br.com.minhascontas.infra.persistence.repository.CategoriaRepository;
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
public class CategoriaJpaRepository extends AbstractJpaRepository<Categoria, Long> implements CategoriaRepository {
    @Override
    public List<Categoria> find(Query<Categoria> query) {
        return this.createQuery(query, true).fetch();
    }

    private JPAQuery<Categoria> createQuery(Query<Categoria> query, Boolean sortAndPaging) {
        QCategoria qCategoria = QCategoria.categoria;
        final PathBuilder<Categoria> path = new PathBuilder<>(Categoria.class, "categoria");

        final JPAQuery<Categoria> jpaQuery = new JPAQueryFactory(entityManager)
                .select(
                        QCategoria.create(
                                qCategoria.id,
                                qCategoria.descricao
                        )
                )
                .from(qCategoria);

        if (Objects.nonNull(query)) {
            if (query.getFilter() != null && query.getFilter() instanceof CategoriaFilter) {
                final CategoriaFilter filter = (CategoriaFilter) query.getFilter();

                if (Objects.nonNull(filter.getId())) {
                    jpaQuery.where(qCategoria.id.eq(filter.getId()));
                }

                if (Objects.nonNull(filter.getDescricao())) {
                    jpaQuery.where(qCategoria.descricao.likeIgnoreCase("%" + filter.getDescricao() + "%"));
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
    public long count(Query<Categoria> query) {
        return this.createQuery(query, false).fetchCount();
    }
}
