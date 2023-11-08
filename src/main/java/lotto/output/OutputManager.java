package lotto.output;

public final class OutputManager {
    private OutputManager() {
    }

    public static void print(Object outPutContents) {
        System.out.println(outPutContents);
    }

    public static void printLottoBuyMoneyInputGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printAnswerNumbersInputGuide() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberInputGuide() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }
}
