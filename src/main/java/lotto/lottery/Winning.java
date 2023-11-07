package lotto.lottery;

import lotto.Lotto;
import lotto.exception.InputValidator;
import lotto.manager.InputManager;

import java.util.Collections;
import java.util.List;

public class Winning {
    private static List<Integer> winningNumbers;

    public Winning(String winningNumbers) {
        InputManager inputManager = new InputManager();
        this.winningNumbers = inputManager.splitAndConvertToList(winningNumbers);
        Collections.sort(this.winningNumbers);
        checkWinningNumbers(winningNumbers);
        new Lotto(this.winningNumbers);
    }

    //당첨 번호 예외 체크
    private void checkWinningNumbers(String winningNumbers) {
        InputValidator inputValidator = new InputValidator();
        inputValidator.checkInputValue(winningNumbers);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
