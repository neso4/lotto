package lotto;

import lotto.Controller.Process;
import lotto.Controller.ValidateInput;
import lotto.View.InputView;
import lotto.View.OutputView;

public class Application {
    public static void main(String[] args) {
        Process process = new Process(new InputView(), new OutputView());
        process.drawLotto(new ValidateInput());

    }
}
