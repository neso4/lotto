package lotto;

import camp.nextstep.edu.missionutils.Console;

import static org.assertj.core.api.Assertions.assertThat;

public class Price {

    public static int readPrice() {
        int price = 0;
        while (true) {
            while (true) {
                try {
                    price = Integer.parseInt(Console.readLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("[ERROR] 숫자로 입력해 주세요.");
                }
            }
            if (price % 1000 == 0) return price;
            System.out.println("[ERROR] 1000단위로 입력해 주세요.");
        }
    }

    public static int calculateAmount(int price) {
        return price / 1000;
    }
}
