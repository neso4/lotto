package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoService {
    Validator validator = new Validator();

    static final int START_NUMBER = 1;
    static final int END_NUMBER = 45;
    static final int COUNT_NUMBER = 6;

    int getInput() {
        int parsedInput;

        String input = Console.readLine();
        parsedInput = validator.validateInput(input);

        return parsedInput;
    }

    List<Integer> getInputForNumbers() {
        List<Integer> parsedInput = new ArrayList<>();

        String input = Console.readLine();

        List<String> splitedInput = Arrays.asList(input.split(","));

        List<String> validatedInput = validator.validateInputForNumbers(splitedInput, COUNT_NUMBER);

        List<String> noDuplicatedInput = validator.checkDuplicationInputForNumbers(splitedInput);

        for (int i = 0; i < noDuplicatedInput.size(); i++) {
            parsedInput.add(Integer.parseInt(noDuplicatedInput.get(i)));
        }

        return parsedInput;
    }

    int getTicket(int cash) {
        int ticket;

        if (cash % 1000 != 0) {
            throw new IllegalArgumentException();
        }

        ticket = cash / 1000;

        return ticket;
    }

    List<Integer> getRandomNumbers() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, COUNT_NUMBER);

        return randomNumbers;
    }

    Lotto getLotto() {
        List<Integer> randomNumbers = getRandomNumbers();

        Lotto lotto = new Lotto(randomNumbers);

        return lotto;
    }

    List<Lotto> getLottoBundle(int ticket) {
        List<Lotto> lottoBundle = new ArrayList<>();

        for (int i = 0 ; i < ticket; i++) {
            Lotto lotto = getLotto();
            lottoBundle.add(lotto);
        }

        return lottoBundle;
    }

    List<Prize> getWinningStatistics(List<Integer> winningNumbers, List<Lotto> lottoBundle) {
        List<Prize> winningStatistics = new ArrayList<>();

        for (int i = 0; i < lottoBundle.size(); i++) {
            Lotto lotto = lottoBundle.get(i);
            List<Integer> lottoNumbers = lotto.getNumbers();
            Prize prize = compareWinningNumbersWithLottoNumbers(winningNumbers, lottoNumbers);
            winningStatistics.add(prize);
        }

        return winningStatistics;
    }

    Prize compareWinningNumbersWithLottoNumbers(List<Integer> winningNumbers, List<Integer> lottoNumbers) {
        int generalCount = 0;
        int bonusCount = 0;
        int bonusNumberIndex = winningNumbers.size() - 1;
        int bonusNumber = winningNumbers.get(bonusNumberIndex);

        generalCount += countGeneralNumber(winningNumbers, lottoNumbers);
        bonusCount += countBonusNumber(bonusNumber, lottoNumbers);

        Prize prize = selectPrize(generalCount, bonusCount);

        return prize;
    }

    int countGeneralNumber(List<Integer> winningNumbers, List<Integer> lottoNumbers) {
        int generalCount = 0;
        int bonusNumberIndex = winningNumbers.size() - 1;

        for (int i = 0; i < bonusNumberIndex; i++) {
            if (lottoNumbers.contains(winningNumbers.get(i))) {
                generalCount++;
            }
        }

        return generalCount;
    }

    int countBonusNumber(int bonusNumber, List<Integer> lottoNumbers) {
        int bonusCount = 0;

        if (lottoNumbers.contains(bonusNumber)) {
            bonusCount++;
        }

        return bonusCount;
    }

    Prize selectPrize(int generalCount, int bonusCount) {
        if (bonusCount < 1) {
            if (generalCount == 3) {
                return Prize.FIFTH;
            }
            if (generalCount == 4) {
                return Prize.FOURTH;
            }
            if (generalCount == 5) {
                return Prize.THIRD;
            }
            if (generalCount == 6) {
                return Prize.FIRST;
            }
        }

        if (bonusCount == 1 && generalCount == 5) {
            return Prize.SECOND;
        }

        return Prize.NONE;
    }

    float getRateOfReturn(int cash, List<Prize> winningStatistics) {
        float rateOfReturn;
        int prize = 0;

        for (int i = 0; i < winningStatistics.size(); i++) {
            prize += winningStatistics.get(i).getPrize();
        }

        rateOfReturn = (float) prize / cash * 100;

        return rateOfReturn;
    }
}