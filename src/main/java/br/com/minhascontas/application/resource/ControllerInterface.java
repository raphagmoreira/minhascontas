package br.com.minhascontas.application.resource;


import br.com.minhascontas.domain.dto.RestResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Interface que designa os métodos básicos das requisições a camada de controller
 *
 * @param <T>
 * @param <I>
 * @author henrique.oliveira
 * @version 1.0.0
 * @since 31/10/2019
 */
public interface ControllerInterface<T, I> {

    @GetMapping("/{id}")
    ResponseEntity<RestResponseDTO<T>> findById(@PathVariable("id") I id);

    @PostMapping
    ResponseEntity<RestResponseDTO<T>> create(@RequestBody T entity);

    @PutMapping
    ResponseEntity<RestResponseDTO<T>> update(@RequestBody T entity);

    @DeleteMapping("/{id}")
    ResponseEntity<RestResponseDTO<T>> delete(@PathVariable("id") I id);
}
