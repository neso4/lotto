package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Money;
import lotto.global.generator.NumbersGenerator;
import lotto.global.generator.RandomNumbersGenerator;

public class LottoStore {

    private final NumbersGenerator numbersGenerator;

    public LottoStore() {
        this(new RandomNumbersGenerator());
    }

    public LottoStore(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public List<Lotto> buyLotto(Money money) {
        int purchaseLottoCount = money.getPurchaseLottoCount();
        return generateLottos(purchaseLottoCount);
    }

    private List<Lotto> generateLottos(int purchaseLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 1; i <= purchaseLottoCount; i++) {
            List<Integer> numbers = numbersGenerator.generateNumbers();
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

}
