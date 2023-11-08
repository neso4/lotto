package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> purchasedLottoTickets = generateLottoTickets(purchaseAmount);
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber();

        List<Prize> prizes = calculatePrizes(purchasedLottoTickets, winningNumbers, bonusNumber);
        printResults(purchaseAmount, purchasedLottoTickets, prizes);
    }

    private static int getPurchaseAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            String input = Console.readLine();

            try {
                return parsePurchaseAmount(input);
            } catch (NumberFormatException e) {
                System.err.println("[ERROR] 숫자를 입력해 주세요. 다시 시도하세요.");
            }
        }
    }

    private static int parsePurchaseAmount(String input) {
        int purchaseAmount = Integer.parseInt(input);
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000 단위로 입력 해주세요");
        }
        return purchaseAmount / 1000;
    }

    private static List<Lotto> generateLottoTickets(int purchaseAmount) {
        System.out.println(purchaseAmount + "개를 구매했습니다.");
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottoTickets.add(new Lotto(numbers));
            System.out.println(lottoTickets.get(i));
        }

        return lottoTickets;
    }

    private static List<Integer> getWinningNumbers() {
        while (true) {
            System.out.println("당첨 번호를 입력해 주세요.");
            String input = Console.readLine();

            try {
                return parseNumbers(input, "로또 번호는 1부터 45 사이의 6개의 중복되지 않는 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] 숫자 또는 6개의 숫자를 입력해주셔야 합니다.");
            }
        }
    }

    private static List<Integer> parseNumbers(String input, String errorMessage) {
        List<Integer> numbers = parseNumbers(input);
        validateNumbers(numbers, errorMessage);
        return numbers;
    }

    private static List<Integer> parseNumbers(String input) {
        String[] numbersAsString = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String num : numbersAsString) {
            numbers.add(Integer.parseInt(num));
        }
        return numbers;
    }

    private static void validateNumbers(List<Integer> numbers, String errorMessage) {
        if (numbers.size() != 6 || !areNumbersWithinRange(numbers, 1, 45) || areNumbersDuplicated(numbers)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static boolean areNumbersWithinRange(List<Integer> numbers, int min, int max) {
        for (int number : numbers) {
            if (number < min || number > max) {
                return false;
            }
        }
        return true;
    }

    private static boolean areNumbersDuplicated(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    private static int getBonusNumber() {
        while (true) {
            System.out.println("보너스 번호를 입력해 주세요.");
            String input = Console.readLine();

            try {
                int bonusNumber = parseBonusNumber(input);
                validateBonusNumber(bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private static int parseBonusNumber(String input) {
        return Integer.parseInt(input);
    }

    private static void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private static List<Prize> calculatePrizes(List<Lotto> purchasedLottoTickets, List<Integer> winningNumbers, int bonusNumber) {
        List<Prize> prizes = new ArrayList<>();
        for (Lotto lotto : purchasedLottoTickets) {
            int matchingCount = countMatchingNumbers(lotto, winningNumbers);
            boolean hasBonusNumber = lotto.hasNumber(bonusNumber);

            Prize prize = Prize.calculatePrize(matchingCount, hasBonusNumber);

            prizes.add(prize);
        }
        return prizes;
    }

    private static int countMatchingNumbers(Lotto lotto, List<Integer> winningNumbers) {
        int count = 0;
        for (int number : winningNumbers) {
            if (lotto.hasNumber(number)) {
                count++;
            }
        }
        return count;
    }

    private static void printResults(int purchaseAmount, List<Lotto> purchasedLottoTickets, List<Prize> prizes) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Prize prize : Prize.values()) {
            int count = countPrizes(prizes, prize);
            System.out.println(prize.getDescription() + " - " + count + "개");
        }

        double totalWinningAmount = prizes.stream()
                .mapToLong(Prize::getWinningAmount)
                .sum();

        double investment = purchaseAmount * 1000;
        double profitRate = (totalWinningAmount / investment) * 100;

        System.out.printf("총 수익률은 %.2f%% 입니다.%n", profitRate);
    }

    private static int countPrizes(List<Prize> prizes, Prize prize) {
        return (int) prizes.stream()
                .filter(p -> p == prize)
                .count();
    }
}
