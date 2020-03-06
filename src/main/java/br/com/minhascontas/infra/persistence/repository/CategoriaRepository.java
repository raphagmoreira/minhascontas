package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.QCategoria;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.domain.query.filter.CategoriaFilter;
import com.querydsl.core.Tuple;
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
public class CategoriaRepository extends RepositoryAbstract<Categoria, Long> {

    @Override
    public Page<Categoria> find(Filter<Categoria> filter, Pageable pageable) {
        CategoriaFilter clienteFilter = (CategoriaFilter) filter;

        return new PageImpl<>(
                createQueryFind(clienteFilter,pageable).fetch(),
                pageable,
                count(clienteFilter));
    }

    private JPAQuery<Categoria> createQueryFind(CategoriaFilter filter, Pageable pageable) {
        QCategoria qCategoria = QCategoria.categoria;
        final PathBuilder<Categoria> path = new PathBuilder<>(Categoria.class, "categoria");

        final JPAQuery<Tuple> jpaQuery = new JPAQueryFactory(entityManager)
                .select(
                        qCategoria.id,
                        qCategoria.descricao
                )
                .from(qCategoria);

        if (Objects.nonNull(filter)) {
            if (Objects.nonNull(filter.getId())) {
                jpaQuery.where(qCategoria.id.eq(filter.getId()));
            }

            if (Objects.nonNull(filter.getDescricao())) {
                jpaQuery.where(qCategoria.descricao.likeIgnoreCase("%" + filter.getDescricao() + "%"));
            }
        }

        return orderAndPaging(jpaQuery,pageable,path);
    }

    @Override
    public long count(Filter<Categoria> filter) {
        return createQueryFind((CategoriaFilter) filter,null).fetchCount();
    }
}
