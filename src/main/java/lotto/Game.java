package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import ui.Input;
import ui.Output;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final Input input = new Input();
    private final Output output = new Output();
    private int price;
    private int bonusNumber;
    private List<Integer> winningNumbers = new ArrayList<>();
    private List<Lotto> lottos = new ArrayList<>();
    private Map<MatchState, Integer> totalMatchCounts = new LinkedHashMap<>();
    private Error errorState = Error.NO_PROBLEM;


    public void run() {
        setPrice();
        output.printNumberOfLotto(price);
        generateLotto();
        setWinningNumbers();
        setBonusNumber();
        setTotalMatchCounts();
        output.printResult(totalMatchCounts);
        output.printRateOfReturn(getRateOfReturn());
    }

    private float getRateOfReturn() {
        float totalWinningPrize = totalMatchCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        return Math.round(totalWinningPrize * 10000.0f / price) / 100.0f;
    }

    private void generateLotto() {
        int numberOfLotto = price / 1000;
        int index = 0;
        while (lottos.size() < numberOfLotto){
            lottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)));
            lottos.get(index).printLottoNumbers();
            index++;
        }
    }

    private void setTotalMatchCounts() {
        lottos.forEach(lotto -> {
            MatchState matchState = MatchState.getMatchState(lotto.getMatchCount(winningNumbers), lotto.checkBonusNumber(bonusNumber));
            if (matchState != null) {
                totalMatchCounts.merge(matchState, 1, Integer::sum);
            }
        });
        sortTotalMatchCounts();
    }

    private void sortTotalMatchCounts() {
        totalMatchCounts = totalMatchCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(MatchState::getMatchCount)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private void setPrice() {
        do {
            try {
                price = input.getPrice();
                validatePrice();
            } catch (IllegalArgumentException e) {
                errorState = Error.printERROR(errorState);
            }
        } while (!errorState.equals(Error.NO_PROBLEM));
    }

    private void setWinningNumbers() {
        do  {
            try {
                winningNumbers = input.getWinningNumbers();
                validateWinningNumbers();
            } catch (IllegalArgumentException e) {
                errorState = Error.printERROR(errorState);
            }
        } while(!errorState.equals(Error.NO_PROBLEM));
    }

    private void setBonusNumber() {
        do {
            try {
                bonusNumber = input.getBonusNumber();
                validateBonusNumber();
            } catch (IllegalArgumentException e) {
                errorState = Error.printERROR(errorState);
            }
        } while (!errorState.equals(Error.NO_PROBLEM));
    }

    private void validatePrice() {
        if (price % 1000 != 0) {
            errorState = Error.PRICE_ERROR;
            throw new IllegalArgumentException();
        }
        errorState = Error.NO_PROBLEM;
    }

    private void validateWinningNumbers() {
        if (winningNumbers.size() != 6) {
            errorState = Error.WINNING_NUMBER_OUT_OF_COUNT_ERROR;
            throw new IllegalArgumentException();
        } else if (winningNumbers.size() > winningNumbers.stream().distinct().count()) {
            errorState = Error.WINNING_NUMBER_DUPLICATE_ERROR;
            throw new IllegalArgumentException();
        } else if (winningNumbers.stream().anyMatch(number -> number < 1 || number > 45)) {
            errorState = Error.WINNING_NUMBER_OUT_OF_RANGE_ERROR;
            throw new IllegalArgumentException();
        }
        errorState = Error.NO_PROBLEM;
    }

    private void validateBonusNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            errorState = Error.BONUS_NUMBER_DUPLICATE_ERROR;
            throw new IllegalArgumentException();
        } else if (bonusNumber < 1 || bonusNumber > 45) {
            errorState = Error.BONUS_NUMBER_OUT_OF_RANGE_ERROR;
            throw new IllegalArgumentException();
        }
        errorState = Error.NO_PROBLEM;
    }
}
