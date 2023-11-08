package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.service.LottoService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ConsoleUI {
    private static final int LOTTO_PRICE = 1000;

    public static int amountInput(){
        int purchaseAmount;
        while (true){
            try{
                System.out.println("구입금액을 입력해 주세요.");
                purchaseAmount = parseInt(Console.readLine());

                if(purchaseAmount % LOTTO_PRICE != 0){
                    throw new IllegalArgumentException("[ERROR] 1,000원 단위로 입력해 주세요.");
                }

                purchaseAmount /= LOTTO_PRICE;
                break;
            } catch (NumberFormatException e){
                System.out.println("[ERROR] 올바른 숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\n" + purchaseAmount + "개를 구매했습니다.");
        return purchaseAmount;
    }

    public static List<List<Integer>> showLotto(int tickets){
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < tickets; i++) {
            List<Integer> lottoNumbers = LottoService.generateRandomLottoNumbers();
            Lotto lotto = new Lotto(lottoNumbers);
            result.add(lotto.getNumbers());
            System.out.println(lotto.getNumbers());
        }
        return result;
    }

    public static List<Integer> winnerInput(){
        System.out.println("당첨 번호를 입력해주세요.");
        while (true){
            try{
                String[] numbers = Console.readLine().split(",");
                if (numbers.length != 6) {
                    throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개를 입력해야 합니다.");
                }
                List<Integer> winningNumbers = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    winningNumbers.add(parseInt(numbers[i].trim()));
                }
                Lotto lotto = new Lotto(winningNumbers);
                return lotto.getNumbers();
            } catch (NumberFormatException e){
                System.out.println("[ERROR] 올바른 숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int bonusInput(){
        System.out.println("보너스 번호를 입력해주세요.");
        while (true){
            try{
                int bonus = parseInt(Console.readLine());
                validateNumberRange(bonus);
                return bonus;
            } catch (NumberFormatException e){
                System.out.println("[ERROR] 올바른 숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printResult(List<List<Integer>> bought, List<Integer> winner, int bonus) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        List<Integer> matchCounts = LottoService.calculateMatches(bought, winner, bonus);
        Rank[] ranks = Rank.values();
        for (int i = 0; i < ranks.length; i++) {
            int count = matchCounts.get(i);
            System.out.println(ranks[i].getDescription() + " - " + count + "개");
        }

        int totalValue = LottoService.prize(matchCounts);
        double result = Math.round((double) totalValue / bought.size()) / 10.0;
        System.out.println("총 수익률은 " + result + "%입니다.");
    }

    private static void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 번호는 1 이상 45 이하의 숫자여야 합니다.");
        }
    }
}
