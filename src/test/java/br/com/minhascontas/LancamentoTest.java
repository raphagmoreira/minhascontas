package br.com.minhascontas;

import br.com.minhascontas.application.resource.LancamentoResource;
import br.com.minhascontas.domain.entity.Categoria;
import br.com.minhascontas.domain.entity.Lancamento;
import br.com.minhascontas.domain.entity.TipoPagamento;
import br.com.minhascontas.domain.enums.EnumPeriodicidade;
import br.com.minhascontas.domain.enums.EnumSituacaoLancamento;
import br.com.minhascontas.domain.enums.EnumTipoLancamento;
import br.com.minhascontas.domain.service.LancamentoService;
import br.com.minhascontas.infra.persistence.repository.LancamentoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LancamentoResource.class)
//@MockBean(LancamentoService.class)
//@MockBeans({ @MockBean(LancamentoService.class), @MockBean(LancamentoRepository.class) })
public class LancamentoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LancamentoService lancamentoService;

    /*@Mock
    private LancamentoRepository lancamentoRepository;*/

    /*@Test
    public void LancamentoTest() {
        List<Lancamento> lista = new ArrayList<>();
        Lancamento lancamento = new Lancamento();

        lancamento.setDescricao("TESTE");
        lancamento.setTipoLancamento(EnumTipoLancamento.DESPESA);
        lancamento.setValor(new BigDecimal(10.30));
        lancamento.setDataVencimento(LocalDate.now());
        lancamento.setSituacao(EnumSituacaoLancamento.PENDENTE);
        lancamento.setPeriodicidade(EnumPeriodicidade.MENSAL);
        lancamento.setQuantidadePeriodo(1);
        lancamento.setParcela(1);
        lancamento.setCategoria(new Categoria(1L));
        lancamento.setTipoPagamento(new TipoPagamento(1L));

        Mockito.when(lancamentoService.createLancamento(lancamento)).thenReturn(lancamento);

        Assertions.assertThat(lancamento).isNotNull();

        Mockito.verify(lancamentoService).createLancamento(lancamento);

        *//*lista.add(lancamentoService.createLancamento(lancamento));

        Assertions.assertThat(lista)
            .isNotEmpty()
            .isNotNull();*//*
    }*/

    @Test
    public void ControllerTest() throws Exception {
        Lancamento lancamento = new Lancamento();

        lancamento.setDescricao("TESTE");
        lancamento.setTipoLancamento(EnumTipoLancamento.DESPESA);
        lancamento.setValor(new BigDecimal(10.30));
        lancamento.setDataVencimento(LocalDate.now());
        lancamento.setSituacao(EnumSituacaoLancamento.PENDENTE);
        lancamento.setPeriodicidade(EnumPeriodicidade.MENSAL);
        lancamento.setQuantidadePeriodo(1);
        lancamento.setParcela(1);
        lancamento.setCategoria(new Categoria(1L));
        lancamento.setTipoPagamento(new TipoPagamento(1L));

        Mockito.when(lancamentoService.findById(1L))
                .thenReturn(lancamento);

        mockMvc.perform(get("/api/lancamentos/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
