package br.com.minhascontas.application.resource;

import br.com.minhascontas.domain.dto.RestResponseDTO;
import br.com.minhascontas.domain.service.ServiceAbstract;
import br.com.minhascontas.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Implementação da abstração da camada de Controller
 *
 * @param <T>
 * @param <I>
 * @author raphael.moreira
 */
public abstract class ControllerAbstract<T, I> implements ControllerInterface<T, I> {

    private ServiceAbstract<T, I> serviceAbstract;

    @Autowired
    public ControllerAbstract(ServiceAbstract<T, I> serviceAbstract) {
        this.serviceAbstract = serviceAbstract;
    }

    @Override
    public ResponseEntity<RestResponseDTO<T>> findById(I id) {

        return new RestResponseDTO<>(
                serviceAbstract.findById(id),
                Util.getMessageApplication("entity.read"),
                HttpStatus.OK
        ).returnEntity();

    }

    @Override
    public ResponseEntity<RestResponseDTO<T>> create(@RequestBody @Valid T entity) {

        return new RestResponseDTO<>(
                serviceAbstract.create(entity),
                Util.getMessageApplication("entity.created"),
                HttpStatus.CREATED
        ).returnEntity();

    }

    @Override
    public ResponseEntity<RestResponseDTO<T>> update(T entity) {

        return new RestResponseDTO<>(
                serviceAbstract.update(entity),
                Util.getMessageApplication("entity.updated"),
                HttpStatus.CREATED
        ).returnEntity();

    }

    @Override
    public ResponseEntity<RestResponseDTO<T>> delete(I id) {

        serviceAbstract.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
