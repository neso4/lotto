package lotto;

import lotto.model.db.Model;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    private final InputView inputView;
    private final OutputView outputView;

    public Application() {
        Model model = new Model();
        this.inputView = new InputView(model);
        this.outputView = new OutputView(model);
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }

    private void start() {
        inputView.start();
        outputView.start();
    }
}
