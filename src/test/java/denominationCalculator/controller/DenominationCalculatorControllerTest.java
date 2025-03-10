package denominationCalculator.controller;

import denominationCalculator.service.DenominationCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DenominationCalculatorController.class)
class DenominationCalculatorControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private DenominationCalculator service;

    @Test
    void testGetDenominationsForAmount() throws Exception {
        mvc.perform(get("/euro-denomination-calculator/amount/1234"))
                .andExpect(status().isOk());
        Mockito.verify(service, times(1)).getDenominationForAmount(new BigDecimal(1234));
    }

    @Test
    void testGetDenominationDifferenceForTwoAmount() throws Exception {
        mvc.perform(get("/euro-denomination-calculator/denominations-difference")
                        .param("newAmount", "123")
                        .param("oldAmount", "456"))
                .andExpect(status().isOk());
        Mockito.verify(service, times(1)).
                getDenominationDifferenceForTwoAmounts(new BigDecimal(123), new BigDecimal(456));
    }

}