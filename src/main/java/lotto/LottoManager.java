package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoManager {
    int buyMoney=0;
    int buyLottoAmount=0;
    List<List<Integer>> lottoCandidateSets = new ArrayList<>();
    Lotto winlotto;
    public void insertMoney() {
        int money =0;
        boolean pass = true;

        System.out.println("구입금액을 입력해 주세요.");
        do {
            try {
                money=Integer.parseInt(Console.readLine());
                pass=true;
                checkMoney(money);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
                pass = false;
            }
        } while (!pass);
        buyMoney=money;
    }

    private void checkMoney(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("금액은 1000단위로 입력해주세요.");
        }
    }

    public void buyLottoAmount(){
        buyLottoAmount=buyMoney/1000;
    }

    public void showBuyAmount(){
        System.out.println();
        System.out.println(buyLottoAmount+"개를 구매했습니다.");
    }

    public void loopCreateLottoCandidateNumbers() {
        for(int i=0; i<buyLottoAmount; i++)
            createLottoCandidateNumbers();
    }

    private void createLottoCandidateNumbers() {
        List<Integer> candidateLottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        lottoCandidateSets.add(candidateLottoNumbers);
    }

    public void showAllLottoCandidateSets(){
        for(List<Integer> candidateLottoSet: lottoCandidateSets){
            showLottoCandidateSet(candidateLottoSet);
        }
        System.out.println();
    }

    private void showLottoCandidateSet(List<Integer> candidateLottoSet){
        System.out.print("[");
        for(int i=0; i<candidateLottoSet.size(); i++){
            System.out.print(candidateLottoSet.get(i));
            if(i<candidateLottoSet.size()-1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public void insertLottoNumbers(){
        String winLottoNumbers;
        List<String> tempWinLottoSet;
        List<Integer> winLottoSet=new ArrayList<>();

        System.out.println("당첨 번호를 입력해 주세요.");
        tempWinLottoSet= Arrays.asList(Console.readLine().split(","));
        for(String number : tempWinLottoSet){
            winLottoSet.add(Integer.parseInt(number));
        }
        winlotto = new Lotto(winLottoSet);
    }
}
