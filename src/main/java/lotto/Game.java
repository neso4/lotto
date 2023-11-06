package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int AMOUNT;
    private int COUNT_LOTTO;
    private String PRIZE;
    private String PAST_BONUS_NUMBER;
    private int BONUS_NUMBER;
    private InputView inputView;
    private OutputView outputView;
    private final List<Lotto> lottos;
    private List<Integer> prizes;

    public Game() {
        inputView = new InputView();
        outputView = new OutputView();
        lottos = new ArrayList<>();
        prizes = new ArrayList<>();
    }

    public void run(){
        inputView.purchaseAmountView();
        AMOUNT = purchaseAmountInput(); //로또 구매 금액
        COUNT_LOTTO = countLotto(AMOUNT);   //로또 금액을 개수로 변환

        outputView.purchasesNumberView(COUNT_LOTTO);
        createLotto(COUNT_LOTTO);    //로또 생성
        outputView.lottosView(lottos);  //로또 출력

        inputView.prizeNumberView();
        PRIZE = prizeNumberInput(); //당첨 번호 입력
        changePrizeNumber(PRIZE);   //당첨 번호 변환

        inputView.bonusNumberView();
        PAST_BONUS_NUMBER = bonusNumberInput(); //보너스 번호 입력
        BONUS_NUMBER = changeBonusNumber(PAST_BONUS_NUMBER);    //보너스 번호 변환

        outputView.winningStatistics(); //당첨 통계 출력
        //수익률 구현해야 함
        
        outputView.yieldRateOfReturn();

    }

    public int purchaseAmountInput(){
        //예외 처리 추가해야 함

        return Integer.parseInt(Console.readLine());
    }

    public int countLotto(int AMOUNT){
        return AMOUNT/1000;
    }

    public void createLotto(int AMOUNT){
        for(int i=0; i<AMOUNT; i++){
            Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(lotto);
        }
    }

    public String prizeNumberInput(){
        //예외 처리 추가해야 함

        return Console.readLine();
    }

    public void changePrizeNumber(String PRIZE){
        String[] prizeNumbers = PRIZE.split(",");
        //예외 처리 추가해야 함

        for (String prizeNumber : prizeNumbers) {
            prizes.add(Integer.parseInt(prizeNumber));
        }
    }

    public String bonusNumberInput(){
        //예외 처리 추가해야 함

        return Console.readLine();
    }

    public int changeBonusNumber(String PAST_BONUS_NUMBER){
        //예외 처리 추가해야 함

        return Integer.parseInt(PAST_BONUS_NUMBER);
    }
}
