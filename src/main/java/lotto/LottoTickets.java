package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import dto.LottoMoney;
import java.util.LinkedList;
import java.util.List;

public class LottoTickets {
    public static final int START_NUM = 1;
    public static final int END_NUM = 45;
    public static final int COUNT = 6;

    private List<Lotto> tickets;

    public LottoTickets(LottoMoney money) {
        tickets = new LinkedList<>();

        for (int i = 0; i < money.getAmount(); i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(START_NUM, END_NUM, COUNT);
            Lotto lotto = new Lotto(numbers);
            tickets.add(lotto);
        }
    }

    public List<String> sayLottoNumbers() {
        List<String> lines = new LinkedList<>();
        for (Lotto ticket : tickets) {
            String line = ticket.sayNumbers();
            lines.add(line);
        }
        return lines;
    }
}
