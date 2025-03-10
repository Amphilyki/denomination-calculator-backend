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

    @Test
    @DisplayName("Denomination difference for 300 and 88.")
    public void test_getDenominationDifferenceForTwoAmounts_300_and_88() {
        Map<BigDecimal, Integer> result =
                underTest.getDenominationDifferenceForTwoAmounts(
                        new BigDecimal(300), new BigDecimal(88));

        assertEquals(8, result.size());
        assertEquals(1, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(1, result.get(DenominationsUtil.ONE_HUNDRED_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.FIFTY_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.TWENTY_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.TEN_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.FIVE_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.TWO_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.ONE_EURO));
    }


    @Test
    @DisplayName("Denomination difference for 88 and 300.")
    public void test_getDenominationDifferenceForTwoAmounts_88_and_300() {
        Map<BigDecimal, Integer> result =
                underTest.getDenominationDifferenceForTwoAmounts(
                        new BigDecimal(88), new BigDecimal(300));

        assertEquals(8, result.size());
        assertEquals(-1, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.ONE_HUNDRED_EUROS));
        assertEquals(1, result.get(DenominationsUtil.FIFTY_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TWENTY_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TEN_EUROS));
        assertEquals(1, result.get(DenominationsUtil.FIVE_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TWO_EUROS));
        assertEquals(1, result.get(DenominationsUtil.ONE_EURO));
    }

    @Test
    @DisplayName("Denomination difference for 1230 and 35.89.")
    public void test_getDenominationDifferenceForTwoAmounts_1230_and_35_89() {
        Map<BigDecimal, Integer> result =
                underTest.getDenominationDifferenceForTwoAmounts(
                        new BigDecimal(1230), new BigDecimal("35.89"));

        assertEquals(9, result.size());
        assertEquals(6, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(0, result.get(DenominationsUtil.TWENTY_EUROS));
        assertEquals(0, result.get(DenominationsUtil.TEN_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.FIVE_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.FIVE_CENTS));
        assertEquals(-1, result.get(DenominationsUtil.TWENTY_CENTS));
        assertEquals(-1, result.get(DenominationsUtil.TEN_CENTS));
        assertEquals(-1, result.get(DenominationsUtil.FIVE_CENTS));
        assertEquals(-2, result.get(DenominationsUtil.TWO_CENTS));
    }

    @Test
    @DisplayName("Denomination difference for 4567.69 and 98.")
    public void test_getDenominationDifferenceForTwoAmounts_4567_69_and_98() {
        Map<BigDecimal, Integer> result =
                underTest.getDenominationDifferenceForTwoAmounts(
                        new BigDecimal("4567.69"), new BigDecimal(98));

        assertEquals(12, result.size());
        assertEquals(22, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(1, result.get(DenominationsUtil.ONE_HUNDRED_EUROS));
        assertEquals(0, result.get(DenominationsUtil.FIFTY_EUROS));
        assertEquals(-2, result.get(DenominationsUtil.TWENTY_EUROS));
        assertEquals(1, result.get(DenominationsUtil.TEN_EUROS));
        assertEquals(0, result.get(DenominationsUtil.FIVE_EUROS));
        assertEquals(0, result.get(DenominationsUtil.TWO_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.ONE_EURO));
        assertEquals(1, result.get(DenominationsUtil.FIFTY_CENTS));
        assertEquals(1, result.get(DenominationsUtil.TEN_CENTS));
        assertEquals(1, result.get(DenominationsUtil.FIVE_CENTS));
        assertEquals(2, result.get(DenominationsUtil.TWO_CENTS));
    }


    @Test
    @DisplayName("Denomination difference for 340.75 and 76598.62")
    public void test_getDenominationDifferenceForTwoAmounts_340_75_and_76598_62() {
        Map<BigDecimal, Integer> result =
                underTest.getDenominationDifferenceForTwoAmounts(
                        new BigDecimal("340.75"), new BigDecimal("76598.62"));

        assertEquals(12, result.size());
        assertEquals(-381, result.get(DenominationsUtil.TW0_HUNDRED_EUROS));
        assertEquals(0, result.get(DenominationsUtil.ONE_HUNDRED_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.FIFTY_EUROS));
        assertEquals(0, result.get(DenominationsUtil.TWENTY_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.FIVE_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.TWO_EUROS));
        assertEquals(-1, result.get(DenominationsUtil.ONE_EURO));
        assertEquals(0, result.get(DenominationsUtil.FIFTY_CENTS));
        assertEquals(1, result.get(DenominationsUtil.TWENTY_CENTS));
        assertEquals(-1, result.get(DenominationsUtil.TEN_CENTS));
        assertEquals(1, result.get(DenominationsUtil.FIVE_CENTS));
        assertEquals(-1, result.get(DenominationsUtil.TWO_CENTS));
    }

}