package domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class LottoMachine {

    public List<List<Integer>> lottoMachineService(int lottoTicketCount) {
        List<List<Integer>> totalLottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(numbers); // 로또 번호 정렬
            totalLottoTickets.add(numbers);
        }
        return totalLottoTickets;
    }
}
