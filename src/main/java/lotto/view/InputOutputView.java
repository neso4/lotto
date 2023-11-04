package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Map;
import lotto.common.LottoRank;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.dto.LottoGameResponse;

public class InputOutputView {
    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                String money = Console.readLine();
                return new Money(money);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Lotto inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                String winningNumbers = Console.readLine();
                return Lotto.createLotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        while (true) {
            try {
                int bonusNumber = Integer.parseInt(Console.readLine());
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printBuyLottos(LottoGameResponse lottoGameResponse) {
        System.out.printf("%s개를 구매했습니다.\n", lottoGameResponse.getCount());
        for (int i = 0; i < lottoGameResponse.getCount(); i++) {
            System.out.println(lottoGameResponse.getBuyLottoNumbers().get(i));
        }
    }

    public void printResult(Map<LottoRank, Integer> result) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %s개\n", result.getOrDefault(LottoRank.FIFTH_RANK, 0));
        System.out.printf("4개 일치 (50,000원) - %s개\n", result.getOrDefault(LottoRank.FOURTH_RANK, 0));
        System.out.printf("5개 일치 (1,500,000원) - %s개\n", result.getOrDefault(LottoRank.THIRD_RANK, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %s개\n", result.getOrDefault(LottoRank.SECOND_RANK, 0));
        System.out.printf("6개 일치 (2,000,000,000원) - %s개\n", result.getOrDefault(LottoRank.FIRST_RANK, 0));
        System.out.println("총 수익률은 62.5%입니다.");
    }
}
