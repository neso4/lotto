package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int numberOfLotto;
    private final List<Lotto> lottoTickets;

    public LottoTicket(int numberOfLotto) {
        this.numberOfLotto = numberOfLotto;
        List<Lotto> lottoTickets = issueTickets(numberOfLotto);
        this.lottoTickets = lottoTickets;
    }


    public List<Lotto> issueTickets(int count) {
        List<Lotto> lottoTickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Lotto lottoTicket = generateLotto();
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_COUNT);
        return new Lotto(numbers);
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }

    public int getCount() {
        return numberOfLotto;
    }
}