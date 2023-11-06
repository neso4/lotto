package lotto;

import lotto.model.Bonus;
import lotto.model.Lotto;
import lotto.model.Purchase;
import lotto.model.RandomLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static int purchasePieces;
    private static List<Integer> lottoNum;
    private static List<Integer> winNum;
    private static List<Integer> bonusNum;
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final RandomLotto random = new RandomLotto();

    public static void purchaseCount(String purchasingAmount) {
        try {
            purchasePieces = new Purchase(purchasingAmount).pieces();
        } catch (IllegalArgumentException e) {
            outputView.errorMessage(e.getMessage());
            purchaseCount(inputView.purchaseAmount());
        }
    }

    public static void randomRepeat() {
        for (int i = 0; i < purchasePieces; i++) {
            lottoNum = random.listSort(random.generateNum());
            outputView.purchaseNum(lottoNum);
        }
    }

    public static List<Integer> convertNum(String numbers) {
        String[] wordNum = numbers.split(",");
        List<Integer> convertedNum = new ArrayList<>();
        for (String beforeNum : wordNum){
            convertedNum.add(Integer.parseInt(beforeNum));
        }
        return convertedNum;
    }


    public static void winLottoNum(List<Integer> numbers) {
        try {
            Lotto lotto = new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            outputView.errorMessage(e.getMessage());
            winNum = convertNum(inputView.winNum());
            winLottoNum(winNum);
        }
    }

    public static void bonusNum(List<Integer> numbers) {
        try {
            Bonus bonus = new Bonus(numbers);
        } catch (IllegalArgumentException e) {
            outputView.errorMessage(e.getMessage());
            bonusNum = convertNum(inputView.bonusNum());
            bonusNum(bonusNum);
        }
    }

    public static void main(String[] args) {
        purchaseCount(inputView.purchaseAmount());
        outputView.purchasePieces(purchasePieces);
        randomRepeat();
        winNum = convertNum(inputView.winNum());
        winLottoNum(winNum);
        bonusNum = convertNum(inputView.bonusNum());
        bonusNum(bonusNum);
    }
}