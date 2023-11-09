package lotto.controller;

import lotto.constants.Grade;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.WinningInformation;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.PointCalculator;
import lotto.domain.PointResult;
import lotto.utils.Parser;
import lotto.utils.RandomUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        PointResult pointResult = generatePointResult(money.getLottoPurchaseCount());
        printLottoResult(money, pointResult);
    }

    private PointResult generatePointResult(int lottoPurchaseCount) {
        List<Lotto> lottos = generateLottoWithCount(lottoPurchaseCount);
        WinningInformation winningInformation = generateWinningInformation();
        List<Double> lottoPointResult = getLottoPointResult(lottos, winningInformation);
        return new PointResult(lottoPointResult);
    }

    private List<Lotto> generateLottoWithCount(int count) {
        OutputView.printLottoPurchaseCount(count);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        List<LottoNumber> randomLottoNumbers = RandomUtils.generateRandomLottoNumber().stream()
                .sorted()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(randomLottoNumbers);
        System.out.println(lotto.getGeneratedLottoString());
        return lotto;
    }

    private Money generateMoney() {
        OutputView.printMoneyInputMessage();
        return new Money(Integer.parseInt(InputView.inputNaturalNumber()));
    }

    private WinningInformation generateWinningInformation() {
        OutputView.printAnswerLottoNumberInputMessage();
        Lotto answerLotto = new Lotto(Parser.stringToLottoNumbers(InputView.inputAnswerLotto()));
        LottoNumber bonusNumber = createBonusNumber();
        return new WinningInformation(answerLotto, bonusNumber);
    }

    private LottoNumber createBonusNumber() {
        OutputView.printBonusNumberInputMessage();
        return LottoNumber.of(Integer.parseInt(InputView.inputNaturalNumber()));
    }

    private List<Double> getLottoPointResult(List<Lotto> lottos, WinningInformation winningInformation) {
        PointCalculator pointCalculator = new PointCalculator();
        List<Double> lottoPointResult = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoPointResult.add(pointCalculator.calculateTotalPoint(lotto, winningInformation));
        }
        return lottoPointResult;
    }

    private void printLottoResult(Money money, PointResult pointResult) {
        OutputView.printLottoResultMessage();
        OutputView.printHorizontalRule();
        for (Grade grade : Grade.values()) {
            OutputView.printResult(grade, pointResult.getCountByGrade(grade));
        }
        OutputView.printEarningRate(pointResult.calculateEarningRate(money));
    }
}
