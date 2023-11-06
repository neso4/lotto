package lotto;

import lotto.constant.ExceptionMessage;
import lotto.constant.LottoRanking;
import lotto.domain.Lotto;
import lotto.domain.LottoJudgement;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.dto.LottoAnswer;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static lotto.constant.LottoConstant.*;

public class Application {
    private static LottoInputView inputView;
    private static LottoOutputView outputView;


    public static void main(String[] args) {
        // TODO: 프로그램 구현
        inputView = new LottoInputView();
        outputView = new LottoOutputView();

        int userPaid = getUserPaid();

        List<Lotto> userLottos = new ArrayList<>();
        for (int i = 0; i < userPaid / LOTTO_PRICE; i++) {
            Lotto lotto = new Lotto(LottoNumberGenerator.generateLottoNumbers());
            userLottos.add(lotto);
        }
        outputView.printBoughtResult(userLottos);

        Lotto winningLotto = getWinningLotto();
        int bonus = getBonusNumber();

        LottoAnswer answer = new LottoAnswer(winningLotto, bonus);
        LottoJudgement judgement = new LottoJudgement(answer);

        Map<LottoRanking, Integer> lottoResult = judgement.calculateLottoRanking(userLottos);
        outputView.printLottoResult(lottoResult);

        int totalPrize = LottoJudgement.calculateTotalPrize(lottoResult);
        outputView.printProfit(userPaid, totalPrize);
    }

    public static int getUserPaid() {
        int userPaid;
        while (true) {
            try {
                outputView.printBuyingPriceInputRequest();
                String input = inputView.getBuyingPrice();
                validateBuyingPrice(input);
                userPaid = Integer.parseInt(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return userPaid;
    }

    public static Lotto getWinningLotto() {
        Lotto winningLotto;
        while (true) {
            try {
                outputView.printAnswerInputRequest();
                String input = inputView.getLottoWinningNumbers();
                List<Integer> numbers = validateLottoIntegers(input);
                winningLotto = new Lotto(numbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningLotto;
    }

    public static int getBonusNumber() {
        int bonus;
        while (true) {
            try {
                outputView.printBonusInputRequest();
                String input = inputView.getBonusNumber();
                validateBonusNumber(input);
                bonus = Integer.parseInt(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonus;
    }

    public static List<Integer> validateLottoIntegers(String input) {
        String[] numberStrings = input.split(",");
        List<Integer> integers = new ArrayList<>();

        for (String numberString : numberStrings) {
            if (!LottoUtil.isInteger(numberString)) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_LOTTO_NUMBER);
            }
            integers.add(Integer.parseInt(numberString));
        }
        return integers;
    }

    public static void validateBuyingPrice(String input) {
        if (!LottoUtil.isInteger(input)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BUYING_PRICE);
        }
        if (Integer.parseInt(input) < 0) {
            throw new IllegalArgumentException(ExceptionMessage.NEGATIVE_BUYING_PRICE);
        }
        if (Integer.parseInt(input) % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.BUYING_PRICE_NOT_DIVISIBLE);
        }
    }

    public static void validateBonusNumber(String input) {
        if (!LottoUtil.isInteger(input)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BONUS_NUMBER);
        }
        if (!LottoUtil.isInRange(Integer.parseInt(input), LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE_BONUS_NUMBER);
        }
    }
}
