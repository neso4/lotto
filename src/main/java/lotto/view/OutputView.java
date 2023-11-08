package lotto.view;

public class OutputView {
    public static void printSuccessResult() {
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public static void printSuccessMessage(String message, int numberOfMatch) {
        System.out.println(message + numberOfMatch + "개");
    }

    public static void printRevenueRate(double EarningRate) {
        System.out.println("총 수익률은 " + String.format("%.1f", EarningRate) + "%입니다.");
    }
}
