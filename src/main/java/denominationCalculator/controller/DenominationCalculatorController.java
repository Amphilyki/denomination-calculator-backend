package denominationCalculator.controller;

import denominationCalculator.service.DenominationCalculator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/euro-denomination-calculator")
public class DenominationCalculatorController {

    @Autowired
    DenominationCalculator denominationCalculator;

    @GetMapping("/amount/{amount}")
    public Map<BigDecimal, Integer> getDenominationsForAmount(@PathVariable("amount") BigDecimal amount)
            throws BadRequestException {
        if (amount.compareTo(new BigDecimal(0)) != 1) {
            throw new BadRequestException(" Amount cannot be 0 or negative");
        }
        return denominationCalculator.getDenominationForAmount(amount);
    }

}
