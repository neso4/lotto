package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    
    private List<Lotto> lottos = new ArrayList<>();
    public void generateLotto(Integer money) {
        Integer iterNum = money / 1000;
        for(int i = 0; i < iterNum; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
    }
    public Integer getGameSize() {
        return this.lottos.size();
    }
    
    public void printLottos() {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
    
}
