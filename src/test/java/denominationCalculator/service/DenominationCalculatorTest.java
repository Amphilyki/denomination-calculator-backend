package denominationCalculator.service;

import denominationCalculator.utils.DenominationsUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DenominationCalculator.class)
public class DenominationCalculatorTest {

    @Autowired
    DenominationCalculator underTest;


    @Test
    @DisplayName("Denomination for 1000 Euros.")
    public void test_getDenominationForAmount_1000() {
        Map<BigDecimal, Integer> result = underTest.getDenominationForAmount(new BigDecimal(1000));

        assertEquals(1, result.size());
        assertEquals(5, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
    }

    @Test
    @DisplayName("Denomination for 900 Euros.")
    public void test_getDenominationForAmount_900() {
        Map<BigDecimal, Integer> result = underTest.getDenominationForAmount(new BigDecimal(900));

        assertEquals(2, result.size());
        assertEquals(4, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(1, result.get(DenominationsUtil.ONE_HUNDRED_EUROS));
    }

    @Test
    @DisplayName("Denomination for 223 Euros.")
    public void test_getDenominationForAmount_223() {
        Map<BigDecimal, Integer> result = underTest.getDenominationForAmount(new BigDecimal(223));

        assertEquals(4, result.size());
        assertEquals(1, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TWENTY_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TWO_EUROS));
        assertEquals(1, result.get(DenominationsUtil.ONE_EURO));
    }


    @Test
    @DisplayName("Denomination for 1257.76 Euros.")
    public void test_getDenominationForAmount_1257_76() {
        Map<BigDecimal, Integer> result = underTest.getDenominationForAmount(new BigDecimal("1257.76"));

        assertEquals(8, result.size());
        assertEquals(6, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(1, result.get(DenominationsUtil.FIFTY_EUROS));
        assertEquals(1, result.get(DenominationsUtil.FIVE_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TWO_EUROS));
        assertEquals(1, result.get(DenominationsUtil.FIFTY_CENTS));
        assertEquals(1, result.get(DenominationsUtil.TWENTY_CENTS));
        assertEquals(1, result.get(DenominationsUtil.FIVE_CENTS));
        assertEquals(1, result.get(DenominationsUtil.ONE_CENT));
    }

}