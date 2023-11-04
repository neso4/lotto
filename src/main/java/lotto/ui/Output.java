package lotto.ui;

import java.util.List;
import lotto.domain.Lotto;

public interface Output {
    void printLottoPriceRequest();
    void printPurchasedLotto(List<Lotto> lottos);
}
