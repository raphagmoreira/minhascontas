package br.com.minhascontas.domain.service;

import br.com.minhascontas.infra.persistence.repository.RepositoryAbstract;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Implementação da abstração da camada de service
 *
 * @param <T>
 * @param <I>
 * @author raphael.moreira
 */
public abstract class ServiceAbstract<T, I> implements ServiceInterface<T, I> {

    RepositoryAbstract<T,I> repositoryAbstract;

    @Autowired
    public ServiceAbstract(RepositoryAbstract<T,I> repositoryAbstract) {
        this.repositoryAbstract = repositoryAbstract;
    }

    @Override
    public T findById(I id) {
        return repositoryAbstract.findById(id);
    }

    @Override
    public boolean existsById(I id) {
        return repositoryAbstract.existsById(id);
    }

    @Override
    @Transactional
    public T create(T model) {
        return repositoryAbstract.create(model);
    }

    @Override
    @Transactional
    public T update(T model) {
      return repositoryAbstract.update(model);
    }

    @Override
    @Transactional
    public void deleteById(I id) {
        repositoryAbstract.removeById(id);
    }
}

