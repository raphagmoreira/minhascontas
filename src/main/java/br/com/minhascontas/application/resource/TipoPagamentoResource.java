package br.com.minhascontas.application.resource;

import br.com.minhascontas.configuration.annotation.QueryParam;
import br.com.minhascontas.domain.dto.RestResponseDTO;
import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.domain.service.TipoPagamentoService;
import br.com.minhascontas.infra.persistence.repository.query.filter.TipoPagamentoFilter;
import br.com.minhascontas.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rapha on 31/12/2019.
 */
@RestController
@RequestMapping("/api/tipopagamentos")
public class TipoPagamentoResource  extends ControllerAbstract<TipoPagamento, Long> {

    private TipoPagamentoService tipoPagamentoService;

    @Autowired
    public TipoPagamentoResource(TipoPagamentoService tipoPagamentoService) {
        super(tipoPagamentoService);
        this.tipoPagamentoService = tipoPagamentoService;
    }

    @GetMapping
    public ResponseEntity<RestResponseDTO<Page<TipoPagamento>>> find(@QueryParam TipoPagamentoFilter tipoPagamentoFilter,
                                                                     Pageable pageable) {
        return new RestResponseDTO<>(
                tipoPagamentoService.find(tipoPagamentoFilter, pageable),
                Util.getMessageApplication("entity.read"),
                HttpStatus.OK
        ).returnEntity();
    }

}
