package lotto.domain.lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotto.domain.PurchasePrice;

public class LottoTickets {

    private final List<Lotto> lottoTickets;

    public LottoTickets(PurchasePrice price) {
        this.lottoTickets = createLottoTickets(price);
    }

    private List<Lotto> createLottoTickets(PurchasePrice price) {
        List<Lotto> lottoTickets = new ArrayList<>();
        int numberOfTickets = getNumberOfTickets(price);

        while (numberOfTickets-- > 0) {
            lottoTickets.add(createNewLotto());
        }

        return lottoTickets;
    }

    private int getNumberOfTickets(PurchasePrice price) {
        return price.getPrice() / 1000;
    }

    private Lotto createNewLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
