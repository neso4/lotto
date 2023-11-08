package lotto.controller.user;

import lotto.controller.handler.ExceptionHandler;
import lotto.domain.Lotto;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class LottoDraw {
    private Lotto lotto;
    private final InputView inputView;

    public LottoDraw(InputView inputView) {
        this.inputView = inputView;
    }

    private boolean isCorrectLottoNumbers(String input) {
        ExceptionHandler exceptionHandler = new ExceptionHandler();

        try {
            exceptionHandler.handleLottoNumbersException(input);
        } catch (IllegalArgumentException exception) {
            inputView.showInputErrorMessage(exception.getMessage());
            return false;
        }

        return true;
    }

    private List<String> inputDrawNumbers() {
        String input;
        List<String> numbers;

        while (true) {
            inputView.showWinningNumbersInputForm();
            input = readLine().trim();
            inputView.endInput();

            if (isCorrectLottoNumbers(input)) {
                break;
            }
        }

        numbers = List.of(input.split(","));
        return numbers;
    }

    private List<Integer> creatLotto(List<String> numbers) {
        List<Integer> drawNumbers = new ArrayList<>();

        for (String number : numbers) {
            drawNumbers.add(Integer.parseInt(number.trim()));
        }

        return drawNumbers;
    }

    public void draw() {
        List<String> numbers = inputDrawNumbers();
        List<Integer> drawNumbers = creatLotto(numbers);

        lotto = new Lotto(drawNumbers);
    }

    public Lotto getLotto() {
        return lotto;
    }
}
