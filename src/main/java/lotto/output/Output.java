package lotto.output;

import lotto.domain.Lotto;

public class Output {
    private static void print(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    public static void printLottoAmount(int lottoCount) {
        System.out.println();
        System.out.print(lottoCount);
        print(OutputMessage.LOTTO_AMOUNT);
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }
}
