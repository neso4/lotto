package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해 주세요.");
        int lottoPur = Integer.parseInt(readLine());
        int lottoNum = lottoPur / 1000;
        // 예외처리

        List<List<Integer>> lotto = new ArrayList<>();
        getLotto(lotto, lottoNum);
        printLotto(lotto, lottoNum);

        System.out.println("당첨 번호를 입려해 주세요.");
        String[] input = readLine().split(",");
        List<Integer> userLotto = new ArrayList<>();
        for (String list : input) {
            userLotto.add(Integer.valueOf(list));
        }
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        //예외
        userLotto.add(Integer.valueOf(readLine()));
        winLotto(lotto, userLotto, lottoNum);
    }

    public static List<List<Integer>> getLotto(List<List<Integer>> lotto, int lottoNum) {
        for (int i = 0; i < lottoNum; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lotto.add(numbers);
        }
        return lotto;
    }

    public static void printLotto(List<List<Integer>> lotto, int lottoNum) {
        System.out.println();
        System.out.println(lottoNum + "개를 구매했습니다.");
        for (int i = 0; i < lottoNum; i++) {
            Collections.sort(lotto.get(i));
            System.out.println(lotto.get(i));
        }
    }

    public static void winLotto(List<List<Integer>> lotto, List<Integer> userLotto, int lottoNum) {
        List<Integer> reward = new ArrayList<>();
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        int bonus = 0;
        for (int i = 0; i < lottoNum; i++) {
            int idx = 0;
            for (int j = 0; j < 6; j++) { // indent depth 1
                if (lotto.get(i).contains(userLotto.get(j))) { // indent depth 2
                    idx += 1;  // indent depth 3
                }
            }
            reward.add(idx);
            if (idx == 4) {
                if (lotto.get(i).contains(userLotto.get(6))){
                    // 보너스 번호 2등
                    bonus+=1;
                    reward.remove(i);
                }
            }
        }
        winPrice(reward, lottoNum, bonus);
    }

    public static void winPrice(List<Integer> reward, int lottoNum, int bonus) {
        int three = Collections.frequency(reward, 3);
        int four = Collections.frequency(reward, 4);
        int five = Collections.frequency(reward, 5);
        int six = Collections.frequency(reward,6);

        System.out.println("3개 일치 (5,000원) - "+three+"개");
        System.out.println("4개 일치 (50,000원) - "+four+"개");
        System.out.println("5개 일치 (1,500,000원) - "+five+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+bonus+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+six+"개");

        float pricePer = ((three*5000) + (four*50000) + (five*1500000) +
                (bonus*30000000) + (six*2000000000)) / (float)(lottoNum*1000);
        System.out.printf("총 수익률은 %.2f%%입니다.",pricePer);
    }
}
