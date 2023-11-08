package lotto.Service;

import camp.nextstep.edu.missionutils.Console;
import lotto.Service.OutputService;

public class InputService {
    private static final String ASK_WINNING_NUMBER_STATEMENT = "당첨 번호를 입력해주세요";
    private static final String ASK_BONUS_NUMBER_STATEMENT = "보너스 번호를 입력해 주세요.";

    private static final String ASK_MONEY_STATEMENT = "구입금액을 입력해 주세요.";

    public static String askUserWinningGame() {
        OutputService.askStatmement(ASK_WINNING_NUMBER_STATEMENT);
        return Console.readLine();
    }

    public static String askBonusNumber(){
        OutputService.askStatmement(ASK_BONUS_NUMBER_STATEMENT);
        return Console.readLine();
    }

    public static String askMoney(){
        OutputService.askStatmement(ASK_MONEY_STATEMENT);
        return Console.readLine();
    }
}
