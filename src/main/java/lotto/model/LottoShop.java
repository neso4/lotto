package lotto.model;

import lotto.constants.Constants;
import lotto.exception.NotDivisibleByLottoPriceException;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {

    private final LottoIssuer lottoIssuer = new LottoIssuer();

    public List<Lotto> sell(int payment) throws IllegalArgumentException {
        validateDivisibleByLottoPrice(payment);
        int amount = calculateLottoAmount(payment);
        return getIssuedLotto(amount);
    }

    private void validateDivisibleByLottoPrice(int number) {
        if (number % Constants.LOTTO_PRICE != 0) {
            throw new NotDivisibleByLottoPriceException();
        }
    }

    private int calculateLottoAmount(int payment) {
        return payment / Constants.LOTTO_PRICE;
    }

    private List<Lotto> getIssuedLotto(int amount) {
        List<Lotto> issuedLotto = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            issuedLotto.add(lottoIssuer.issue());
        }
        return issuedLotto;
    }
}