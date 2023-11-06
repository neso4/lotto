package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public final class InputView extends ConsoleWriter {
    public int readPaymentPrice() {
        this.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> readAnswerNumber() {
        this.newLine();
        this.println("당첨 번호를 입력해 주세요.");
        return Arrays.stream(Console.readLine().split(",")).map(splitStr -> {
            try {
                return Integer.parseInt(splitStr);
            } catch (NumberFormatException ignore) {
                throw new IllegalArgumentException();
            }
        }).toList();
    }

    public int readBonusNumber() {
        this.newLine();
        this.println("보너스 번호를 입력해 주세요.");
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }
}
