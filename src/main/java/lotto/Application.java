package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        try {
            LottoController controller = new LottoController();
            controller.run();
        } catch (IllegalArgumentException e) {
            ErrorLogger.log(e);
        } catch (Exception e) {
            ErrorLogger.exceptionLog(e);
        }

    }
}
