package lotto.view;

import java.util.List;

public class OutputView {

    public void printLottoBuyingResult(int lottoAmount, List<List<Integer>> lottoNumbers) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
        for (List<Integer> lottoNumber : lottoNumbers) {
            printNumbers(lottoNumber);
            System.out.println();
        }
    }

    public void printLottoWinningResult(List<String> eachWinningResult) {
        System.out.println("3개 일치 (5,000원) - " + eachWinningResult.get(0) + "개");
        System.out.println("4개 일치 (50,000원) - " + eachWinningResult.get(1) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + eachWinningResult.get(2) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + eachWinningResult.get(4) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + eachWinningResult.get(3) + "개");
    }

    private void printNumbers(List<Integer> numbers) {
        System.out.print("[");
        for (int i = 0; i < numbers.size(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(numbers.get(i));
        }
        System.out.print("]");
    }
}
