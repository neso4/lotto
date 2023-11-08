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
    int bonusNumber=0;
    List<List<Integer>> lottoCandidateSets = new ArrayList<>();
    List<Integer> winLottoCount;
    List<Integer> winLottoMoney;
    Lotto winLotto;
    public void insertMoney() {
        String money;

        System.out.println("구입금액을 입력해 주세요.");
        money=Console.readLine();
        insertMoneyErrorHandling(money);
    }

    public void insertMoneyErrorHandling(String money) {
        try {
            checkMoney(money);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println();
            insertMoney();
        }
    }

    private void checkMoney(String money) {
        int tempMoney=0;

        for(int i=0; i<money.length(); i++){
            if(money.charAt(i)-'0'<0 || money.charAt(i)-'9'>0)
                throw new IllegalArgumentException("금액에 문자가 포함되면 안됩니다.");
        }
        tempMoney=Integer.parseInt(money);
        if (tempMoney % 1000 != 0) {
            throw new IllegalArgumentException("금액은 1000단위로 입력해주세요.");
        }
        buyMoney=tempMoney;
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
        winLottoNumbers=Console.readLine();
        tempWinLottoSet= Arrays.asList(winLottoNumbers.split(","));
        for(String number : tempWinLottoSet){
            winLottoSet.add(Integer.parseInt(number));
        }
        insertLottoNumbersErrorHandling(winLottoSet);
    }

    private void insertLottoNumbersErrorHandling(List<Integer> winLottoSet) {
        try {
            winLotto = new Lotto(winLottoSet);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println();
            insertLottoNumbers();
        }
    }

    public void insertBonusNumber(){
        int number=0;

        System.out.println();
        System.out.println("보너스 번호를 입력해주세요.");
        number=Integer.parseInt(Console.readLine());
        insertBonusNumberErrorHandling(number);
    }

    private void insertBonusNumberErrorHandling(int number){
        try {
            checkBonusNumberRange(number);
            checkBonusNumberInLottoNumbers(number);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println();
            insertBonusNumber();
        }
        bonusNumber=number;
    }

    private void checkBonusNumberRange(int number){
        if(number<1 || number>45){
            throw new IllegalArgumentException("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void checkBonusNumberInLottoNumbers(int number){
        if(winLotto.getNumbers().stream().anyMatch(n->n==number))
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복하면 안됩니다.");
    }

    public void loopJudgeLottoCandidateSetsResult(){
        winLottoCount=Arrays.asList(0, 0, 0, 0, 0);
        winLottoMoney=Arrays.asList(2000000000, 30000000, 1500000, 50000, 5000);

        for(List<Integer> lottoCandidateSet : lottoCandidateSets){
            judgeLottoCandiateSetResult(lottoCandidateSet);
        }
    }

    private void judgeLottoCandiateSetResult(List<Integer> lottoCandidateSet){
        countCorrectNumbers(lottoCandidateSet);
    }

    private void countCorrectNumbers(List<Integer> lottoCandidateSet){
        int count=0;
        int bonusCount=0;
        List<Integer> winNumbers=winLotto.getNumbers();

        for(int number : lottoCandidateSet){
            for(int winNumber : winNumbers){
                if(number==winNumber){
                    count++;
                    break;
                }
                if(number==bonusNumber){
                    bonusCount++;
                    break;
                }
            }
        }
        addWinNumbers(count, bonusCount);
    }

    private void addWinNumbers(int count, int bonusCount){
        if(count==6){
            winLottoCount.set(0,winLottoCount.get(0)+1);
        }
        else if(count==5 && bonusCount==1){
            winLottoCount.set(1,winLottoCount.get(1)+1);
        }
        else if(count==5){
            winLottoCount.set(2,winLottoCount.get(2)+1);
        }
        else if(count==4){
            winLottoCount.set(3,winLottoCount.get(3)+1);
        }
        else{
            if(count==3){
                winLottoCount.set(4,winLottoCount.get(4)+1);
            }
        }
    }

    public void showResult() {
        System.out.println();
        System.out.println("3개 일치 (5,000원) - "+winLottoCount.get(4)+"개");
        System.out.println("4개 일치 (50,000원) - "+winLottoCount.get(3)+"개");
        System.out.println("5개 일치 (1,500,000원) - "+winLottoCount.get(2)+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+winLottoCount.get(1)+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+winLottoCount.get(0)+"개");
    }

    public void showReturnRate(){
        int profit=0;
        float returnRate=0.0f;

        profit=sumWinMoney(winLottoCount, winLottoMoney);
        System.out.println();
        returnRate=(float)profit/buyMoney*100;
        System.out.print("총 수익률은 ");
        System.out.printf("%.1f", returnRate);
        System.out.println("%입니다.");
    }

    public int sumWinMoney(List<Integer> winLottoCount, List<Integer> winLottoMoney){
        int sum=0;

        for(int i=0; i<winLottoCount.size(); i++){
            sum+=winLottoCount.get(i)*winLottoMoney.get(i);
        }
        return sum;
    }
}
