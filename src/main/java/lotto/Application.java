package lotto;

import lotto.Controller.LottoController;

public class Application {
    public static void main(String[] args) {
       try {
        LottoController lottoController = new LottoController();
        lottoController.run();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
}
}