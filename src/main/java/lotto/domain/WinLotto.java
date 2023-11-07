package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinLotto {
    private static Lotto lotto;
    private static BounsNumber bounsNumber;

    public void createLotto() {
        lotto = new Lotto(inputWinNumber());
        lotto.lottoByDuplicateNumber();
        lotto.lottoByRange();
    }

    public void createBonusNumber() {
        bounsNumber = new BounsNumber(inputBonusNumber());
    }

    private List<Integer> inputWinNumber() {
        try {
            return Arrays.stream(Console.readLine().split(","))
                           .map(e -> Integer.parseInt(e))
                           .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(",로 구분하여 입력해주세요.");
        }
    }

    private int inputBonusNumber() {
        return Integer.parseInt(Console.readLine());
    }
}
