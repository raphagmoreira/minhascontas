package br.com.minhascontas.application.resource;

import br.com.minhascontas.configuration.annotation.QueryParam;
import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.domain.service.CategoriaService;
import br.com.minhascontas.domain.service.TipoPagamentoService;
import br.com.minhascontas.infra.persistence.query.Sorter;
import br.com.minhascontas.infra.persistence.query.impl.CategoriaFilter;
import br.com.minhascontas.infra.persistence.query.impl.TipoPagamentoFilter;
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
@RequestMapping("/api/tipopagamentos")
public class TipoPagamentoResource {

    @Autowired
    private TipoPagamentoService tipoPagamentoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoPagamento>> find(@QueryParam TipoPagamentoFilter tipoPagamentoFilter,
                                                    @RequestParam(value = "sortBy", required = false) String sortProperty,
                                                    @RequestParam(value = "sortDirection", required = false) Sorter.Direction sortDirection,
                                                    @RequestParam(value = "page", required = false) Long page) {
        return ResponseEntity.ok(
                tipoPagamentoService.find(tipoPagamentoFilter, sortProperty, sortDirection, page)
        );
    }

    @GetMapping(value = "/{idTipoPagamento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoPagamento> findById(@PathVariable("idTipoPagamento") final Long idTipoPagamento) {
        return ResponseEntity.ok(tipoPagamentoService.findById(idTipoPagamento));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody TipoPagamento tipoPagamento) {
        tipoPagamentoService.create(tipoPagamento);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(tipoPagamento.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody TipoPagamento tipoPagamento) {
        tipoPagamentoService.update(tipoPagamento);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{idTipoPagamento}")
    public ResponseEntity<Void> remove(@PathVariable("idTipoPagamento") Long idTipoPagamento) {
        tipoPagamentoService.remove(idTipoPagamento);
        return ResponseEntity.noContent().build();
    }

}
