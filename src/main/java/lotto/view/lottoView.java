package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.model.Lotto;
import lotto.service.LottoService;

import java.util.List;

import static lotto.service.LottoService.inputNumberOfLotto;

public class LottoView {

    public static Integer getNumberOfLottoForPrice() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String priceInput = Console.readLine();
                return LottoService.inputNumberOfLotto(priceInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printLottoList(List<Lotto> lottoList) {
        for (Lotto lotto : lottoList) {
            System.out.println(lotto.toString());
        }
    }


}
