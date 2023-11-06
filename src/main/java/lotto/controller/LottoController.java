package lotto.controller;

import lotto.domain.Payment;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private LottoService lottoService = new LottoService();

    public void run() {
        buyTickets();
        printTickets();
        readWinningNumbers();
    }

    private void buyTickets() {
        try {
            Payment payment = new Payment(InputView.readPayment());
            lottoService.init(payment.getLottoCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            buyTickets();
        }
    }

    private void printTickets() {
        OutputView.printTickets(lottoService.tickets());
    }

    private void readWinningNumbers() {
        readMainNumbers();
        readBonusNumber();
    }

    private void readMainNumbers() {
        try {
            lottoService.initMainNumbers(InputView.readMainNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            readMainNumbers();
        }
    }

    private void readBonusNumber() {
        try {
            lottoService.initBonusNumber(InputView.readBonusNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            readBonusNumber();
        }
    }

}
