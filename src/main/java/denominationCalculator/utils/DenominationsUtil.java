package denominationCalculator.utils;

import java.math.BigDecimal;

public class DenominationsUtil {
    public static final BigDecimal TW0_HUNDRED_EUROS = new BigDecimal(200);
    public static final BigDecimal ONE_HUNDRED_EUROS = new BigDecimal(100);
    public static final BigDecimal FIFTY_EUROS = new BigDecimal(50);
    public static final BigDecimal TWENTY_EUROS = new BigDecimal(20);
    public static final BigDecimal TEN_EUROS = new BigDecimal(10);
    public static final BigDecimal FIVE_EUROS = new BigDecimal(5);
    public static final BigDecimal TWO_EUROS = new BigDecimal(2);
    public static final BigDecimal ONE_EURO = new BigDecimal(1);
    public static final BigDecimal FIFTY_CENTS = new BigDecimal("0.5");
    public static final BigDecimal TWENTY_CENTS = new BigDecimal("0.2");
    public static final BigDecimal TEN_CENTS = new BigDecimal("0.1");
    public static final BigDecimal FIVE_CENTS = new BigDecimal("0.05");
    public static final BigDecimal TWO_CENTS = new BigDecimal("0.02");
    public static final BigDecimal ONE_CENT = new BigDecimal("0.01");

    public static final BigDecimal[] DENOMINATIONS = new BigDecimal[]{
            TW0_HUNDRED_EUROS,
            ONE_HUNDRED_EUROS,
            FIFTY_EUROS,
            TWENTY_EUROS,
            TEN_EUROS,
            FIVE_EUROS,
            TWO_EUROS,
            ONE_EURO,
            FIFTY_CENTS,
            TWENTY_CENTS,
            TEN_CENTS,
            FIVE_CENTS,
            TWO_CENTS,
            ONE_CENT
    };
}
