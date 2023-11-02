package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.WinningLotto;

import java.util.Arrays;
import java.util.List;

import static lotto.constant.SymbolConstant.COMMA;

public class MessageReceiver {

    private final ViewValidator viewValidator;

    public MessageReceiver(final ViewValidator viewValidator) {
        this.viewValidator = viewValidator;
    }

    public int receiveBuyingPrice() {
        boolean validInput = false;
        int buyingPrice = 0;

        while (!validInput) {
            String buyingPriceText = Console.readLine();
            buyingPrice = Integer.parseInt(buyingPriceText);
            validInput = viewValidator.validateBuyingPrice(buyingPrice);
        }

        return buyingPrice;
    }

    public void receiveWinningNumbers() {
        String winningNumbersText = Console.readLine();
        List<String> winningNumbers = Arrays.asList(winningNumbersText.split(COMMA));
        WinningLotto winningLotto = WinningLotto.of(winningNumbers);
    }
}
