package lotto;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoVendor {

    private final int price;

    public LottoVendor(int price) {
        this.price = price;
    }

    public List<Lotto> purchaseAll(int money) throws IllegalArgumentException {

        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < money / price; ++i) {
            purchased.add(createOne());
        }
        return purchased;
    }

    private Lotto createOne() {
        List<Integer> numbers = pickUniqueNumbersInRange(1, 45, 6).stream().sorted().collect(Collectors.toList());
        return new Lotto(numbers);
    }
}
