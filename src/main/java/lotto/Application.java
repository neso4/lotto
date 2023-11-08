package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Application {
    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static int convertInteger(String input) {
        return Integer.parseInt(input);
    }

    public static void validateBudget(int budget) {
        boolean isNotMultipleOf1000 = budget % 1000 != 0;
        if (isNotMultipleOf1000) {
            throw new IllegalArgumentException();
        }
    }

    public static int inputBudget() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                int input = convertInteger(Console.readLine());
                validateBudget(input);
                return input;
            } catch (IllegalArgumentException e) {
                error("로또 구입 금액은 1,000 단위의 숫자여야 합니다.");
            }
        }
    }

    public static List<Integer> getRandomLottoNumber() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public static Lotto getRandomLotto() {
        return new Lotto(getRandomLottoNumber());
    }

    public static List<Lotto> buyLottos(int budget) {
        int toBuy = budget / 1000;
        List<Lotto> lottos = IntStream.rangeClosed(1, toBuy)
                .mapToObj(i -> getRandomLotto())
                .toList();
        System.out.println(toBuy + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(lotto.toString()));
        return lottos;
    }

    public static Lotto inputWinningLotto() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String[] input = Console.readLine().split(",");
                List<Integer> winningNumbers = Arrays.stream(input)
                        .map(Application::convertInteger)
                        .toList();
                return new Lotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                error("로또 번호는 쉼표로 구분된 1부터 45 사이의 숫자 6개여야 합니다.");
            }
        }
    }

    public static void validateBonusNumber(int bonusNumber) {
        boolean isNotInLottoNumberRange = bonusNumber < 1 || 45 < bonusNumber;
        if (isNotInLottoNumberRange) {
            throw new IllegalArgumentException();
        }
    }

    public static int inputBonusNumber() {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                int input = convertInteger(Console.readLine());
                validateBonusNumber(input);
                return input;
            } catch (IllegalArgumentException e) {
                error("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    public static long countResultByType(List<LottoResult> results, LottoResult type) {
        return results.stream()
                .filter(result -> result.equals(type))
                .count();
    }

    public static long getTotalPrize(List<LottoResult> results) {
        long totalPrize = 0;
        for (LottoResult resultType : LottoResult.values()) {
            if (resultType.equals(LottoResult.NONE))
                continue;
            long count = countResultByType(results, resultType);
            System.out.printf("%s (%,d원) - %d개\n", resultType.getDescription(), resultType.getPrize(), count);
            totalPrize += count * resultType.getPrize();
        }
        return totalPrize;
    }

    public static void printProfitRate(long totalPrize, long budget) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", (double) totalPrize / budget * 100);
    }

    public static void main(String[] args) {
        int budget = inputBudget();
        List<Lotto> lottos = buyLottos(budget);
        Lotto winningLotto = inputWinningLotto();
        int bonusNumber = inputBonusNumber();
        List<LottoResult> lottoResults = lottos.stream()
                .map(lotto -> lotto.getLottoResult(winningLotto, bonusNumber))
                .toList();
        long totalPrize = getTotalPrize(lottoResults);
        printProfitRate(totalPrize, budget);
    }
}
