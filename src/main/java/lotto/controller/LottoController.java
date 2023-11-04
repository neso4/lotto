package lotto.controller;

import static lotto.model.LottoConstant.FIFTH_PRIZE_MATCH;
import static lotto.model.LottoConstant.FIRST_PRIZE_MATCH;
import static lotto.model.LottoConstant.GOAL_MATCH_POINT;
import static lotto.model.LottoConstant.LOTTO_NUMBERS_SIZE;
import static lotto.model.LottoConstant.SECOND_PRIZE_MATCH;
import static lotto.model.Point.FIRST_POINT;
import static lotto.model.Point.SECOND_POINT;

import lotto.model.BonusNumber;
import lotto.model.GoalNumbers;
import lotto.model.Lotto;
import lotto.model.LottoCompany;
import lotto.model.Prize;
import lotto.model.dto.LottoResponse;
import lotto.model.judge.BonusNumberJudge;
import lotto.model.judge.GoalNumberJudge;
import lotto.model.judge.LottoJudge;
import lotto.service.InvestorService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        InvestorService investorService = initInvestorService();
        List<Lotto> lottos = investorService.buyLottos();
        outputView.printBoughtLottoSize(lottos.size());

        printLottoValues(lottos);

        LottoCompany lottoCompany = initLottoCompany();

        LottoJudge goalJudge = initGoalNumberJudge();
        LottoJudge bonusJudge = initBonusNumberJudge();
        for (int i = FIFTH_PRIZE_MATCH.getValue(); i < FIRST_PRIZE_MATCH.getValue(); i++) {
            List<Lotto> matchLotto = goalJudge.collectLottoWithMatchSize(lottos, i);
            Prize prize = Prize.findByPoint(i * GOAL_MATCH_POINT.getValue());
            investorService.addProfit(prize.getMoney() * matchLotto.size());
            outputView.printEachPrize(prize.getCondition(), prize.getMoney(), matchLotto.size());
        }

        List<Lotto> secondMatchLotto = bonusJudge.collectLottoWithMatchSize(lottos, SECOND_PRIZE_MATCH.getValue());
        List<Lotto> secondResultLotto = goalJudge.collectLottoWithMatchSize(secondMatchLotto, SECOND_PRIZE_MATCH.getValue());
        Prize secondPrize = Prize.findByPoint(SECOND_POINT.getPoint());
        investorService.addProfit(secondPrize.getMoney() * secondResultLotto.size());
        outputView.printEachPrize(secondPrize.getCondition(), secondPrize.getMoney(), secondResultLotto.size());

        List<Lotto> firstLotto = goalJudge.collectLottoWithMatchSize(lottos, LOTTO_NUMBERS_SIZE.getValue());
        Prize prize = Prize.findByPoint(FIRST_POINT.getPoint());
        investorService.addProfit(prize.getMoney() * firstLotto.size());
        outputView.printEachPrize(prize.getCondition(), prize.getMoney(), firstLotto.size());

        outputView.printProfitRate(investorService.calculateProfitRate());
    }

    private LottoCompany initLottoCompany() {
        GoalNumbers goalNumbers = initGoalNumbers();
        BonusNumber bonusNumber = initBonusNumber();

        return LottoCompany.of(goalNumbers, bonusNumber);
    }

    private GoalNumbers initGoalNumbers() {
        outputView.askGoalNumbers();
        String goalNumbersInput = inputView.readLine();
        return GoalNumbers.from(goalNumbersInput);
    }

    private BonusNumber initBonusNumber() {
        outputView.askBonusNumber();
        String bonusNumberInput = inputView.readLine();
        return BonusNumber.from(bonusNumberInput);
    }

    private InvestorService initInvestorService() {
        outputView.askInvestMoney();
        String investorInput = inputView.readLine();

        return InvestorService.createDefault(investorInput);
    }

    private void printLottoValues(final List<Lotto> lottos) {
        List<LottoResponse> lottoResponses = convertLottoResponses(lottos);
        outputView.printEachLottoNumbers(lottoResponses);
    }

    private List<LottoResponse> convertLottoResponses(final List<Lotto> lottos) {
        return lottos.stream()
                .map(lotto -> LottoResponse.from(lotto.getNumbers()))
                .toList();
    }

    private GoalNumberJudge initGoalNumberJudge() {
        outputView.askGoalNumbers();
        String goalNumbersInput = inputView.readLine();
        return GoalNumberJudge.from(goalNumbersInput);
    }

    private BonusNumberJudge initBonusNumberJudge() {
        outputView.askBonusNumber();
        String bonusNumberInput = inputView.readLine();
        return BonusNumberJudge.from(bonusNumberInput);
    }
}
