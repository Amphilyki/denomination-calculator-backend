package denominationCalculator.service;

import denominationCalculator.utils.DenominationsUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DenominationCalculator {

    /**
     * Calculates denominations (notes and coins) for a given amount of money  in euro.
     * Largest possible notes or coins are taken first.
     *
     * @param amount : the amount of money
     * @return a map containing key-pair values of the denomination (key) and its quantity (value).
     * For example, the amount of 250 euro would return {"200.00": 1, "50:00": 1}.
     */
    public Map<BigDecimal, Integer> getDenominationForAmount(BigDecimal amount) {
        Map<BigDecimal, Integer> resultAsMap = new HashMap<>();

        for (BigDecimal currentDenomination : DenominationsUtil.DENOMINATIONS) {
            if (amount.compareTo(currentDenomination) != -1) {
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

    /**
     * Calculates the difference in denominations between two amounts of money in euro,
     * namely their difference in notes and coins.
     *
     * @param newAmount : the new amount
     * @param oldAmount : the old amount
     * @return a map containing key-value pairs, where key is the denomination and value the increase or reduction of it.
     * For example, {"200.00": -1} means that the new amount has a 200 euro note less,
     * and {"100.00": 1} means that it has a 100 euro note more than the previous amount.
     */
    public Map<BigDecimal, Integer> getDenominationDifferenceForTwoAmounts(BigDecimal newAmount, BigDecimal oldAmount) {
        Map<BigDecimal, Integer> resultAsMap = new HashMap<>();
        Map<BigDecimal, Integer> denominationForNewAmount = this.getDenominationForAmount(newAmount);
        Map<BigDecimal, Integer> denominationForOldAmount = this.getDenominationForAmount(oldAmount);

        denominationForNewAmount.forEach((key, value) -> {
            Integer denominationInOldAmount = denominationForOldAmount.get(key);
            if (denominationInOldAmount != null) {
                if (denominationInOldAmount > value) {
                    resultAsMap.put(key, -(denominationInOldAmount - value));
                }
                if (value >= denominationInOldAmount) {
                    resultAsMap.put(key, value - denominationInOldAmount);
                }
            } else {
                resultAsMap.put(key, +value);
            }
            denominationForOldAmount.remove(key);
        });
        if (!denominationForOldAmount.isEmpty()) {
            denominationForOldAmount.forEach((key, value) -> resultAsMap.put(key, -value));
        }
        return resultAsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
