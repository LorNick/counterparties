package ru.krivko.controiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import ru.krivko.entity.Counterparty;
import ru.krivko.service.CounterpartyService;

/**
 * Тестируем web контроллер с помощью Spring Mock-MVC тестов, без запуска сервера
 *
 * <br>данный тест сгенерирован автоматически, плагином Diffblue Cover
 */
@ContextConfiguration(classes = {CounterpartyWebController.class})
@ExtendWith(SpringExtension.class)
class CounterpartyWebControllerTest {
    @MockBean
    private CounterpartyService counterpartyService;

    @Autowired
    private CounterpartyWebController counterpartyWebController;

    @Test
    void testCounterpartiesPage() {
        CounterpartyService counterpartyService = mock(CounterpartyService.class);
        when(counterpartyService.readAll()).thenReturn(new ArrayList<Counterparty>());
        CounterpartyWebController counterpartyWebController = new CounterpartyWebController(counterpartyService);
        assertEquals("counterparties", counterpartyWebController.counterpartiesPage(new ConcurrentModel()));
        verify(counterpartyService).readAll();
    }

    @Test
    void testCreateCounterparty() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/counterparties/new");
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("command"))
                .andExpect(MockMvcResultMatchers.view().name("counterpartyNew"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterpartyNew"));
    }

    @Test
    void testDeleteCounterparty() throws Exception {
        when(this.counterpartyService.delete(anyLong())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/counterparties/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/counterparties"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/counterparties"));
    }

    @Test
    void testNewCounterpartiesPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counterparties/new");
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("command"))
                .andExpect(MockMvcResultMatchers.view().name("counterpartyNew"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterpartyNew"));
    }

    @Test
    void testNewCounterpartiesPage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/counterparties/new");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("command"))
                .andExpect(MockMvcResultMatchers.view().name("counterpartyNew"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterpartyNew"));
    }

    @Test
    void testSearchCounterpartyBicAndAccountNumber() throws Exception {
        when(this.counterpartyService.searchBicAndAccountNumber(anyString(), anyString()))
                .thenReturn(new ArrayList<Counterparty>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counterparties/search_two")
                .param("accountNumber", "foo")
                .param("bic", "foo");
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("counterparties"))
                .andExpect(MockMvcResultMatchers.view().name("counterparties"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterparties"));
    }

    @Test
    void testSearchCounterpartyName() throws Exception {
        when(this.counterpartyService.searchName(anyString())).thenReturn(new ArrayList<Counterparty>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counterparties/search")
                .param("name", "foo");
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("counterparties"))
                .andExpect(MockMvcResultMatchers.view().name("counterparties"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterparties"));
    }

    @Test
    void testUpdateCounterparty() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/counterparties/update");
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("command"))
                .andExpect(MockMvcResultMatchers.view().name("counterpartyUpdate"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterpartyUpdate"));
    }

    @Test
    void testUpdateCounterpartyPage() throws Exception {
        Counterparty counterparty = new Counterparty();
        counterparty.setAccountNumber("Account Number");
        counterparty.setInn("Inn");
        counterparty.setId(123L);
        counterparty.setKpp("Kpp");
        counterparty.setName("Name");
        counterparty.setBic("Bic");
        Optional<Counterparty> ofResult = Optional.<Counterparty>of(counterparty);
        when(this.counterpartyService.read(anyLong())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/counterparties/update");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.counterpartyWebController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("command"))
                .andExpect(MockMvcResultMatchers.view().name("counterpartyUpdate"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("counterpartyUpdate"));
    }
}