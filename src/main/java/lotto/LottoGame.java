package lotto;

import java.util.Arrays;
import java.util.List;

import static lotto.Validator.*;

public class LottoGame {
    private final UserInterface userInterface = new UserInterface();
    private WinLotto winLotto;
    private UserLottos userLottos;

    // 로또 게임 진행 시작
    public void start() {
        int amount = getLottoAmount();
        UserLottos userLottos = new UserLottos(amount);
        showLottos(userLottos);
        winLotto = new WinLotto(getWinNumbers());
        winLotto.setBonusNumber(getBonusNumber());
    }

    // 구입금액 입력
    private int getLottoAmount() {
        String amount = "";
        while (true) {
            try {
                userInterface.showText("구입금액을 입력해 주세요.");
                amount = userInterface.getUserInput();
                validateAmount(amount);
            } catch (IllegalArgumentException e) {
                userInterface.showText(e.getMessage());
                continue;
            }
            userInterface.newLine();
            return Integer.parseInt(amount);
        }
    }

    // 발행한 로또 수량 및 번호 출력
    private void showLottos(UserLottos userLottos) {
        userInterface.showText(userLottos.getSize() + "개를 구매했습니다.");
        userInterface.showText(userLottos.getLottosString());
    }

    // 당첨 번호 입력
    private List<String> getWinNumbers() {
        String numbers = "";
        while (true) {
            try {
                userInterface.showText("당첨 번호를 입력해 주세요.");
                numbers = userInterface.getUserInput();
                validateWinNumbers(numbers);
            } catch (IllegalArgumentException e) {
                userInterface.showText(e.getMessage());
                continue;
            }
            userInterface.newLine();
            return Arrays.asList(numbers.split(","));
        }
    }

    // 보너스 번호 입력
    private int getBonusNumber() {
        String number = "";
        while (true) {
            try {
                userInterface.showText("보너스 번호를 입력해 주세요.");
                number = userInterface.getUserInput();
                validateBonusNumber(number, winLotto.getLotto().getNumbers());
            } catch (IllegalArgumentException e) {
                userInterface.showText(e.getMessage());
                continue;
            }
            userInterface.newLine();
            return Integer.parseInt(number);
        }
    }
}
