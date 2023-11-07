package lotto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LottoController {
    private final Input input = new Input();
    private final Output output = new Output();

    public void run() {
        LottoCost lottoCost = createLottoCost();
        int lottoTicket = lottoCost.getNumberOfLotto();
        output.printNumberOfPurchase(lottoTicket);

        LottoGenerate lottoGenerate = new LottoGenerate(lottoTicket);
        HashMap<Integer, List<Integer>> randomLottoNumbers = lottoGenerate.getRandomLottoNumbers();
        output.printRandomLottoNumbers(randomLottoNumbers);

        Lotto lotto = createWinningNumber();
        LottoBonus lottoBonus = createBonusNumber(lotto);
        HashMap<Integer, List<Integer>> compareLottoNumResult = compareLottoNumber(lottoGenerate, lotto, lottoBonus);
        printResult(checkPrizeByLotto(compareLottoNumResult), lottoCost.getCost());
    }

    private LottoCost createLottoCost() {
        LottoCost lottoCost = null;
        boolean loop = true;

        while (loop) {
            String cost = input.buyLotto();
            try {
                lottoCost = new LottoCost(toNumber(cost));
                loop = false;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        return lottoCost;
    }

    private int toNumber(String inputValue) {
        isNumber(inputValue);
        return Integer.parseInt(inputValue);
    }

    private void isNumber(String number) {
        boolean hasOnlyNum = !number.isEmpty() && number.chars().allMatch(Character::isDigit);

        if (!hasOnlyNum) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_NOT_NUMBER.getMessage());
        }
    }

    private Lotto createWinningNumber() {
        Lotto lotto = null;
        boolean loop = true;

        while (loop) {
            List<String> inputWinningNumbers = Arrays.asList(input.winningNumbers().split(Config.SPLIT_SYMBOL));
            try {
                isNumber(inputWinningNumbers);
                lotto = new Lotto(inputWinningNumbers.stream().map(Integer::valueOf).toList());
                loop = false;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        return lotto;
    }

    public void isNumber(List<String> numbers) {
        boolean hasOnlyNum;

        for (String number : numbers) {
            hasOnlyNum = !number.isEmpty() && number.chars().allMatch(Character::isDigit);
            if (!hasOnlyNum) {
                throw new IllegalArgumentException(ErrorMessages.ERROR_NOT_NUMBER.getMessage());
            }
        }
    }

    private LottoBonus createBonusNumber(Lotto lotto) {
        LottoBonus lottoBonus = null;
        boolean loop = true;

        while (loop) {
            String inputBonusNumber = input.bonusNumber();
            try {
                int bonusNumber = toNumber(inputBonusNumber);
                hasSameNumberBetweenWinningAndBonusNumber(lotto.getLotto(), bonusNumber);
                lottoBonus = new LottoBonus(bonusNumber);
                loop = false;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        return lottoBonus;
    }

    private void hasSameNumberBetweenWinningAndBonusNumber(List<Integer> winingNumber, int bonusNumber) {
        if (winingNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    private void printResult(HashMap<String, Integer> countByPrize, int lottoCost) {
        System.out.println(output.printResultMessage());
        printFifthPrizeCount(countByPrize);
        printFourthPrizeCount(countByPrize);
        printThirdPrizeCount(countByPrize);
        printSecondPrizeCount(countByPrize);
        printFirstPrizeCount(countByPrize);
        printReturnRate(countByPrize, lottoCost);
    }

    private void printReturnRate(HashMap<String, Integer> countByPrize, int lottoCost) {
        System.out.printf(output.printReturnRate(), getReturnRate(countByPrize, lottoCost));
    }

    private float getReturnRate(HashMap<String, Integer> countByPrize, int lottoCost) {
        int totalFirstReward = rewardToNumber(Config.FIRST_PRIZE_REWARD)
                * countByPrize.get(Config.FIRST_PRIZE_REWARD);
        int totalSecondReward = rewardToNumber(Config.SECOND_PRIZE_REWARD)
                * countByPrize.get(Config.SECOND_PRIZE_REWARD);
        int totalThirdReward = rewardToNumber(Config.THIRD_PRIZE_REWARD)
                * countByPrize.get(Config.THIRD_PRIZE_REWARD);
        int totalFourthReward = rewardToNumber(Config.FIFTH_PRIZE_REWARD)
                * countByPrize.get(Config.FOURTH_PRIZE_REWARD);
        int totalFifthReward = rewardToNumber(Config.FIFTH_PRIZE_REWARD)
                * countByPrize.get(Config.FIFTH_PRIZE_REWARD);

        int totalReward = totalFirstReward + totalSecondReward + totalThirdReward
                + totalFourthReward + totalFifthReward;

        return (float) totalReward / lottoCost * Config.PERCENTAGE;
    }

    private int rewardToNumber(String reward) {
        return Integer.parseInt(reward.replace(Config.THOUSANDS_SEPARATOR, ""));
    }

    private void printFirstPrizeCount(HashMap<String, Integer> countByPrize) {
        System.out.printf(output.printCompareLottoNumberResultExceptSecondPrize(),
                Config.FIRST_PRIZE_HAS_WINNING, Config.FIRST_PRIZE_REWARD,
                countByPrize.get(Config.FIRST_PRIZE_REWARD));
    }

    private void printSecondPrizeCount(HashMap<String, Integer> countByPrize) {
        System.out.printf(output.printCompareLottoNumberResultSecondPrize(),
                Config.SECOND_PRIZE_HAS_WINNING, Config.SECOND_PRIZE_REWARD,
                countByPrize.get(Config.SECOND_PRIZE_REWARD));
    }

    private void printThirdPrizeCount(HashMap<String, Integer> countByPrize) {
        System.out.printf(output.printCompareLottoNumberResultExceptSecondPrize(),
                Config.THIRD_PRIZE_HAS_WINNING, Config.THIRD_PRIZE_REWARD,
                countByPrize.get(Config.THIRD_PRIZE_REWARD));
    }

    private void printFourthPrizeCount(HashMap<String, Integer> countByPrize) {
        System.out.printf(output.printCompareLottoNumberResultExceptSecondPrize(),
                Config.FOURTH_PRIZE_HAS_WINNING, Config.FOURTH_PRIZE_REWARD,
                countByPrize.get(Config.FOURTH_PRIZE_REWARD));
    }

    private void printFifthPrizeCount(HashMap<String, Integer> countByPrize) {
        System.out.printf(output.printCompareLottoNumberResultExceptSecondPrize(),
                Config.FIFTH_PRIZE_HAS_WINNING, Config.FIFTH_PRIZE_REWARD,
                countByPrize.get(Config.FIFTH_PRIZE_REWARD));
    }

    //이 아래는 추후 LottoService 클래스로 이동(일부 메서드 접근 제어자 수정 필요)
    private HashMap<String, Integer> checkPrizeByLotto(HashMap<Integer, List<Integer>> countMatchByLotto) {
        HashMap<String, Integer> countByPrize = new HashMap<>();

        countByPrize.put(Config.FIRST_PRIZE_REWARD,
                countByMatchNumber(countMatchByLotto, Config.FIRST_PRIZE_HAS_WINNING));
        countByPrize.put(Config.SECOND_PRIZE_REWARD,
                countByMatchNumber(countMatchByLotto, Config.SECOND_PRIZE_HAS_WINNING, Config.SECOND_PRIZE_HAS_BONUS));
        countByPrize.put(Config.THIRD_PRIZE_REWARD,
                countByMatchNumber(countMatchByLotto, Config.THIRD_PRIZE_HAS_WINNING));
        countByPrize.put(Config.FOURTH_PRIZE_REWARD,
                countByMatchNumber(countMatchByLotto, Config.FOURTH_PRIZE_HAS_WINNING));
        countByPrize.put(Config.FIFTH_PRIZE_REWARD,
                countByMatchNumber(countMatchByLotto, Config.FIFTH_PRIZE_HAS_WINNING));

        return countByPrize;
    }

    private int countByMatchNumber(HashMap<Integer, List<Integer>> compareLottoNumbers, int countMatchWinning) {
        int count = 0;

        for (int key : compareLottoNumbers.keySet()) {
            List<Integer> compareNumbersResult = compareLottoNumbers.get(key);

            if (compareNumbersResult.get(Config.INDEX_WINNING_NUMBER) == countMatchWinning) {
                count++;
            }
        }

        return count;
    }

    private int countByMatchNumber(HashMap<Integer, List<Integer>> compareLottoNumbers,
                                   int countMatchWinning, int countMatchBonus) {
        int count = 0;

        for (int key : compareLottoNumbers.keySet()) {
            List<Integer> compareNumbersResult = compareLottoNumbers.get(key);

            if (compareNumbersResult.get(Config.INDEX_WINNING_NUMBER) == countMatchWinning
                    && compareNumbersResult.get(Config.INDEX_BONUS_NUMBER) == countMatchBonus) {
                count++;
            }
        }

        return count;
    }

    private HashMap<Integer, List<Integer>> compareLottoNumber(LottoGenerate lottoGenerate,
                                                               Lotto lotto, LottoBonus lottoBonus) {
        HashMap<Integer, List<Integer>> countSameNumbers = new HashMap<>();
        HashMap<Integer, List<Integer>> randomLottoNumbers = lottoGenerate.getRandomLottoNumbers();
        int winningNumberMatchCount; //countWinningInLotto
        int bonusNumberMatchCount; //countBonusInLotto

        for (int key : lottoGenerate.getRandomLottoNumbers().keySet()) {
            List<Integer> randomLottoNumber = randomLottoNumbers.get(key);
            winningNumberMatchCount = compareWinningAndLottoNumber(randomLottoNumber, lotto.getLotto());
            bonusNumberMatchCount = compareBonusAndLottoNumber(randomLottoNumber, lottoBonus.getBonusNumber());

            countSameNumbers.put(key, List.of(winningNumberMatchCount, bonusNumberMatchCount));
        }

        return countSameNumbers;
    }

    private int compareWinningAndLottoNumber(List<Integer> randomLottoNumber, List<Integer> winningNumber) {
        int matchCount = 0; //countSameNumber

        for (int number : randomLottoNumber) {
            if (winningNumber.contains(number)) {
                matchCount++;
            }
        }

        return matchCount;
    }

    private int compareBonusAndLottoNumber(List<Integer> randomLottoNumber, int bonusNumber) {
        int matchCount = 0; //countSameNumber

        for (int number : randomLottoNumber) {
            if (number == bonusNumber) {
                matchCount++;
            }
        }

        return matchCount;
    }
}
