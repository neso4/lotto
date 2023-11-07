package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LottoGame {

    public LottoGame() {
        gameStart();
    }
    
    private void gameStart() {
        int money = inputPurchaseMoney();
        int lottoCount = getLottoCount(money);
        List<Lotto> lottos = new ArrayList<Lotto>();

        lottoIssue(lottoCount, lottos);
        int[] prizeNum = inputPrizeNumber();
        int bonusNum = inputBonusNumber();
        List<Integer> lottoMatchCount = winningStatistics(lottos, prizeNum, bonusNum);
        printWinningResult(lottoMatchCount);
        int income = getIncome(lottoMatchCount);
        printEarningRate(income, money);
    }

    private int isInt(String value) {
        for (char c : value.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
            }
        }

        return Integer.parseInt(value);
    }

    private int inputPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money;

        try {
            money = isInt(Console.readLine());
            if (money % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액이 1,000원 단위가 아닙니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseMoney();
        }
        return money;
    }

    private int getLottoCount(int m) {
        System.out.println("\n" + m / 1000 + "개를 구매했습니다.");
        return m / 1000;
    }

    private int getPrizeCount(List<Integer> lottoMatch, int num) {
        return Collections.frequency(lottoMatch, num);
    }

    private void lottoIssue(int lottoCnt, List<Lotto> lottos) {
        for (int i = 0; i < lottoCnt; i++) {
            List<Integer> nr = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> numbers = new ArrayList<>(nr);            // solution of [java.lang.UnsupportedOperationException]
            Collections.sort(numbers);
            Lotto lotto = new Lotto(numbers);
            System.out.println(lotto.getNumbers());
            lottos.add(lotto);
        }
    }

    private int[] inputPrizeNumber() {
        System.out.println("\n당첨 번호를 입력해 주세요.");

        return Stream.of(Console.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
    }

    private int inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");

        return Integer.parseInt(Console.readLine());
    }

    private int checkPrizeNumber(Lotto l, int[] pn) {
        int count = 0;
        for (int p : pn) {
            if (l.getNumbers().contains(p)) {
                count++;
            }
        }
        return count;
    }

    private int checkBonusNumber(Lotto l, int bonusNum, int cnt) {
        if (l.getNumbers().contains(bonusNum)) {
            cnt += 100;
        }
        return cnt;
    }

    private List<Integer> winningStatistics(List<Lotto> lottos, int[] prizeNum, int bonusNum) {
        System.out.println("\n당첨 통계\n---");

        List<Integer> lottoMatchCount = new ArrayList<Integer>();                   // 각 로또 당, 당첨번호가 몇개나 겹치는지
        for (Lotto l : lottos) {
            int count = checkPrizeNumber(l, prizeNum);
            if (count == 5) {
                count = checkBonusNumber(l, bonusNum, count);
            }
            lottoMatchCount.add(count);
        }

        return lottoMatchCount;
    }

    private void printWinningResult(List<Integer> lottoMatchCount) {
        DecimalFormat df = new DecimalFormat("###,###");

        for (prizeStatus ps : prizeStatus.values()) {
            if (ps.equals(prizeStatus.FIVEBONUS)) {
                System.out.println(ps.getNum() + "개 일치, 보너스 볼 일치 (" + df.format(ps.getPrice()) + "원) - " + getPrizeCount(lottoMatchCount, ps.getPrizeNum()) + "개");
                continue;
            }
            System.out.println(ps.getNum() + "개 일치 (" + df.format(ps.getPrice()) + "원) - " + getPrizeCount(lottoMatchCount, ps.getPrizeNum()) + "개");
        }
    }

    private int getIncome(List<Integer> lottoMatchCount) {
        int income = 0;

        for (prizeStatus ps : prizeStatus.values()) {
            income += ps.getPrice() * getPrizeCount(lottoMatchCount, ps.getPrizeNum());
        }
        return income;
    }

    private void printEarningRate(int income, int money) {
        DecimalFormat df = new DecimalFormat("###,###.0");
        // 수익률 = 당첨금 / 구입금 * 100) -> 5000/8000 = 0.625 * 100 = 62.5%
        float earningRate = ((float) income / money) * 100;
        System.out.println("총 수익률은 "+df.format(earningRate)+"%입니다.");
    }
}
