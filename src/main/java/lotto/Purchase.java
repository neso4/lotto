package lotto;

import static lotto.constant.Number.*;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class Purchase {

    private final int purchaseAmount;
    private final List<Lotto> myLotto;

    public Purchase(String purchaseInput) {
        this.purchaseAmount = calculatePurchaseAmount(getPurchasePrice(purchaseInput));
        this.myLotto = makeMyLotto(purchaseAmount);
    }



    private int getPurchasePrice(String purchaseInput) {
        return Integer.parseInt(purchaseInput);
    }

    public int calculatePurchaseAmount(int purchasePrice) {
        return purchasePrice / PURCHASE_UNIT.getRange();
    }

    public List<Integer> generateNumber() {
        return Randoms.pickUniqueNumbersInRange(MIN.getRange(), MAX.getRange(), CNT.getRange());
    }

    private Lotto generateLotto() {
        List<Integer> numbers = generateNumber();
        return new Lotto(numbers);
    }

    public List<Lotto> makeMyLotto(int purchaseAmount) {
        List<Lotto> myLotto = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            Lotto lotto = generateLotto();
            myLotto.add(lotto);
        }
        return myLotto;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public List<Lotto> getMyLotto() {
        return myLotto;
    }
}
