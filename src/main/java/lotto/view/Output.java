package lotto.view;

import java.util.Arrays;
import lotto.model.LottoMaker;
import lotto.model.LottoRank;
import lotto.model.LottoResult;
import lotto.model.Ratio;

public class Output {

    static private final int MONEYUNIT = 1000;
    static private final String BUYLOTTO = "%d개를 구매했습니다.";
    static private final String RESULTLOTTO = "%d개 일치 (%s) - %d개";
    static private final String SECONDRESULTLOTTO = "%d개 일치, 보너스 볼 일치 (%s) - %d개";
    static private final String MONEY = "구입금액을 입력해 주세요.";
    static private final String WINNING = "당첨 번호를 입력해 주세요.";
    static private final String BONUS = "보너스 번호를 입력해 주세요.";
    static private final String END = "당첨 통계\n---";
    static private final String LOTTORATIO = "총 수익률은 %.1f%%입니다.";



    public static void printMoney(){
        System.out.println(MONEY);
    }
    public static void printGap(){
        System.out.println();
    }

    public static void printWinning(){
        System.out.println(WINNING);
    }

    public static void printBonus(){
        System.out.println(BONUS);
    }
    public static void printEnd(){
        System.out.println(END);
    }

    public static void printBuylotto(int money){
        int count = money / MONEYUNIT;
        System.out.printf(BUYLOTTO + "\n", count);
    }


    public static void printRatio(Ratio ratio){
        System.out.printf(LOTTORATIO + "\n", ratio.getRatio());
    }
    public static void printlottos(LottoMaker lottos){
        lottos.getLottos().stream()
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printResults(LottoResult lottoResult){
        Arrays.stream(LottoRank.values())
                .filter(lotto -> lotto != LottoRank.NOLUCK)
                .forEach(lotto -> System.out.println(printResult(lotto,lottoResult)));

    }

    static private String printResult(LottoRank rank, LottoResult lottoResult){
        Integer rankSum = lottoResult.getRankCount(rank);
        if(rank.name().equals(LottoRank.SECOND.name())){
            return String.format(SECONDRESULTLOTTO,
                    rank.getMatchLottoNumber(),
                    rank.getWinnigMoney(),
                    rankSum);
        }

        return String.format(RESULTLOTTO,
                rank.getMatchLottoNumber(),
                rank.getWinnigMoney(),
                rankSum);
    }

}
