package lotto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    private static int getTickets(){
        try {
            Integer N = Integer.parseInt(Console.readLine());
            if (N % 1000 != 0) {
                throw new IllegalStateException();
            }
            return N/1000;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] 구입 금액은 1000원 단위로 입력해주세요.");
        }
        return -1;
    }

    private static boolean isLottoLength(String[] sNumbers) {
        try{
            if (sNumbers.length != 6) {
                throw new IllegalArgumentException();
            }
            return true;
        } catch(IllegalArgumentException e) {
            System.out.println("[ERROR] 로또 번호 6개를 입력해주세요.");
            return false;
        }
    }

    private static boolean isLottoNumber(String sNumber) {
        try {
            Integer number = Integer.parseInt(sNumber);
            if (number >= 1 && number <= 45) {
                return true;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 1~45 사이의 번호를 입력해주세요.");
            return false;
        }
    }

    private static boolean isDuplicated(List<Integer> numbers) {
        try {
            Set<Integer> setNumbers = new HashSet<>(numbers);
            if (numbers.size() != setNumbers.size()) {
                throw new IllegalArgumentException();
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 번호가 중복되지 않도록 입력해주세요.");
            return true;
        }
    }

    private static boolean isDuplicated(List<Integer> numbers, Integer number){
        try {
            Set<Integer> setNumbers = new HashSet<>(numbers);
            setNumbers.add(number);

            if (setNumbers.size() != numbers.size() + 1) {
                throw new IllegalArgumentException();
            }
            
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 번호가 중복되지 않도록 입력해주세요.");
            return true;
        }

    }

    private static List<Integer> inputPlayerNumbers(){
        String[] sNumbers = Console.readLine().split(",");
        List<Integer> numbers = new ArrayList<Integer>();

        if (!isLottoLength(sNumbers)) {
            return new ArrayList<Integer>();
        }

        for (String number : sNumbers) {
            if (isLottoNumber(number)) {
                numbers.add(Integer.parseInt(number));
            } else {
                return new ArrayList<Integer>();
            }
        }

        if (isDuplicated(numbers)) {
            return new ArrayList<Integer>();
        }

        return numbers;

    }

    private static Integer inputPlayerBonusNumber(List<Integer> numbers){
        String sNumber = Console.readLine();

        if (isLottoNumber(sNumber)) {
            Integer number = Integer.parseInt(sNumber);
            if (!isDuplicated(numbers, number))
                return number;
        }
        
        return -1;
    }

    private static void printResult(int[] result, int tickets) {
        double revenue = 0;
        double price = tickets*1000;
        revenue = 5000 * result[0] + 50000 * result[1] + 1500000 * result[2] + 30000000 * result[3] + 2000000000 * result[4];

        System.out.printf("3개 일치 (5,000원) - %d개\n", result[0]);
        System.out.printf("4개 일치 (50,000원) - %d개\n", result[1]);
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", result[2]);
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", result[3]);
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", result[4]);

        System.out.printf("총 수익률은 %.1f", (revenue / price) * 100);
        System.out.println("%입니다.");
    }
    public static void main(String[] args) {
        
        // int count = lotto.run(players);

        System.out.println("구입금액을 입력해 주세요.");
        int tickets;
        do {
            tickets = getTickets();
        } while(tickets < 0);

        List<Lotto> lottos = new ArrayList<Lotto>();
        System.out.printf("\n%d개를 구매했습니다.\n", tickets);
        for (int i = 0; i < tickets; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            System.out.println(numbers.toString());

            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }

        System.out.println("\n당첨 번호를 입력해 주세요.");
        List<Integer> playerNumbers;
        do{
            playerNumbers = inputPlayerNumbers();
        } while(playerNumbers.isEmpty());
        Collections.sort(playerNumbers);
        
        System.out.println("\n보너스 번호를 입력해 주세요.");
        Integer playerBonusNumber;
        do {
            playerBonusNumber = inputPlayerBonusNumber(playerNumbers);
        } while(playerBonusNumber < 0);

        int[] result = {0, 0, 0, 0, 0};
        for (Lotto l : lottos) {
            int count = l.run(playerNumbers);
            
            count -= 3;
            if (count < 0) {
                continue;
            }

            if (count == 2) {
                result[count + l.runBonus(playerBonusNumber)] ++;
            }
            else if (count == 3) {
                result[count + 1] ++;
            }
            else {
                result[count] ++;
            }
        }

        System.out.println("\n당첨 통계");        
        System.out.println("---");
        printResult(result, tickets);

    }
}
