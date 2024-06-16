package lotto;

import org.assertj.core.api.ThrowableAssert;

import java.util.*;

public class Result {
    public void showResult(List<Integer>[] lotto, List<Integer> win, int bonus) {
        HashMap<Integer, Integer> count = count(lotto, win, bonus);
        HashMap<String, Integer> result = result(count);
        double percent = rate(count, lotto.length / 6);
        printResult(result, percent);
    }

    // 맞춘갯수끼리 map
    private HashMap<Integer, Integer> count(List<Integer>[] lotto, List<Integer> win, int bonus) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < lotto.length / 6; i++) {
            int correct = count(lotto[i], win);
            if (correct == 5 && countBonus(lotto[i], bonus)) {
                correct = 7; // 보너스까지 맞았다면 7로 설정
            }
            // mapping, https://gymdev.tistory.com/39 참고
            map.put(correct, map.getOrDefault(correct, 0) + 1);
        }
        return map;
    }

    // 복권 한개당 일치하는 숫자 확인
    public int count(List<Integer> lotto, List<Integer> win) {
        int correct = 0;
        for (int i = 0; i < lotto.size(); i++) {
            if(countBonus(lotto, win.get(i))){
                correct++;
            }
        }
        return correct;
    }

    // 5개+보너스숫자 확인
    private boolean countBonus(List<Integer> lotto, int bonus) {
        for (Integer integer : lotto) {
            if (integer == bonus) {
                return true;
            }
        }
        return false;
    }

    // 순서대로 결과 mapping, https://tosuccess.tistory.com/138 참고
    private HashMap<String, Integer> result(HashMap<Integer, Integer> count) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (PRICE price : PRICE.values()) {
            result.put(price.getRank(), 0);
            if (count.containsKey(price.getNumber())) {
                result.replace(price.getRank(), count.get(price.getNumber()));
            }
        }
        return result;
    }

    // 수익률 구하기
    private double rate(HashMap<Integer, Integer> result, int n) {
        double avg = (double) (result.getOrDefault(3, 0) * 5 + result.getOrDefault(4, 0) * 50
                + result.getOrDefault(5, 0) * 1500 + result.getOrDefault(7, 0) * 30000
                + result.getOrDefault(6, 0) * 2000000) / n * 100;
        return avg;
    }

    // 결과 출력
    private void printResult(HashMap<String, Integer> result, double percent) {
        for (String key : result.keySet()) {
            System.out.println(key + result.get(key) + "개");
        }
        System.out.printf("총 수익률은 %3.1f%%입니다.", percent);
    }
}

enum PRICE {
    FIFTH(3, "3개 일치 (5,000원) - "),
    FOURTH(4, "4개 일치 (50,000원) - "),
    THIRD(5, "5개 일치 (1,500,000원) - "),
    SECOND(7, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    FIRST(6, "6개 일치 (2,000,000,000원) - ");

    final private int number;
    final private String rank;

    public int getNumber() {
        return number;
    }

    public String getRank() {
        return rank;
    }

    private PRICE(int number, String text) {
        this.number = number;
        this.rank = text;
    }
}
