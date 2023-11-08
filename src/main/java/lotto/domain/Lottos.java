package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    //로또 수량을 입력 받아 1부터 45까지의 랜덤 숫자 생성
    public void lottoTicketsNum(int lottoCount){
        List<Lotto> lottoTickets = new ArrayList<>();
        int count = 0;

        while(count < lottoCount) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            try{
                Collections.sort(numbers);
                lottoTickets.add(new Lotto(numbers));
                count++;
            }catch (IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
        printLottoTickets(lottoTickets);
    }

    public void printLottoTickets(List<Lotto> lottoTickets) {
        for (Lotto lotto : lottoTickets) {
            System.out.println(lotto.getNumbers());
        }
    }
}
