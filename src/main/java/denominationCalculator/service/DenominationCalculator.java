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
