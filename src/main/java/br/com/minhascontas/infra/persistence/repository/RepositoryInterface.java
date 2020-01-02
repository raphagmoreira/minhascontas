package br.com.minhascontas.infra.persistence.repository;

import br.com.minhascontas.domain.query.filter.Filter;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface que designa os métodos básicos das requisições a camada de Repository
 *
 * @author raphael.moreira
 */
public interface RepositoryInterface<T,I>  {
    /**
     * Consulta as entidades.
     * @param filter
     * @param pageable
     * @return
     */
    Page<T> find(Filter<T> filter, Pageable pageable);

    /**
     * Consulta o total de entidades.
     *
     * @param filter
     * @return
     */
    long count(Filter<T> filter);

    /**
     * Verifica de existe.
     *
     * @param id
     * @return
     */
    boolean existsById(I id);

    /**
     * Consulta uma entidade através do ID.
     *
     * @param id
     * @return
     */
    T findById(I id);

    /**
     * Insere uma nova entidade.
     *update
     * @param entity
     */
    T create(T entity);

    /**
     * Altera uma entidade.
     *
     * @param entity
     */
    T update(T entity);

    /**
     * Remove uma entidade.
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * Remove uma entidade através do ID.
     *
     * @param id
     */
    void removeById(I id);

    /**
     * Remove uma entidade através do ID.
     *
     * @param jpaQuery
     * @param pageable
     * @param path
     */
    JPAQuery<T> orderAndPaging(JPAQuery jpaQuery, Pageable pageable, PathBuilder path);

}
