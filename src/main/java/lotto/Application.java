package lotto;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.text.DecimalFormat;

public class Application {
    public static void main(String[] args) {
        List<Lotto> lottoList = new ArrayList<>();

        int lottoCnt = getInputFir();

        System.out.println(lottoCnt + "개를 구매했습니다.");

        int produceCnt = 0;

        while (produceCnt < lottoCnt){
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(randomNumbers);
            lottoList.add(lotto);

            printLottoNumbers(lotto);

            produceCnt++;
        }

        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");

        Lotto winningNumbers = getInputSec();

        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");

        int bonusNumber = getInputThi(winningNumbers);

        int[] winningResults = winningCalculate(lottoList, winningNumbers, bonusNumber);

        printStatistics(winningResults);

        long winnings = calculateWinnings(winningResults);

        calculateProfit(lottoCnt*1000, winnings);
    }

    private static int getInputFir() {
        int cash = 0;
        while (true){
            System.out.println("구입금액을 입력해 주세요.");
            try{
                String cashStr = Console.readLine();
                cash = validInputOne(cashStr);
                break;
            }
            catch(IllegalArgumentException e){
                System.out.println("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
            }
        }
        return cash / 1000;
    }

    private static int validInputOne(String cashStr){
        try{
            int tempCash = 0;
            tempCash = Integer.parseInt(cashStr);
            if (tempCash % 1000 != 0) {
                throw new IllegalArgumentException();
            }
            return tempCash;
        }
        catch(Exception e){
            throw new IllegalArgumentException();
        }
    }

    private static void printLottoNumbers(Lotto lotto){
        List<Integer> numbers = lotto.getNumbers();

        System.out.print("[");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));
            if (i < numbers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private static Lotto getInputSec() {
        Lotto tempNumbers;
        while (true){
            try{
                String targetStr = Console.readLine();
                tempNumbers = validInputSec(targetStr);
                break;
            }
            catch(IllegalArgumentException e){
                System.out.print("\n");
            }
            catch(Exception e){
                System.out.println("[ERROR] 형식에 맞게 다시 입력해주세요.");
            }
        }
        return tempNumbers;
    }

    private static Lotto validInputSec(String targetStr) {
        String[] numberStrings = targetStr.split(",");

        List<Integer> winningNumbers = new ArrayList<>();

        for (String numberString : numberStrings) {
            int number = Integer.parseInt(numberString);
            winningNumbers.add(number);
        }

        return new Lotto(winningNumbers);
    }

    private static int getInputThi(Lotto winningNumbers) {
        int tempNumber;
        while (true){
            try{
                String targetStr = Console.readLine();
                tempNumber = validInputThi(targetStr, winningNumbers);
                break;
            }
            catch(IllegalArgumentException e){
                System.out.print("\n");
            }
        }
        return tempNumber;
    }

    private static int validInputThi(String targetStr, Lotto winningNumbers) {
        try {
            int number = Integer.parseInt(targetStr);
            if (1 > number || number > 45) {
                System.out.print("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                throw new IllegalArgumentException();
            } if (IsDuplicated(number, winningNumbers)){
                System.out.print("[ERROR] 보너스 번호가 당첨 번호와 겹칩니다.");
                throw new IllegalArgumentException();
            }
            return number;
        }
        catch (NumberFormatException e) {
            System.out.print("[ERROR] 숫자를 입력해주세요.");
            throw new IllegalArgumentException();
        }
    }

    private static boolean IsDuplicated(int number, Lotto winningNumbers){
        List<Integer> targetNumbers = winningNumbers.getNumbers();

        return targetNumbers.contains(number); // 중복된 숫자가 존재
    }

    private static int[] winningCalculate(List<Lotto> HaveList, Lotto winningNumbers, int bonusNumber){
        int[] tempWinningResults = new int[5];
        for (Lotto lottoNumbers : HaveList) {
            int tempCnt = CalculateCnt(lottoNumbers, winningNumbers);
            boolean tempBonus = IsDuplicated(bonusNumber, lottoNumbers);
            if (tempCnt < 3){
                continue;
            }
            else if (tempCnt == 6 || (tempCnt == 5 && tempBonus)) {
                tempWinningResults[6 - tempCnt]++;
                continue;
            }
            tempWinningResults[7 - tempCnt]++;
        }
        return tempWinningResults;
    }

    private static int CalculateCnt(Lotto lottoNumbers, Lotto winningNumbers){
        int cnt = 0;

        List<Integer> userNumbers = lottoNumbers.getNumbers();
        List<Integer> targetNumbers = winningNumbers.getNumbers();

        for (int userNumber : userNumbers) {
            if (targetNumbers.contains(userNumber)) {
                cnt++;
            }
        }

        return cnt;
    }

    private static void printStatistics(int[] winningResults){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        System.out.println("3개 일치 (5,000원) - " + winningResults[4] + "개");
        System.out.println("4개 일치 (50,000원) - " + winningResults[3] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + winningResults[2] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + winningResults[1] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + winningResults[0] + "개");
    }

    public enum WinningPrize {
        GRAND_PRIZE(2000000000),
        FIRST_PRIZE(30000000),
        SECOND_PRIZE(1500000),
        THIRD_PRIZE(50000),
        FOURTH_PRIZE(5000);
        private final long prizeMoney;
        WinningPrize(long prizeMoney) {
            this.prizeMoney = prizeMoney;
        }
        public long getPrizeMoney() {
            return prizeMoney;
        }
    }

    public static long calculateWinnings(int[] winningResults) {
        long tempMoney = 0;

        for (int i = 0; i < winningResults.length; i++) {
            if (winningResults[i] > 0) {
                tempMoney += WinningPrize.values()[i].getPrizeMoney() * winningResults[i];
            }
        }

        return tempMoney;
    }

    private static void calculateProfit(long income, long outcome){
        double returnRate = (double)Math.round((double)(outcome*1000)/income) / 10;

        System.out.println("총 수익률은 " + returnRate + "%입니다.");
    }
}
