package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.View.Output;

import java.util.*;

import static lotto.Model.Rank.*;

public class Lotto {
    public final static int START_NUMBER = 1;
    public final static int END_NUMBER = 45;
    final static int COUNT_NUMBER = 6;
    static int lottoCnt;
    private final List<Integer> numbers;


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
        Set<Integer> set = new HashSet<>();
        for (Integer element : numbers) {
            if (!set.add(element)) {
                throw new IllegalArgumentException("[ERROR] 중복된 숫자가 있습니다.");
            }
        }
    }

    // TODO: 추가 기능 구현
    public static int issuanceLotto(int money) {
        lottoCnt = money / 1000;
        return lottoCnt;
    }


    public static List<List<Integer>> getLottoBundle() {
        List<List<Integer>> tickets = new ArrayList<>();
        for (int i = 0; i < lottoCnt; i++) {
            tickets.add(getRandomNumbers());
        }
        Output.checkLottoNumberMessage(tickets);
        return tickets;
    }


    public static List<Integer> getRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, COUNT_NUMBER));
        Collections.sort(randomNumbers);
        return randomNumbers;
    }


    public static void getTwoNumbers(List<Integer> winningNumbers, List<List<Integer>> userNumbersBundle, int bonus) {
        int[] equalNumCount;
        for (List<Integer> userNumbersUnit : userNumbersBundle) {
            equalNumCount = compareNumbers(winningNumbers, userNumbersUnit, bonus);
            result(equalNumCount);
        }
    }

    public static int[] compareNumbers(List<Integer> winningNumbers, List<Integer> userNumberUnit, int bonus) {
        int cnt = 0;
        int bonusCnt = 0;
        int[] cntFile = new int[2];
        for (int winningNumber : winningNumbers) {
            if (userNumberUnit.contains(winningNumber)) {
                cnt++;
            }
        }
        if (cnt == 5) {
            if (userNumberUnit.contains(bonus)) {
                bonusCnt++;
            }
        }
        cntFile[0] = cnt;
        cntFile[1] = bonusCnt;
        return cntFile;
    }

    public static void result(int[] equalNumCount) {

        if (equalNumCount[0] == 3) {
            FIFTH.setCnt();
        } else if (equalNumCount[0] == 4) {
            FOURTH.setCnt();
        } else if (equalNumCount[0] == 5) {
            if (equalNumCount[1] == 0) {
                THIRD.setCnt();
            } else if (equalNumCount[1] == 1) {
                SECOND.setCnt();
            }
        } else if (equalNumCount[0] == 6) {
            FIRST.setCnt();
        }
    }
}
