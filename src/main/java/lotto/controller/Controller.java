package lotto.controller;

import lotto.Lotto;
import lotto.model.Result;
import lotto.view.InputView;
import lotto.utility.Utility;
import lotto.validation.Validation;
import lotto.model.Model;
import lotto.view.OutputView;
import java.util.List;

public class Controller {
    public Controller() {
        InputView inputView = new InputView();
        Utility utility = new Utility();
        Validation validation = new Validation();
        Model model = new Model();
        OutputView outputView = new OutputView();

        // 구입 금액 입력 받고 정수형으로 변환 및 검증
        String paymentInput = inputView.getPayment();
        int payment = utility.parseInt(paymentInput);
        validation.validateUnit(payment);

        List<Lotto> lottos = model.buyLotto(payment);

        outputView.printAllLottoNumbers(lottos);

        // 당첨 번호 입력 받고, 분리, 정수형 리스트로 변환 및 검증
        String winningInput = inputView.getWinningNumbers();
        String[] splitResult = utility.splitInput(winningInput);
        List<Integer> winningNumbers = utility.getIntList(splitResult);
        validation.validateSize(winningNumbers);
        validation.validateRange(winningNumbers);
        validation.validateDuplication(winningNumbers);

        // 보너스 번호 입력 받고, 정수형으로 변환 및 검증
        String bonusNumberInput = inputView.getBonusNumber();
        int bonusNumber = utility.parseInt(bonusNumberInput);

        // [일치하는 숫자 개수][보너스 번호 일치 여부]
        // 보너스 번호 일치 여부는 기본적으로 0을 사용합니다. 일치하는 숫자가 5개인 경우에만, 0은 보너스 번호 불일치, 1은 보너스 번호 일치의 결과를 저장했습니다.
        int[][] totalResult = new int[7][2];
        for(Lotto lotto : lottos) {
            Result currentResult = model.compareNumbers(lotto, winningNumbers, bonusNumber);
            model.updateResult(totalResult, currentResult);
        }

        outputView.printTotalResult(totalResult);

        long totalPrize = utility.getProfit(totalResult);
        System.out.println(totalPrize);
    }
}
