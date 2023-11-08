package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPrizeCalculator;
import lotto.domain.LottoReceipt;
import lotto.domain.WinningLotto;
import lotto.service.Input;
import lotto.service.Output;

public class Application {
    private static final int LOTTO_PRICE = 1000;
    public static final String MESSAGE_ERROR = "[ERROR]";

    public static void main(String[] args) {
        // 사용자 로또 발행
        int amount = validateAmount(Input.getPurchaseAmount());
        validateDivisible(amount);
        List<Lotto> lottoTickets = generateLottoTicket(amount);
        LottoReceipt lottoReceipt = new LottoReceipt(lottoTickets);
        Output.printLottoReceipt(lottoReceipt);
        // 당첨 로또 발행
        Lotto lotto = new Lotto(Input.getWinningNumber());
        WinningLotto winningLotto = new WinningLotto(lotto, Input.getBonusNumber());
        // 당첨 통계
        LottoPrizeCalculator.checkLottoResult(lottoReceipt, winningLotto);
        Output.printLottoWinningResults(lottoReceipt);
        // 수익률
        LottoPrizeCalculator.calculateProfitRate(lottoReceipt, amount);
        Output.printProfitRate(lottoReceipt.getProfitRate());
    }

    public static int validateAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            Output.printErrorMessage(" 숫자로 변환할 수 없는 입력입니다.");
        }
        return 0;
    }

    public static void validateDivisible(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(MESSAGE_ERROR + "1000원 단위로 입력해주세요.");
        }
    }

    public static List<Lotto> generateLottoTicket(int amount) {
        List<Lotto> lottoTickets = new ArrayList<>();
        int value = amount / LOTTO_PRICE;
        for (int i = 0; i < value; i++) {
            Lotto lotto = new Lotto(generateRandomNumber());
            lottoTickets.add(lotto);
        }
        return lottoTickets;
    }

    public static List<Integer> generateRandomNumber() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }
}
