package lotto.view;

import java.util.Collections;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoService;

import java.text.DecimalFormat;
import java.util.*;

public class OutputView {
    public static void printBuyInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }
    
    public static void printNumberOfLottosPurchased(List<Lotto> playersLottos) {
        int lottosCount = playersLottos.size();
        System.out.println();
        System.out.println(lottosCount + "개를 구매했습니다.");
    }
    
    public static void printPlayerLottos(List<Lotto> playersLottos) {
    	for(Lotto lotto : playersLottos) {
    		List<Integer> lottoNumberSort = new ArrayList<>(lotto.getNumbers());
            Collections.sort(lottoNumberSort);
            System.out.println(lottoNumberSort.toString());
    	}
    	System.out.println();
    }
    
    public static void printWinningNumberInstruction() {
    	System.out.println("당첨 번호를 입력해 주세요.");
    }
    public static void printBonusNumberInstruction() {
    	System.out.println();
    	System.out.println("보너스 번호를 입력해 주세요.");
    }
    
    public static void printWinningStatistics(Map<String,Integer> rankCount) {
    	System.out.println("\n당첨 통계");
    	System.out.println("---");
    	System.out.println("3개 일치 (5,000원) - "+rankCount.getOrDefault("FIFTH", 0)+"개");
    	System.out.println("4개 일치 (50,000원) - "+rankCount.getOrDefault("FOURTH", 0)+"개");
    	System.out.println("5개 일치 (1,500,000원) - "+rankCount.getOrDefault("THIRD", 0)+"개");
    	System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+rankCount.getOrDefault("SECOND", 0)+"개");
    	System.out.println("6개 일치 (2,000,000,000원) - "+rankCount.getOrDefault("FIRST", 0)+"개");
    }
    
    public static void printReturnOnInvestment(double roi){
        DecimalFormat df = new DecimalFormat("0.##");
        System.out.println("총 수익률은 " + df.format(roi) + "%입니다.");
    }

    
}
