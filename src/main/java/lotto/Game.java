package lotto;

import static lotto.constant.GameMessage.INPUT_BONUS_NUMBERS;
import static lotto.constant.GameMessage.INPUT_BUY_PRICE;
import static lotto.constant.GameMessage.INPUT_WIN_NUMBERS;
import static lotto.constant.GameMessage.YOU_BOUGHT_N_LOTTOS;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Bonus;
import lotto.domain.Lotto;
import lotto.domain.Price;

public class Game {

    public static void start() {
        Price price = inputBuyPrice();
        printBoughtAmount(price);
        List<Lotto> lottos = makeLottos(price.getAmount());
        printLottos(lottos);
        Lotto winLotto = inputWinningNumbers();
        Bonus bonusNumber = inputBonusNumber(winLotto);
    }

    private static Price inputBuyPrice() {
        while (true) {
            System.out.println(INPUT_BUY_PRICE.getMessage());
            try {
                return new Price(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printBoughtAmount(Price price) {
        System.out.println(price.getAmount() + YOU_BOUGHT_N_LOTTOS.getMessage());
    }

    private static List<Lotto> makeLottos(long amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (long i = 0; i < amount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return lottos;
    }

    private static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    private static Lotto inputWinningNumbers() {
        while (true) {
            System.out.println(INPUT_WIN_NUMBERS.getMessage());
            try {
                String input = Console.readLine();
                return new Lotto(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Bonus inputBonusNumber(Lotto winLotto) {
        while (true) {
            System.out.println(INPUT_BONUS_NUMBERS.getMessage());
            try {
                String input = Console.readLine();
                return new Bonus(input, winLotto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
