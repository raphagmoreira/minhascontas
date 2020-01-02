package br.com.minhascontas.application.resource;

import br.com.minhascontas.configuration.annotation.QueryParam;
import br.com.minhascontas.domain.dto.RestResponseDTO;
import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.Lancamento;
import br.com.minhascontas.domain.query.filter.CategoriaFilter;
import br.com.minhascontas.domain.query.filter.LancamentoFilter;
import br.com.minhascontas.domain.service.CategoriaService;
import br.com.minhascontas.domain.service.LancamentoService;
import br.com.minhascontas.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rapha on 31/12/2019.
 */
@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource extends ControllerAbstract<Lancamento, Long> {

    private LancamentoService lancamentoService;

    @Autowired
    public LancamentoResource(LancamentoService lancamentoService) {
        super(lancamentoService);
        this.lancamentoService = lancamentoService;
    }

    @GetMapping
    public ResponseEntity<RestResponseDTO<Page<Lancamento>>> find(@QueryParam LancamentoFilter lancamentoFilter,
                                                                 Pageable pageable) {
        return new RestResponseDTO<>(
                lancamentoService.find(lancamentoFilter, pageable),
                Util.getMessageApplication("entity.read"),
                HttpStatus.OK
        ).returnEntity();
    }

}
