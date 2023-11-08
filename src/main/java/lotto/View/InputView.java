package lotto.View;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.View.InputConvertor.InputConvertor;
import lotto.View.InputValidator.BonusWinningNumValidator;
import lotto.View.InputValidator.BudgetInputValidator;
import lotto.View.InputValidator.WinningNumValidator;

public class InputView {

    private static final String ASK_LOTTERY_BUDGET = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String ASK_WINNING_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    BudgetInputValidator budgetValidator = new BudgetInputValidator();
    WinningNumValidator winningNumValidator = new WinningNumValidator();
    BonusWinningNumValidator bonusWinningNumValidator = new BonusWinningNumValidator();


    InputConvertor inputConvertor = new InputConvertor();

    public Integer lotteryBudgetInput() {
        while (true) {
            try {
                System.out.println(ASK_LOTTERY_BUDGET);
                String lotteryBudgetInput = Console.readLine();
                budgetValidator.validate(lotteryBudgetInput);
                return inputConvertor.convertLotteryBudget(lotteryBudgetInput);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }

    public List<Integer> winningNumInput() {
        while (true) {
            try {
                System.out.println(ASK_WINNING_NUMBER);
                String winningNumInput = Console.readLine();
                winningNumValidator.validate(winningNumInput);
                return inputConvertor.convertWinningNum(winningNumInput);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }

    public Integer bonusWinningNumInput(List<Integer> winningNumInput) {
        while (true) {
            try {
                System.out.println(ASK_WINNING_BONUS_NUMBER);
                String bonusWinningNumInput = Console.readLine();
                bonusWinningNumValidator.validate(bonusWinningNumInput, winningNumInput);
                return inputConvertor.convertBonusWinningNum(bonusWinningNumInput);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }
}
