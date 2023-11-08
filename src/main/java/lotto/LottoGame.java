package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGame {

    private int purchaseAmount;
    private int numberOfPurchase;
    private LottoRepository lottoRepository = new LottoRepository();
    private String[] winningNumbers;
    private int bonusNumber;

    public void start() {
        inputPurchaseAmount();
        printNumberOfPurchase();
        setRandomLottoNumber(this.numberOfPurchase);
        printRandomLottoNumber();
        inputWinningNumber();
        inputBonusNumber();
        compareLottoNumber(winningNumbers, bonusNumber);
        printResult();
    }

    private void inputPurchaseAmount() {
        try {
            System.out.println("구입금액을 입력해주세요.");
            setPurchaseAmount(Integer.parseInt(readLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자만 입력해주세요.");
            inputPurchaseAmount();
        }
    }

    private void setPurchaseAmount(int purchaseAmount) {
        try {
            if (purchaseAmount % 1000 != 0) {
                throw new IllegalArgumentException();
            } else if (purchaseAmount % 10 == 0) {
                this.purchaseAmount = purchaseAmount;
                this.numberOfPurchase = purchaseAmount / 1000;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 1000원 단위로만 입력이 가능합니다.");
            inputPurchaseAmount();
        }
    }


    private int getNumberOfPurchase() {
        return this.numberOfPurchase;
    }


    private void printNumberOfPurchase() {
        System.out.println(getNumberOfPurchase() + "개를 구매했습니다.");
    }

    private void printRandomLottoNumber() {
        lottoRepository.printLottoNumbers();

    }

    private void setRandomLottoNumber(int numberOfPurchase) {
        for (int i = 0; i < numberOfPurchase; i++) {
            List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(numbers);
            setLotto(numbers);
        }
    }

    private void setLotto(List<Integer> numbers) {
        Lotto lotto = new Lotto(numbers);
        lottoRepository.add(lotto);
    }

    private void inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumber;
        winningNumber = readLine();
        winningNumbers = winningNumber.split(",");
    }

    private void inputBonusNumber() {
        try {
            System.out.println("보너스 번호를 입력해 주세요.");
            bonusNumber = Integer.parseInt(readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자만 입력주세요.");
            inputBonusNumber();
        }
    }

    private void compareLottoNumber(String[] winningNumbers, int bonusNumber) {
        lottoRepository.compareLottoNumberList(winningNumbers, bonusNumber);

    }

    private void printResult() {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (" + Rank.FIFTH.getReward() + ") - " + Rank.FIFTH.getCount() + "개");
        System.out.println("4개 일치 (" + Rank.FOURTH.getReward() + ") - " + Rank.FOURTH.getCount() + "개");
        System.out.println("5개 일치 (" + Rank.THIRD.getReward() + ") - " + Rank.THIRD.getCount() + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (" + Rank.SECOND.getReward() + ") - " + Rank.SECOND.getCount() + "개");
        System.out.println("6개 일치 (" + Rank.FIRST.getReward() + ") - " + Rank.FIRST.getCount() + "개");
        System.out.println("총 수익률은 " + getRateOfReturn() + "%입니다.");

    }

    private String getRateOfReturn() {
        String result = String.format("%.1f", (double) ((calculateTotalGain() / purchaseAmount) * 100));
        return result;
    }

    private double calculateTotalGain() {
        double totalGain = 0;
        totalGain =
                (Rank.FIFTH.getCount() * 5000) + (Rank.FOURTH.getCount() * 50000) + (Rank.THIRD.getCount() * 1500000)
                        + (Rank.SECOND.getCount() * 30000000) + (Rank.FIRST.getCount() * 2000000000);
        return totalGain;
    }
}
