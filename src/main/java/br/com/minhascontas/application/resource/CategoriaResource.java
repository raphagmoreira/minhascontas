package br.com.minhascontas.application.resource;

import br.com.minhascontas.configuration.annotation.QueryParam;
import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.service.CategoriaService;
import br.com.minhascontas.infra.persistence.query.Sorter;
import br.com.minhascontas.infra.persistence.query.impl.CategoriaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by Rapha on 31/12/2019.
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categoria>> find(@QueryParam CategoriaFilter categoriaFilter,
                                                @RequestParam(value = "sortBy", required = false) String sortProperty,
                                                @RequestParam(value = "sortDirection", required = false) Sorter.Direction sortDirection,
                                                @RequestParam(value = "page", required = false) Long page) {
        return ResponseEntity.ok(
                categoriaService.find(categoriaFilter, sortProperty, sortDirection, page)
        );
    }

    @GetMapping(value = "/{idCategoria}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> findById(@PathVariable("idPessoa") final Long idCategoria) {
        return ResponseEntity.ok(categoriaService.findById(idCategoria));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Categoria categoria) {
        categoriaService.create(categoria);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody Categoria categoria) {
        categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{idCategoria}")
    public ResponseEntity<Void> remove(@PathVariable("idCategoria") Long idCategoria) {
        categoriaService.remove(idCategoria);
        return ResponseEntity.noContent().build();
    }

}
