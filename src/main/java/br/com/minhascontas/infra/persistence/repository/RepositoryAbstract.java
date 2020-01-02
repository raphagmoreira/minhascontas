package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.exception.MinhasContasException;
import br.com.minhascontas.infra.persistence.repository.RepositoryInterface;
import br.com.minhascontas.util.Direction;
import br.com.minhascontas.util.Util;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementação da abstração da camada de Repository
 *
 * @author felipe costa
 * @version 1.0.0
 * @since 01/11/2019
 */
public abstract class RepositoryAbstract<T, I> implements RepositoryInterface<T, I> {

    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Retorna se o ID da entidade é gerado automaticamente.
     *
     * @return
     */
    private boolean isGeneratedId() {
        return Arrays.stream(entityClass.getDeclaredFields()).anyMatch(field -> field.isAnnotationPresent(GeneratedValue.class));
    }

    /**
     * Consulta uma entidade através do ID.
     *
     * @param id
     * @return
     */
    @Override
    public T findById(I id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Insere uma nova entidade.
     *
     * @param entity
     */
    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    /**
     * Altera uma entidade.
     *
     * @param entity
     */
    @Override
    public T update(T entity) {
        Optional<I> id = getIdentifier(entity);

        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    /**
     * Remove uma entidade.
     *
     * @param entity
     */
    @Override
    public void remove(T entity) {
        removeById(getIdentifier(entity)
                .orElseThrow(() -> new MinhasContasException(
                        Util.getMessageValidation("retorno.consulta.exclusao"),
                        HttpStatus.OK)
                )
        );
    }

    /**
     * Remove uma entidade através do ID.
     *
     * @param id
     */
    @Override
    public void removeById(I id) {
        entityManager.remove(Optional.ofNullable(entityManager.find(entityClass, id))
                .orElseThrow(() -> new MinhasContasException(
                        Util.getMessageValidation("retorno.consulta.exclusao"),
                        HttpStatus.OK)
                )
        );

        entityManager.flush();
    }

    /**
     * Retorna o ID da entidade.
     *
     * @param entity
     * @return
     */
    protected Optional<I> getIdentifier(T entity) {
        return Optional.ofNullable((I) entityManager
                .getEntityManagerFactory()
                .getPersistenceUnitUtil()
                .getIdentifier(entity));
    }

    @Override
    public boolean existsById(I id) {
        return Objects.nonNull(entityManager.find(entityClass, id));
    }

    @Override
    public JPAQuery orderAndPaging(JPAQuery jpaQuery, Pageable pageable, PathBuilder path) {

        if (Objects.nonNull(pageable) && pageable.getSort().isSorted()) {
            jpaQuery.orderBy(
                    pageable.getSort().ascending().isSorted() ? path.getString(Direction.ASC.toString()).asc() : path.getString(Direction.DESC.toString()).desc()
            );

            jpaQuery.offset(pageable.getOffset());
            jpaQuery.limit(pageable.getPageSize());
        }

        return jpaQuery;
    }

}
