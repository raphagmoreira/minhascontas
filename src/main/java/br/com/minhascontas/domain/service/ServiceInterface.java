package br.com.minhascontas.domain.service;

/**
 * Interface que designa os métodos básicos da camada de service
 *
 * @param <T>
 * @param <I>
 * @author neto.diegues
 * @version 1.0.0
 * @since 31/10/2019
 */
public interface ServiceInterface<T, I> {

    T findById(I id);

    boolean existsById(I id);

    T create(T model);

    T update(T model);

    void deleteById(I id);

}
