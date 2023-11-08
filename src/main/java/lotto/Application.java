package lotto;

import lotto.domain.UserLotto;
import lotto.service.LottoCalculator;
import lotto.service.UserLottoGenerator;
import lotto.validation.LottoValidation;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        LottoValidation lottoValidation = new LottoValidation();
        UserLottoGenerator userLottoGenerator = new UserLottoGenerator();
        LottoCalculator lottoCalculator = new LottoCalculator();

        start(outputView, inputView, lottoValidation, userLottoGenerator, lottoCalculator);
    }

    // 기능 목록 1~6번 차례대로 실행
    private static void start(OutputView outputView, InputView inputView, LottoValidation lottoValidation, UserLottoGenerator userLottoGenerator, LottoCalculator lottoCalculator) {
        int purchaseAmount = ioPurchaseAmount(outputView, inputView, lottoValidation);
        List<UserLotto> userLottos = outputPurchasedUserLotto(purchaseAmount, outputView, userLottoGenerator);
        List<Integer> winningNumber = ioWinningNumber(outputView, inputView, lottoValidation);
        int bonusNumber = ioBonusNumber(outputView, inputView, lottoValidation, winningNumber);
        List<Integer> resultData = outputWinningStatistics(outputView, lottoCalculator, userLottos, winningNumber, bonusNumber);
        outputRateOfReturn(outputView, lottoCalculator, purchaseAmount, resultData);
    }

    // 수익률 계산 및 출력 기능
    private static void outputRateOfReturn(OutputView outputView, LottoCalculator lottoCalculator, int purchaseAmount, List<Integer> resultData) {
        double rateOfReturn = lottoCalculator.calculateRateOfReturn(purchaseAmount, resultData);
        outputView.printRateOfReturnMessage(rateOfReturn);
    }

    // 당첨 결과 계산 및 출력 기능
    private static List<Integer> outputWinningStatistics(OutputView outputView, LottoCalculator lottoCalculator, List<UserLotto> userLottos, List<Integer> winningNumber, int bonusNumber) {
        List<Integer> resultData = lottoCalculator.calculateLottoWin(userLottos, winningNumber, bonusNumber);
        outputView.printWinningStatisticsMessage(resultData);
        return resultData;
    }

    // 보너스 번호 입출력 기능
    private static int ioBonusNumber(OutputView outputView, InputView inputView, LottoValidation lottoValidation, List<Integer> winningNumber) {
        int bonusNumber = 0;
        outputView.printInputBonusNumberMessage();
        while (true) {
            try {
                bonusNumber = inputView.readBonusNumber(lottoValidation, winningNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }

    // 당첨 번호 입출력 기능
    private static List<Integer> ioWinningNumber(OutputView outputView, InputView inputView, LottoValidation lottoValidation) {
        List<Integer> winningNumber = new ArrayList<>();
        outputView.printInputWinningNumberMessage();
        while (true) {
            try {
                List<Integer> numbers = inputView.readWinningNumber(lottoValidation);
                Lotto lotto = new Lotto(numbers);
                winningNumber.addAll(lotto.getNumbers());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningNumber;
    }

    // 로또 수량 및 번호 발행 후 출력 기능
    private static List<UserLotto> outputPurchasedUserLotto(int purchaseAmount, OutputView outputView, UserLottoGenerator userLottoGenerator) {
        int lottoTicket = purchaseAmount / 1000;
        outputView.printInformPurchaseMessage(lottoTicket);
        List<UserLotto> userLottos = userLottoGenerator.generateUserLotto(lottoTicket);
        for (UserLotto userLotto : userLottos) {
            System.out.println(userLotto.getNumbers());
        }
        return userLottos;
    }

    // 로또 구입금액 입출력 기능
    private static int ioPurchaseAmount(OutputView outputView, InputView inputView, LottoValidation lottoValidation) {
        int purchaseAmount = 0;
        outputView.printInputPurchaseAmountMessage();
        while (true) {
            try {
                purchaseAmount = inputView.readPurchaseAmount(lottoValidation);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return purchaseAmount;
    }
}
