package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import domain.LottoProfitCalculator;
import domain.LottoWinChecker;

import java.util.*;
import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        // 1. 구입 금액 입력
        int purchaseAmount; // 로또 구입 금액
        int numberOfLottos; // 구입한 로또 개수
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                purchaseAmount = Integer.parseInt(readLine());
                numberOfLottos = purchaseAmount / 1000;
                if (purchaseAmount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액이 1,000원으로 나누어 떨어지지 않습니다.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자가 아닙니다.(구입 금액)\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()+ '\n');
            }
        }

        // 2. 로또 발행 및 번호 출력
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < numberOfLottos; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> numbersCopy = new ArrayList<>(numbers);
            Collections.sort(numbersCopy);
            lottos.add((new Lotto(numbersCopy)));
        }

        System.out.println();
        System.out.println(numberOfLottos + "개를 구매했습니다.");
        for (int i = 0; i < numberOfLottos; i++) {
            System.out.println(lottos.get(i).getNumbers());
        }

        // 3. 당첨 번호와 보너스 번호 입력
        List<Integer> winningNumbers;

        while (true) {
            try {
                System.out.println('\n' + "당첨 번호를 입력해 주세요.");
                String winningNumberInput = readLine();
                winningNumbers = Arrays.stream(winningNumberInput.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                if (winningNumbers.size() != 6) {
                    throw new IllegalArgumentException("[ERROR] 6개의 숫자만 입력해야 됩니다.(당첨 번호)");
                }

                int max = Collections.max(winningNumbers);
                int min = Collections.min(winningNumbers);
                if (max > 45 || min < 1) {
                    throw new IllegalArgumentException("[ERROR] 1-45 내에 숫자만 입력해야 됩니다.(당첨 번호)");
                }

                long countDistinctElements = winningNumbers.stream().distinct().count();
                if (countDistinctElements != 6) {
                    throw new IllegalArgumentException("[ERROR] 중복 입력된 숫자가 있습니다.(당첨 번호)");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자가 아닙니다.(당첨 번호)");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println('\n' + "보너스 번호를 입력해 주세요.");
                int bonusNumber = Integer.parseInt(readLine());
                winningNumbers.add(bonusNumber);

                if (bonusNumber > 45 || bonusNumber < 1) {
                    throw new IllegalArgumentException("[ERROR] 1-45 내에 숫자만 입력해야 됩니다.(보너스 번호)");
                }

                long countDistinctWinningNumbers = winningNumbers.stream().distinct().count();
                if (countDistinctWinningNumbers != 7) {
                    throw new IllegalArgumentException("[ERROR] 중복 입력된 숫자가 있습니다.(보너스 번호)");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력해주세요.(보너스 번호)");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 4. 당첨 여부 확인 및 등수(LottoRank) 부여
        for (int i = 0; i < lottos.size(); i++) {
            Lotto lotto = lottos.get(i);
            LottoWinChecker winChecker = new LottoWinChecker(lotto, winningNumbers);
        }

        // 5. 당첨 통계 계산 및 출력
        System.out.println('\n' + "당첨 통계");
        System.out.println("---");
        LottoProfitCalculator lottoProfitCalculator = new LottoProfitCalculator(purchaseAmount, lottos);
        System.out.println(lottoProfitCalculator.rankPrinter());
        System.out.println("총 수익률은 " + lottoProfitCalculator.getProfitRate() + "%입니다.");
    }
}
