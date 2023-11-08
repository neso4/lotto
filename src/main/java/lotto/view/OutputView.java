package lotto.view;


import java.util.List;
import lotto.domain.Lottos;
import lotto.dto.ResultResponse;

public class OutputView {

    public void printPurchaseGuideMessage() {
        System.out.println(GuideMessage.PURCHASE_GUIDE_MESSAGE.getText());
    }

    public void printWinningNumbersGuideMessage() {
        System.out.println(GuideMessage.WINNING_NUMBERS_GUIDE_MESSAGE.getText());
    }

    public void printBonusNumberGuideMessage() {
        System.out.println(GuideMessage.BONUS_NUMBER_GUIDE_MESSAGE.getText());
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos);
    }

    public void printLottoCount(int lottoCount) {
        System.out.printf(GuideMessage.LOTTO_COUNT_GUIDE_MESSAGE.getText(), lottoCount);
    }

    public void printBenefitRate(double benefitRate) {
        System.out.printf(LottoResultConstants.BENEFIT_FORMAT.getText(), benefitRate);
    }

    public void printLottoResult(List<ResultResponse> resultResponses) {
        for (ResultResponse resultResponse : resultResponses) {
            System.out.print(resultResponse.toString());
        }
    }

    public void printResultGuideMessage() {
        System.out.println(GuideMessage.RESULT_GUIDE_MESSAGE.getText());
        System.out.println(GuideMessage.SPLIT_GUIDE_MESSAGE.getText());
    }
}
