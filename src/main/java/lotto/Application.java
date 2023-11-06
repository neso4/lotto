package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {
    private static int inputPurchaseAmount() {
        int purchaseAmount = Integer.parseInt(Console.readLine());
        while (purchaseAmount % 1000 != 0) {
            purchaseAmount = isValidPurchaseAmount(purchaseAmount);
        }
        return purchaseAmount;
    }

    private static int isValidPurchaseAmount(int purchaseAmount) {
        try {
            if (purchaseAmount % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액 단위를 1,000원(천원)단위로 입력하여 주세요!!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            int refixPurchaseAmount = Integer.parseInt(Console.readLine());
            return refixPurchaseAmount;
        }
        return purchaseAmount;
    }

    private static void printPurchaseNumber(int purchaseAmount) {
        System.out.println(purchaseAmount + "개를 구매했습니다.");
    }

    private static Lotto inputLottoNumbers() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(lottoNumbers);
        Lotto myLotto = new Lotto(lottoNumbers);
        return myLotto;
    }

    private static List<Lotto> generateLottos(int purchaseNumber) {
        List<Lotto> myLottos = new ArrayList<>();
        for (int i = 0; i < purchaseNumber; i++) {
            myLottos.add(inputLottoNumbers());
        }
        return myLottos;
    }

    private static void printMyLottos(List<Lotto> myLottos, int purchaseNumber) {
        for (int i = 0; i < purchaseNumber; i++) {
            myLottos.get(i).printLottoNumbers();
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = inputPurchaseAmount();
        int purchaseNumber = purchaseAmount / 1000;
        printPurchaseNumber(purchaseNumber);
        List<Lotto> myLottos = generateLottos(purchaseNumber);
        printMyLottos(myLottos, purchaseNumber);
    }
}
