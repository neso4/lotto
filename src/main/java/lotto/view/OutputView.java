package lotto.view;

import java.util.List;

public class OutputView {
    public static void printBuyNumbers (int number) {
        System.out.println(number+"개를 구매했습니다.");
    }
    public static void printLottoNumbers (StringBuilder numbers) {
        System.out.println(numbers);
    }

    public static void printLottoResult (String result, double percentage) {
        System.out.println("당첨 통계\n---");
        System.out.println(result);
        System.out.println("총 수익률은 "+percentage+"%입니다.");
    }

    public static void printIsInteger () {
        System.out.println("[ERROR] 숫자 형태의 값이여야 합니다.");
    }

    public static void printIsWrongMoney () {
        System.out.println("[ERROR] 1000원 단위의 금액이여야 합니다.");
    }

    public static void printIsMinMoney () {
        System.out.println("[ERROR] 1000원 이상의 금액이여야 합니다.");
    }

    public static void printIsUnique () {
        System.out.println("[ERROR] 고유한 값들 이여야 합니다.");
    }

    public static void printIsWrongRange () {
        System.out.println("[ERROR] 1 ~ 45 사이의 숫자여야 합니다.");
    }

    public static void printIsWrongLength () {
        System.out.println("[ERROR] 6개의 숫자를 입력해야 합니다.");
    }

    public static void printIsDuplicate () {
        System.out.println("[ERROR] 중복된 값이 아니여야 합니다.");
    }
}
