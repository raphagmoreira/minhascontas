package br.com.minhascontas.application.resource;

import br.com.minhascontas.configuration.annotation.QueryParam;
import br.com.minhascontas.domain.dto.RestResponseDTO;
import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.service.CategoriaService;
import br.com.minhascontas.domain.query.filter.CategoriaFilter;
import br.com.minhascontas.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/categorias")
@Api("Recurso para o gerenciamento de Categorias.")
public class CategoriaResource extends ControllerAbstract<Categoria, Long> {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaResource(CategoriaService categoriaService) {
        super(categoriaService);
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista paginada de Categorias.",
            notes = "O objeto retornado contém informações de paginação.",
            response = Categoria[].class)
    public ResponseEntity<RestResponseDTO<Page<Categoria>>> find(@QueryParam CategoriaFilter categoriaFilter,
                                                                 @QueryParam Pageable pageable) {
        return new RestResponseDTO<>(
                categoriaService.find(categoriaFilter, pageable),
                Util.getMessageApplication("entity.read"),
                HttpStatus.OK
        ).returnEntity();
    }

}
