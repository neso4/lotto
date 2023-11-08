package Controller;

import Domain.Board.Board;
import Domain.Market.LotteryMarket;
import View.BoardView;
import View.InputView;
import View.LottoView;
import Domain.WinningNumber.WinningNumber;
import Domain.lotto.Lotto;

import java.util.List;

public class ControllerFacade {
    MoneyInputController moneyInputController;
    WinningNumberInputController winningNumberInputController;
    BoardController boardController;
    LotteryController lotteryController;

    public ControllerFacade(InputView inputView, BoardView boardView, LottoView lottoView){
        this.moneyInputController = new MoneyInputController(inputView);
        this.winningNumberInputController = new WinningNumberInputController(inputView);
        this.boardController = new BoardController(boardView);
        this.lotteryController = new LotteryController(lottoView);
    }

    public void run(){
        int money = moneyInputController.RecursiveGetMoneyFromConsole();
        List<Lotto> lotteries = LotteryMarket.issueLotteries(money);

        lotteryController.setLotteries(lotteries);
        lotteryController.printLotteries();

        WinningNumber winningNumber = winningNumberInputController.getWinningNumberFromConsole();
        Board board = new Board(money);
        boardController.setParameters(board, winningNumber, lotteries);
        boardController.printBoard();
    }
}
