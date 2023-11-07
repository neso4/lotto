package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.domain.Lotto;

public class LottoTicketFactory {

    public static List<Lotto> lottoTickets(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        while (tickets.size() < ticketCount) {
            tickets.add(lottoTicket());
        }
        return tickets;
    }

    private static Lotto lottoTicket() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(lottoNumbers);
        return new Lotto(lottoNumbers);
    }
}
