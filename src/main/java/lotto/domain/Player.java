package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lotto.utils.Prints;
import lotto.utils.Utils;

public class Player {
    private static final int START_NUM_RANGE = 1;
    private static final int END_NUM_RANGE = 45;
    private static final int LOTTO_NUM_COUNT = 6;
    private static LottoAmount lottoAmount;
    private static LottoCount lottoCount;
    private static List<Lotto> lottos = new ArrayList<>();
    private static WinLotto winLotto = new WinLotto();

    public void createLotto() {
        IntStream.rangeClosed(1, lottoCount.getLottoCount())
                .forEach(e -> lottos.add(
                        new Lotto(
                                Randoms.pickUniqueNumbersInRange(START_NUM_RANGE, END_NUM_RANGE, LOTTO_NUM_COUNT))));
    }

    public void showLottos() {
        lottos.stream().forEach(e -> {
            e.showLotto();
        });
    }

    public void setLottoAmount() {
        Prints.print_input_amount_message();
        this.lottoAmount = new LottoAmount(Utils.parseIntValidate(Console.readLine()));
    }

    public void setLottoCount() {
        this.lottoCount = new LottoCount(lottoAmount.getLottoAmount());
    }

    public void resultCalculate(WinLotto winLotto) {
    }

    public void createWinLotto() {
        winLotto.createLotto();
        winLotto.createBonusNumber();
    }

    public void showLottoCount() {
        Prints.print_lottoCount(lottoCount.getLottoCount());
    }
}
