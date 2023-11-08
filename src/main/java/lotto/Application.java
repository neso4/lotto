package lotto;

import lotto.constants.Rank;
import lotto.domain.Calculator;
import lotto.domain.Controller;
import lotto.domain.Convertor;
import lotto.domain.Validator;
import lotto.model.GameData;
import lotto.model.GameStatistics;
import lotto.model.Lotto;
import lotto.view.View;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import static lotto.constants.Notice.*;

public class Application {
    public static void main(String[] args) {

        GameData gameData = new GameData();
        GameStatistics gameStatistics = new GameStatistics();

        //게임 초기화
        View.printMessage(ASK_BUDGET);
        String lottoBudgetInput;
        Integer budget;

        //로또 금액 입력 및 검증
        while (true) {
            lottoBudgetInput = View.getUserInput();
            if (Validator.isEmpty_(lottoBudgetInput)) {
                continue;
            }
            if (Validator.isNumberOnly(lottoBudgetInput)) {
                continue;
            }
            budget = Convertor.convert(lottoBudgetInput);
            if (Validator.isUnder1000(budget)) {
                continue;
            }
            if (Validator.isIndivisible(budget)) {
                continue;
            }
            break;
        }

        int lotteryCount = Calculator.calculateCount(budget);
        View.printLotteryCount(lotteryCount);

        //로또 생성
        gameData.generateLottos();
        List<Lotto> lottos = gameData.getLottos();

        for (int i = 0; i < lotteryCount; i++) {
            List<Integer> lotteryNumbers = Lotto.generateLotteryNumbers();
            Controller.sortNumbers(lotteryNumbers);
            lottos.add(new Lotto(lotteryNumbers));
        }
        for (Lotto lotto : lottos) {
            List<Integer> numbers = lotto.getNumbers();
            View.printLotteryNumbers(numbers);
        }
        System.out.println();


        //게임 진행
        //당첨 번호 입력
        View.printMessage(ASK_WINNING_NUMBERS);
        String winningNumbersInput;
        String[] winningNumbersAfterSplit;
        List<Integer> winningNumbersTemp;

        //당첨 번호 검증
        while (true) {
            winningNumbersInput = View.getUserInput();
            if (Validator.isEmpty_(winningNumbersInput)) {
                continue;
            }
            if (Validator.isValidCharacter(winningNumbersInput)) {
                continue;
            }

            winningNumbersAfterSplit = Convertor.split(winningNumbersInput);
            if (Validator.are_OnlyNumber_(winningNumbersAfterSplit)) {
                continue;
            }

            winningNumbersTemp = Convertor.convertGathering(winningNumbersAfterSplit);
            if (Validator.isCountOutOfRange(winningNumbersTemp)) {
                continue;
            }
            if (Validator.areOutOfRange(winningNumbersTemp)) {
                continue;
            }
            if (Validator.areDuplicated(winningNumbersTemp)) {
                continue;
            }
            break;
        }
        gameData.setWinningNumbers(winningNumbersTemp);

        //보너스 번호 입력
        View.printMessage(ASK_BONUS_NUMBERS);
        String bonusNumberInput;
        Integer bonusNumberTemp;

        //보너스 번호 검증
        while (true) {
            bonusNumberInput = View.getUserInput();
            if (Validator.isEmpty_(bonusNumberInput)) {
                continue;
            }
            if (Validator.isNumberOnly(bonusNumberInput)) {
                continue;
            }

            bonusNumberTemp = Convertor.convert(bonusNumberInput);
            if (Validator.isOutOfRange(bonusNumberTemp)) {
                continue;
            }

            List<Integer> winningNumbers = gameData.getWinningNumbers();
            if (Validator.isBonusNumberDuplicate(bonusNumberTemp, winningNumbers)) {
                continue;
            }
            break;
        }

        gameData.setBonusNumber(bonusNumberTemp);


        //게임 종료
        //당첨 통계 산출
        List<Integer> winningNumbers = gameData.getWinningNumbers();
        gameStatistics.generateMatchingCounts();
        List<Integer> matchingCounts = gameStatistics.getMatchingCounts();
        gameStatistics.generateLotteryRanks();
        List<Rank> lotteryRanks = gameStatistics.getLotteryRanks();

        Controller.fillMatchingCounts(lottos, winningNumbers, matchingCounts);
        Controller.fillRanks(lotteryRanks, matchingCounts);

        Integer bonusNumber = gameData.getBonusNumber();
        for (int i = 0; i < lotteryRanks.size(); i++) {
            boolean bonusFlag;
            Rank rank = lotteryRanks.get(i);

            //3등(번호 5개 일치)인 경우, 보너스 번호 일치 여부 확인 / 2등으로 전환
            if (rank.equals(Rank.THIRD)) {
                Lotto secondRankCandidateLotto = lottos.get(i);
                List<Integer> secondRankCandidateLottoNumbers = secondRankCandidateLotto.getNumbers();
                bonusFlag = Controller.bonusNumberFlag(secondRankCandidateLottoNumbers, bonusNumber);
                Controller.changeRankByBonusNumber(lotteryRanks, bonusFlag, i);
            }
        }

        //당첨 통계 출력
        View.printMessage(GAME_STATISTICS);
        View.printMessage(SEPARATE_LINE);

        for (Rank rank : Rank.values()) {
            DecimalFormat df = new DecimalFormat("###,###");
            Integer matchingCount = Collections.frequency(lotteryRanks, rank);
            if (rank.equals(Rank.FAIL)) {
                continue;
            }
            if (rank.equals(Rank.SECOND)) {
                View.print2ndRankPrize(rank, matchingCount, df);
            }
            if (!(rank.equals(Rank.SECOND))) {
                View.printPrize(rank, matchingCount, df);
            }
        }

        Integer totalPrize = Calculator.calculateTotalPrize(lotteryRanks);
        Double pricePrizeRatio = Calculator.calculatePricePrizeRatio(totalPrize, budget);
        View.printPricePrizeRatio(pricePrizeRatio);
    }
}
