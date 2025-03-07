package denominationCalculator.service;

import denominationCalculator.utils.DenominationsUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DenominationCalculator {


    public Map<BigDecimal, Integer> getDenominationForAmount(BigDecimal amount) {
        Map<BigDecimal, Integer> resultAsMap = new HashMap<>();

        for (BigDecimal currentDenomination : DenominationsUtil.DENOMINATIONS) {
            if (amount.compareTo(currentDenomination) != -1) {
                //>=
                int denominationTimes = 0;
                while (amount.compareTo(currentDenomination) != -1) {
                    denominationTimes++;
                    resultAsMap.put(currentDenomination, denominationTimes);
                    amount = amount.subtract(currentDenomination);
                }
            }
        }
        TreeMap<BigDecimal, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(resultAsMap);
        return sorted;
    }

    public Map<BigDecimal, Integer> getDenominationDifferenceForAmounts(BigDecimal newAmount, BigDecimal oldAmount) {
        Map<BigDecimal, Integer> resultAsMap = new HashMap<>();
        return resultAsMap;
    }

}
