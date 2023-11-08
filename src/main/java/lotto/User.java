package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int lottoCount;
    private int lottoPrice;
    private List<Lotto> lottos;
    private List<Integer> ratings;
    private int totalPrize;

    public User() {
        lottos = new ArrayList<>();
        ratings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ratings.add(0);
        }
        totalPrize = 0;
    }

    private void buyLotto() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputText = Console.readLine();
        try {
            Validator.buyCheck(inputText);
            lottoPrice = Integer.parseInt(inputText);
            lottoCount = lottoPrice / 1000;
        } catch (IllegalArgumentException e) {
            buyLotto();
        }
    }

    public void getLotto() {
        buyLotto();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(lotto);
        }
        printLotto();
    }

    private void printLotto() {
        System.out.println(lottoCount + "개를 구매했습니다.");
        for (int i = 0; i < lottoCount; i++) {
            lottos.get(i).printLottoNumber();
        }
    }

    public void calculate(List<Integer> targetNumbers, int bonusNumber) {
        for (int i = 0; i < lottoCount; i++) {
            calcPrize(lottos.get(i),targetNumbers, bonusNumber);
        }
    }

    private void calcPrize(Lotto lotto ,List<Integer> targetNumbers, int bonusNumber) {
        for (int i = 0; i < 6; i++) {
            targetNumbers.get(i)==lotto.
        }
    }
}
