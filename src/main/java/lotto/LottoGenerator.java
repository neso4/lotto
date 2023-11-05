package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String OUTPUT_PURCHASE_AMOUNT = "\n%d개를 구매했습니다.\n";

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_OF_NUMBERS = 6;

    private final List<Lotto> lottos;
    private final int lottoAmount;

    public LottoGenerator() {
        this.lottoAmount = getLottoAmount(inputPurchaseAmount());
        this.lottos = makeLottos();
    }

    private int inputPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
        return Integer.parseInt(Console.readLine());
    }

    private int getLottoAmount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private List<Lotto> makeLottos() {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoAmount; i++) {
            lottos.add(new Lotto(makeRandomLottoNumbers()));
        }

        return lottos;
    }

    private List<Integer> makeRandomLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBER_OF_NUMBERS);
    }

    public void printLottos() {
        System.out.printf(OUTPUT_PURCHASE_AMOUNT, lottoAmount);

        for (Lotto lotto : lottos) {
            lotto.printNaturalOrder();
        }
    }
}
