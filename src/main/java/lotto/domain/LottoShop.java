package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoShop {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_AMOUNT = 1000;

    public static List<Lotto> createLottosBy(PurchaseAmount purchaseAmount) {
        return IntStream.range(0, changeAmountToLottos(purchaseAmount)).mapToObj(i -> new Lotto(createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static List<Integer> createRandomLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_NUMBER,MAX_NUMBER,LOTTO_SIZE);
    }

    private static int changeAmountToLottos(PurchaseAmount purchaseAmount) {
        validateAmountDivisible(purchaseAmount);
        return purchaseAmount.changeBy(LOTTO_AMOUNT);
    }

    public static int countLottoTicket(PurchaseAmount purchaseAmount) {
        return changeAmountToLottos(purchaseAmount);
    }

    public static void validateAmountDivisible(PurchaseAmount purchaseAmount) {
        if(!purchaseAmount.divisible(LOTTO_AMOUNT)) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }
}
