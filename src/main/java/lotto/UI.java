package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UI {
    public String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String line = readLine();
        System.out.println();
        return line;
    }

    public void printPurchases(List<Lotto> lottos) {
        System.out.println("8개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        System.out.print("[");
        for (int i = 0; i < Lotto.size; i++) {
            System.out.print(numbers.get(i));
            if (i < Lotto.size - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해주세요.");
        String line = readLine();
        System.out.println();
        String[] split = line.split(",");
        List<Integer> numbers = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
        return numbers;
    }

    public Integer inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String line = readLine();
        System.out.println();
        return Integer.parseInt(line);
    }

    public void printWinningStats(List<Lotto> lottos, LottoDrawResult lottoDrawResult) {
        System.out.println("당첨 통계");
        System.out.println("---");
        Map<Grade, Integer> winningFrequency = obtainWinningFrequency(lottos, lottoDrawResult);
        printWinningFrequency(winningFrequency);
        printReturnRate(lottos, lottoDrawResult);
    }

    private Map<Grade, Integer> obtainWinningFrequency(List<Lotto> lottos, LottoDrawResult lottoDrawResult) {
        Map<Grade, Integer> winningFrequency = new HashMap<>();

        winningFrequency.put(Grade.FIRST, 0);
        winningFrequency.put(Grade.SECOND, 0);
        winningFrequency.put(Grade.THIRD, 0);
        winningFrequency.put(Grade.FOURTH, 0);
        winningFrequency.put(Grade.FIFTH, 0);

        for (Lotto lotto : lottos) {
            Grade grade = Calculator.checkWinning(lotto, lottoDrawResult);
            Integer frequency = winningFrequency.get(grade);
            winningFrequency.put(grade, frequency + 1);
        }
        return winningFrequency;
    }

    private void printWinningFrequency(Map<Grade, Integer> winningFrequency) {
        Integer frequency = winningFrequency.get(Grade.FIFTH);
        System.out.println("3개 일치 (5,000원) - " + frequency + "개");
        frequency = winningFrequency.get(Grade.FOURTH);
        System.out.println("4개 일치 (50,000원) - " + frequency + "개");
        frequency = winningFrequency.get(Grade.THIRD);
        System.out.println("5개 일치 (1,500,000원) - " + frequency + "개");
        frequency = winningFrequency.get(Grade.SECOND);
        System.out.println("5개 일치 (30,000,000원) - " + frequency + "개");
        frequency = winningFrequency.get(Grade.FIRST);
        System.out.println("6개 일치 (2,000,000,000원) - " + frequency + "개");
    }

    private void printReturnRate(List<Lotto> lottos, LottoDrawResult lottoDrawResult) {
        Double returnRate = Calculator.returnRate(lottos, lottoDrawResult);
        String returnRateByRound = String.format("%.1f", returnRate);
        System.out.println("총 수익률은 " + returnRateByRound + "%입니다.");
    }
}
