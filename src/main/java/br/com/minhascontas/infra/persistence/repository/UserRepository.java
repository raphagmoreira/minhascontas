package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.entity.QUser;
import br.com.minhascontas.domain.entity.User;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.domain.query.filter.UserFilter;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepository extends RepositoryAbstract<User, Long> {

    private JPAQuery<User> createQueryFind(UserFilter filter, Pageable pageable) {
        QUser qUser = QUser.user1;
        final PathBuilder<User> path = new PathBuilder<>(User.class, "user");

        final JPAQuery<User> jpaQuery = new JPAQueryFactory(entityManager)
                .select(
                        QUser.create(
                                qUser.id,
                                qUser.user
                        )
                )
                .from(qUser);

        if (Objects.nonNull(filter)) {
            if (Objects.nonNull(filter.getId())) {
                jpaQuery.where(qUser.id.eq(filter.getId()));
            }

            if (Objects.nonNull(filter.getUser())) {
                jpaQuery.where(qUser.user.eq(filter.getUser()));
            }
        }

        return orderAndPaging(jpaQuery,pageable,path);
    }

    @Override
    public Page<User> find(Filter<User> filter, Pageable pageable) {
        UserFilter userFilter = (UserFilter) filter;

        return new PageImpl<>(
                createQueryFind(userFilter,pageable).fetch(),
                pageable,
                count(userFilter));
    }

    @Override
    public long count(Filter<User> filter) {
        return createQueryFind((UserFilter) filter,null).fetchCount();
    }

    public User findByUser(String user) {
        QUser qUser = QUser.user1;

        return new JPAQueryFactory(entityManager)
                .select(
                        qUser
                )
                .from(qUser)
                .where(qUser.user.eq(user))
                .fetchOne();
    }
}
