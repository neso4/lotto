package controller;

import model.MyLotto;
import model.WinningNumbers;
import view.InputView;
import view.OutPutView;

public class FrontController {

    MyLotto myLotto;
    WinningNumbers winningNumbers;
    CompareController compareController;

    public FrontController() {
        compareController = new CompareController();
    }

    public void run() {
        myLotto = buyLotto();
        OutPutView.printPurchaseResult(myLotto);
        winningNumbers = drawWinningNumbers();
        setBonusNumber();
        compareController.run(myLotto, winningNumbers);
    }

    public MyLotto buyLotto() {
        while (true) {
            try {
                return new MyLotto(InputView.askPurchaseAmount());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public WinningNumbers drawWinningNumbers() {
        while (true) {
            try {
                return new WinningNumbers(InputView.askWinningNumbers());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setBonusNumber() {
        while (true) {
            try {
                winningNumbers.setBonusNumber(InputView.askBonusNumber());
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
