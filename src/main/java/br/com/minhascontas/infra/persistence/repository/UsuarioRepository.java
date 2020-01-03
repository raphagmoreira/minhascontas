package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.entity.QUsuario;
import br.com.minhascontas.domain.entity.Usuario;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.domain.query.filter.UsuarioFilter;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UsuarioRepository extends RepositoryAbstract<Usuario, Long> {

    private JPAQuery<Usuario> createQueryFind(UsuarioFilter filter, Pageable pageable) {
        QUsuario qUsuario = QUsuario.usuario;
        final PathBuilder<Usuario> path = new PathBuilder<>(Usuario.class, "usuario");

        final JPAQuery<Usuario> jpaQuery = new JPAQueryFactory(entityManager)
                .select(
                        QUsuario.create(
                                qUsuario.id,
                                qUsuario.user
                        )
                )
                .from(qUsuario);

        if (Objects.nonNull(filter)) {
            if (Objects.nonNull(filter.getId())) {
                jpaQuery.where(qUsuario.id.eq(filter.getId()));
            }

            if (Objects.nonNull(filter.getUser())) {
                jpaQuery.where(qUsuario.user.eq(filter.getUser()));
            }
        }

        return orderAndPaging(jpaQuery,pageable,path);
    }

    @Override
    public Page<Usuario> find(Filter<Usuario> filter, Pageable pageable) {
        UsuarioFilter usuarioFilter = (UsuarioFilter) filter;

        return new PageImpl<>(
                createQueryFind(usuarioFilter,pageable).fetch(),
                pageable,
                count(usuarioFilter));
    }

    @Override
    public long count(Filter<Usuario> filter) {
        return createQueryFind((UsuarioFilter) filter,null).fetchCount();
    }

    public Usuario findByUser(String user) {
        QUsuario qUsuario = QUsuario.usuario;

        return new JPAQueryFactory(entityManager)
                .select(
                        qUsuario
                )
                .from(qUsuario)
                .where(qUsuario.user.eq(user))
                .fetchOne();
    }
}
