package lotto;

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

    private static Integer inputPlayerBonusNumber(){
        String sNumber = Console.readLine();

        if (isLottoNumber(sNumber)) {
            return Integer.parseInt(sNumber);
        }
        return -1;
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
        
        System.out.println("\n보너스 번호를 입력해 주세요.");
        Integer playerBonusNumber;
        do {
            playerBonusNumber = inputPlayerBonusNumber();
        } while(playerBonusNumber < 0);

        int[] result = {0, 0, 0, 0, 0};
        for (Lotto l : lottos) {
            int count = l.run(playerNumbers);
            
            if (count == 5) {
                result[count - 3 + l.runBonus(playerBonusNumber)] ++;
            }
            else if (count == 6) {
                result[count - 2] ++;
            }
            else {
                result[count - 3] ++;
            }
        }

        


    }
}
