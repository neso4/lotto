package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class LottoView {
    public static String askLottoNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");

        return Console.readLine();
    }

    public static String askBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");

        return Console.readLine();
    }
}
