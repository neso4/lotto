package lotto.view;

import java.util.ArrayList;
import java.util.List;
import lotto.config.AppConfig;
import lotto.config.LottoGameMessage;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.exception.NonNumericAmountException;
import lotto.io.InputReceiver;
import lotto.utils.Parser;
import lotto.utils.Validator;

public class InputView {

    private final InputReceiver receiver;

    public InputView(InputReceiver receiver) {
        this.receiver = receiver;
    }

    public Money getMoney() {
        System.out.println(LottoGameMessage.INPUT_GET_MONEY.message());

        int money = inputMoney();
        try {
            return new Money(money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

    public List<Integer> getWinningNumbers() {
        System.out.println(LottoGameMessage.INPUT_WINNING_NUMBER.message());

        try {
            return inputWinningNumbers();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    public LottoNumber getBonusNumber(final Lotto lotto) {
        System.out.println(LottoGameMessage.INPUT_BONUS_NUMBER.message());
        try {
            return inputBonusNumber(lotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBonusNumber(lotto);
        }

    }

    private LottoNumber inputBonusNumber(Lotto lotto) {
        String bonusNumber = receiver.readLine();
        LottoNumber bonusLottoNumber = new LottoNumber(convertToInt(bonusNumber));
        Validator.validateDuplicate(lotto, bonusLottoNumber);
        return bonusLottoNumber;
    }

    private List<Integer> inputWinningNumbers() {
        String winningNumbers = receiver.readLine();
        List<String> parsedString = getParsedString(winningNumbers);
        List<Integer> numbers = convertToInts(parsedString);
        validate(numbers);
        return numbers;
    }

    private List<Integer> convertToInts(final List<String> parsedString) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : parsedString) {
            numbers.add(convertToInt(number));
        }
        return numbers;
    }

    private List<String> getParsedString(final String winningNumbers) {
        AppConfig appConfig = new AppConfig();
        Parser parser = appConfig.parser();
        return parser.split(winningNumbers);
    }

    private void validate(final List<Integer> numbers) {
        Validator.validateDuplicate(numbers);
        Validator.validateInRange(numbers);
        Validator.validateSize(numbers);
    }

    private int inputMoney() {
        try {
            String money = receiver.readLine();
            return convertToInt(money);
        } catch (NonNumericAmountException e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    private int convertToInt(final String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NonNumericAmountException();
        }
    }

    public void close() {
        receiver.close();
    }

}

