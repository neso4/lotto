package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Purchase;
import lotto.domain.number.LottoRandomNumber;

public class LottoTicket {

    private List<Lotto> lottoTicket = new ArrayList<>();
    private final static LottoRandomNumber numbers = new LottoRandomNumber();

    public LottoTicket(Purchase purchase) {
        createLottoTicket(purchase.getPurchaseLotto());
    }

    private void createLottoTicket(int purchaseLotto) {
        lottoTicket = IntStream.range(0, purchaseLotto)
            .mapToObj(i -> new Lotto(numbers.getLottoRandomNumbers()))
            .collect(Collectors.toList());
    }

    public List<Lotto> getLottoTicket() {
        return lottoTicket;
    }
}