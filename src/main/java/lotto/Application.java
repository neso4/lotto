package lotto;

import java.util.List;

import static lotto.Notice.*;

public class Application {
    public static void main(String[] args) {

        GameData gameData = new GameData();

        View.printMessage(ASK_BUDGET);
        String lottoBudgetInput = View.getUserInput();
        Integer budget = Convertor.convertInputToInteger(lottoBudgetInput);
        Integer lotteryCount = Controller.calculateLotteryCount(budget);
        gameData.generateLottoList();

        for (int i = 0; i < lotteryCount; i++) {
            List<Integer> lotteryNumbers = Lotto.generateLotteryNumbers();
            List<Lotto> lottoList = gameData.getLottoList();
            lottoList.add(new Lotto(lotteryNumbers));
        }

        View.printMessage(ASK_WINNING_NUMBERS);
        String winningNumbersInput = View.getUserInput();
        String[] winningNumbersArray = Convertor.splitInput(winningNumbersInput);
        List<Integer> winningNumbersTemp = Convertor.convertToList(winningNumbersArray);
        gameData.setWinningNumbers(winningNumbersTemp);

        View.printMessage(ASK_BONUS_NUMBERS);
        String bonusNumberInput = View.getUserInput();
        Integer bonusNumberTemp = Convertor.convertInputToInteger(bonusNumberInput);
        gameData.setBonusNumber(bonusNumberTemp);



        View.printMessage(GAME_STATISTICS);
        View.printMessage(SEPARATE_LINE);
    }

}
