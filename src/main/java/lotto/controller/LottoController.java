package lotto.controller;

import lotto.Lotto;
import lotto.domain.LottoGenerator;
import lotto.view.SystemInput;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    // 금액을 입력받는다
    // 금액을 통해 게임 개수를 구한다
    // 게임 수를 출력한다
    // 게임 수에 맞게 로또가 발행된다
    // 발행된 로또를 출력한다
    // 유저가 로또 번호를 입력하고 보너스 번호를 입력한다
    // 발행된 로또와 유저가 입력한 로또에서 같은 번호를 찾는다
    // rank를 출력한다
    // rate을 출력한다
    public void run() {
        try {
            start();
        }
        catch(IllegalArgumentException e) {

        }
    }

    public void start() {
        String purchaseAmount = SystemInput.readMoney();
        int money = Integer.parseInt(purchaseAmount);
        int gameAmount = money / 1000;
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> winlottos = lottoGenerator.lottoGenerateWinningLotto(gameAmount);
        String userPickLotto = SystemInput.readPickLotto();
        List<Integer> userPickNumber = new ArrayList<>();
        String[] userPickLottoSplit = userPickLotto.split(",");
        for (String userpick : userPickLottoSplit) {
            userPickNumber.add(Integer.parseInt(userpick));
        }
    }
}
