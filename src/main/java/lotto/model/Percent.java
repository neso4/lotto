package lotto.model;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Percent {
    private final double percent;
    private final double deltaValue = 0.001;
    public Percent(LottoTicketMoney lottoTicketMoney, PriceMoney priceMoney) {
        percent = priceMoney.div(lottoTicketMoney) * 100.0;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%.1f%%", percent);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(percent);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Percent otherPercent)) return false;
        return Math.abs(percent - otherPercent.percent) < deltaValue;
    }
}
