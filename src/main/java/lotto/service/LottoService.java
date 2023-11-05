package lotto.service;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;

public class LottoService {

    public ArrayList<Lotto> generateLotto(PurchaseAmount purchaseAmount) {
        ArrayList<Lotto> lottos = new ArrayList<>();

        int count = purchaseAmount.getMoney() / 1000;

        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto(generateLottoNumbers());
            lottos.add(lotto);
        }

        return lottos;
    }

    public List<Integer> generateLottoNumbers() {
        List<Integer> numbers = pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(numbers);
        return numbers;
    }
}