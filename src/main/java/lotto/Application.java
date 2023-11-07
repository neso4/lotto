package lotto;

import lotto.controller.Controller;
import lotto.model.Model;
import lotto.view.LottoView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new LottoView());
        controller.run();
    }
}
