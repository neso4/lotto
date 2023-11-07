package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

    private static final int WIN_LOTTO_SIZE = 6;
    private static final int LOTTO_RESULT_SIZE = 8;

    private static int purchaseAmount;

    private static Lotto winLotto;

    private static int winBonusLottoNumber;

    private static List<Integer> correctCount = new ArrayList<>();
    private static List<Integer> totalWinResult = new ArrayList<>();

    enum lottoResult {
        six(6, 0, 2_000_000_000, 0),
        fiveAndBonus(5, 1, 30_000_000, 1),
        five(5, 0, 1_500_000, 2),
        four(4, 0, 50_000, 3),
        three(3, 0, 5_000, 4),
        two(2, 0, 0, 5),
        one(1, 0, 0, 6),
        nothing(0, 0, 0, 7);

        private final int corretNumbers;
        private final int bonusNumbers;
        private final int winPrize;

        private final int index;

        lottoResult(int corretNumbers, int bonusNumbers, int winPrize, int index) {
            this.corretNumbers = corretNumbers;
            this.bonusNumbers = bonusNumbers;
            this.winPrize = winPrize;
            this.index = index;
        }

        public static lottoResult whatIsResult() {
            return Arrays.stream(lottoResult.values())
                    .filter(lottoResult -> lottoResult.corretNumbers == correctCount.get(0))
                    .filter(lottoResult -> lottoResult.bonusNumbers == correctCount.get(1))
                    .findFirst()
                    .orElseThrow();
        }
    }

    private static int inputPurchaseAmount() {
        String userPurchaseAmount = Console.readLine();
        isOnlyNumber(userPurchaseAmount);
        purchaseAmount = Integer.parseInt(userPurchaseAmount);

        while (purchaseAmount % 1000 != 0) {
            purchaseAmount = isValidPurchaseAmount(purchaseAmount);
        }
        return purchaseAmount;
    }

    private static int isValidPurchaseAmount(int purchaseAmount) {
        try {
            if (purchaseAmount % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR]");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            int refixPurchaseAmount = Integer.parseInt(Console.readLine());
            return refixPurchaseAmount;
        }
        return purchaseAmount;
    }

    private static void isOnlyNumber(String userPurchaseAmount) {
        try {
            Integer.parseInt(userPurchaseAmount);
            char isValidNumber;
            for (int i = 0; i < userPurchaseAmount.length(); i++) {
                isValidNumber = userPurchaseAmount.charAt(i);
                if ((int) isValidNumber < 48 || (int) isValidNumber > 57) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]");
            Console.readLine();
        }
    }

    private static void printPurchaseNumber(int purchaseAmount) {
        System.out.println(purchaseAmount + "개를 구매했습니다.");
    }

    private static Lotto inputLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(List.of());
        lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
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
            Lotto copyLotto = myLottos.get(i);
            List<Integer> copyLottoNumbers = new ArrayList<>();
            for (int j = 0; j < copyLotto.getLottoNumbers().size(); j++) {
                copyLottoNumbers.add(copyLotto.getLottoNumbers().get(j));
            }
            Collections.sort(copyLottoNumbers);
            System.out.println(copyLottoNumbers);
        }
        System.out.println();
    }

    private static Lotto inputWinLottoNumbers() {
        String userInputWinLotto = Console.readLine();
        List<String> winLottoLetter = Arrays.asList(userInputWinLotto.split(","));

        List<Integer> winLotto = new ArrayList<>();
        for (int i = 0; i < WIN_LOTTO_SIZE; i++) {
            winLotto.add(Integer.parseInt(winLottoLetter.get(i)));
        }

        isValidLottoNumbers(winLotto);

        Lotto winLottoNumber = new Lotto(winLotto);
        return winLottoNumber;
    }

    private static void isValidLottoNumbers(List<Integer> winLotto) {
        try {
            Set<Integer> validwinLotto = new HashSet<>(winLotto);
            if (validwinLotto.size() != winLotto.size()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]");
            throw new IllegalArgumentException();
        }
    }

    private static int inputBonusLottoNumber() {
        return Integer.parseInt(Console.readLine());
    }

    private static void whatIsMyResult(List<Lotto> myLottos, int purchaseNumber) {
        lottoResult myResult;
        for (int i = 0; i < purchaseNumber; i++) {
            initializeCorrectCount();
            howManyCorrectLottoNumber(myLottos.get(i)); // 몇 개 맞았는지 검사 correctcount에 저장
            myResult = lottoResult.whatIsResult(); //six, five ... 당첨 결과
            int index = myResult.index; // 당첨 결과에 해당하는 인덱스 six = 0, fiveandbonus = 1 ...
            totalWinResult.set(index, totalWinResult.get(index) + 1); // 당첨 개수 카운트
        }
    }

    private static void initializeCorrectCount() {
        correctCount.set(0, 0);
        correctCount.set(1, 0);
    }

    private static void howManyCorrectLottoNumber(Lotto myLotto) {
        int count = 0;
        List<Integer> winLottoNumber = winLotto.getLottoNumbers();
        List<Integer> myLottoNumber = myLotto.getLottoNumbers();
        for (int i = 0; i < WIN_LOTTO_SIZE; i++) {
            if (myLottoNumber.contains(winLottoNumber.get(i))) {
                count += 1;
            }
        }
        correctCount.set(0, count);
        if (count == 5 && myLottoNumber.contains(winBonusLottoNumber)) {
            correctCount.set(1, 1);
        }
    }

    private static void printTotalWinResult() {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + totalWinResult.get(4) + "개");
        System.out.println("4개 일치 (50,000원) - " + totalWinResult.get(3) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + totalWinResult.get(2) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + totalWinResult.get(1) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + totalWinResult.get(0) + "개");
    }

    private static double calculateTotalAmount() {
        double totalAmount = 0;
        totalAmount += lottoResult.six.winPrize * totalWinResult.get(0);
        totalAmount += lottoResult.fiveAndBonus.winPrize * totalWinResult.get(1);
        totalAmount += lottoResult.five.winPrize * totalWinResult.get(2);
        totalAmount += lottoResult.four.winPrize * totalWinResult.get(3);
        totalAmount += lottoResult.three.winPrize * totalWinResult.get(4);

        return totalAmount;
    }

    private static void printRateOfReturn(int purchaseAmount) {
        System.out.println("총 수익률은 " + calculateTotalAmount() / purchaseAmount * 100 + "%입니다.");
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해 주세요.");
        purchaseAmount = inputPurchaseAmount();

        System.out.println();
        int purchaseNumber = purchaseAmount / 1000;
        printPurchaseNumber(purchaseNumber);
        List<Lotto> myLottos = generateLottos(purchaseNumber);
        printMyLottos(myLottos, purchaseNumber);

        System.out.println("당첨 번호를 입력해 주세요.");
        winLotto = inputWinLottoNumbers();
        System.out.println();

        System.out.println("보너스 번호를 입력해 주세요.");
        winBonusLottoNumber = inputBonusLottoNumber();

        // 로또 번호 맞은 개수 카운트하는 리스트 초기화
        for (int i = 0; i < 2; i++) {
            correctCount.add(0);
        }
        for (int i = 0; i < LOTTO_RESULT_SIZE; i++) {
            totalWinResult.add(0);
        }

        whatIsMyResult(myLottos, purchaseNumber);
        printTotalWinResult();
        printRateOfReturn(purchaseAmount);
    }
}
