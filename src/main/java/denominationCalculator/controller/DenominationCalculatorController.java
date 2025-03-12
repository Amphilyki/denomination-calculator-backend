package denominationCalculator.controller;

import denominationCalculator.service.DenominationCalculator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/euro-denomination-calculator")
public class DenominationCalculatorController {

    @Autowired
    DenominationCalculator denominationCalculator;

    @GetMapping("/{amount}")
    public Map<BigDecimal, Integer> getDenominationsForAmount(@PathVariable("amount") BigDecimal amount)
            throws BadRequestException {
        if (amount.compareTo(new BigDecimal(0)) == -1) {
            throw new BadRequestException(" Amount cannot be negative number.");
        }
        return denominationCalculator.getDenominationForAmount(amount);
    }

    @GetMapping("/{newAmount}/difference-from/{oldAmount}")
    public Map<BigDecimal, Integer> getDenominationDifferenceForTwoAmount(
            @PathVariable("newAmount") BigDecimal newAmount, @PathVariable("oldAmount") BigDecimal oldAmount)
            throws BadRequestException {
        if (newAmount.compareTo(new BigDecimal(0)) == -1 || oldAmount.compareTo(new BigDecimal(0)) == -1) {
            throw new BadRequestException(" Amounts cannot be negative number.");
        }
        return denominationCalculator.getDenominationDifferenceForTwoAmounts(newAmount, oldAmount);
    }

}
