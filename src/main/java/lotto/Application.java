package lotto;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

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

        int bonusNumber = getInputThi();

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

    private static int getInputThi() {
        int tempNumber;

        while (true){
            try{
                String targetStr = Console.readLine();
                tempNumber = validInputThi(targetStr);
                break;
            }
            catch(IllegalArgumentException e){
                System.out.print("\n");
            }
        }

        return tempNumber;
    }

    private static int validInputThi(String targetStr) {
        try {
            int number = Integer.parseInt(targetStr);

            if (1 > number || number > 45) {
                System.out.print("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                throw new IllegalArgumentException();
            }

            return number;
        }
        catch (NumberFormatException e) {
            System.out.print("[ERROR] 숫자를 입력해주세요.");
            throw new IllegalArgumentException();
        }
    }
}
