package lotto;

import static lotto.constant.LottoInformation.DELIMITER;
import static lotto.constant.message.ErrorMessage.NOT_ONLY_DIGIT;
import static lotto.constant.message.InputMessage.INPUT_BONUS_NUMBER;
import static lotto.constant.message.InputMessage.INPUT_MONEY;
import static lotto.constant.message.InputMessage.INPUT_WINNING_NUMBER;
import static lotto.constant.message.OutputMessage.RESULT_MESSAGE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Customer;
import lotto.domain.LottoCalculator;
import lotto.domain.LottoSeller;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final Customer customer;
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final WinningNumbers winningNumbers = new WinningNumbers();

    public LottoController(Customer customer) {
        this.customer = customer;
    }

    public void purchaseLotto() {
        handleUserInput(() -> {
            String input = inputView.requestInputValue(INPUT_MONEY);
            int purchaseAmount = convertInteger(input);

            customer.scanPocket(purchaseAmount);
            LottoSeller seller = new LottoSeller();
            seller.checkRemainder(customer.getMoney());
            outputView.printLottoCount(seller.getSellCount());
            customer.setLotteryTickets(seller.makeLottoTickets());
            outputView.printLottoNumbers(customer.getLotteryTickets());
        });
    }

    public void generateWinningNumbers() {
        handleUserInput(() -> {
            String input = inputView.requestInputValue(INPUT_WINNING_NUMBER);
            List<Integer> numbers = convertIntegerList(input);
            winningNumbers.makeWinningNumber(numbers);
        });
    }

    public void generateBonusNumber() {
        handleUserInput(() -> {
            String inputValue = inputView.requestInputValue(INPUT_BONUS_NUMBER);
            int bonusNumber = convertInteger(inputValue);
            winningNumbers.makeBonusNumber(bonusNumber);
        });
    }

    public void revealLottoResults() {
        outputView.printOutputMessage(RESULT_MESSAGE);
        LottoCalculator calculator = new LottoCalculator(winningNumbers);
        calculator.makePrizeResult(customer.getLotteryTickets());
        outputView.printPrizeResult(calculator.getResult());
        String profitRate = calculator.calculateProfitRate(customer.getMoney());
        outputView.printProfitRate(profitRate);
    }

    private void handleUserInput(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int convertInteger(String input) {
        validateOnlyDigit(input);
        return Integer.parseInt(input);
    }

    private List<Integer> convertIntegerList(String input) {
        String[] numbers = validateListOnlyDigit(input);

        return Arrays.stream(numbers).map(Integer::parseInt).collect(Collectors.toList());
    }

    private String[] validateListOnlyDigit(String input) {
        String[] numbers = input.split(DELIMITER, -1);

        for (String number : numbers) {
            validateOnlyDigit(number);
        }
        return numbers;
    }

    private void validateOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit) || input.isEmpty()) {
            throw new IllegalArgumentException(NOT_ONLY_DIGIT.getMessage());
        }
    }

}
