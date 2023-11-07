package lotto.controller.action;

import lotto.domain.service.LottoService;
import lotto.ui.input.InputView;
import lotto.ui.output.OutputView;

import static lotto.controller.util.Conversion.makeInt;

public class LottoAction {

    public void process(String message, LottoService service) {
        String input;
        do {
            OutputView.printMessage(message);
            input = InputView.input();
        } while (run(input, service));
    }

    public boolean run(String input, LottoService service) {
        try {
            int price = makeInt(input); //검증 필요
            service.buyLotteries(price); //검증 필요
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

}
