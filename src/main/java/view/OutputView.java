package view;

import java.util.List;
import model.dto.LottoResponse;

public class OutputView {

    public void askPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void informLottoCount(final int count) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public void noticeGeneratedLottos(final List<LottoResponse> lottoResponses) {
        lottoResponses.stream()
            .map(LottoResponse::getJoinedNumbers)
            .forEach(numbers -> {
                System.out.printf("[%s]\n", numbers);
            });
    }

    public void askLottoNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }


    public void askBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }
}
