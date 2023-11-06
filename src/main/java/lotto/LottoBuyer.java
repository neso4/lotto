package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;

public class LottoBuyer {
    final String REQUEST_INPUT_MESSAGE = "구입 금액을 입력해 주세요.\n";
    final String PRINT_MY_LOTTOS = "%d개를 구매했습니다.\n";
    final int LOTTO_PURCHASE_UNIT = 1000;
    private int purchaseAmount;
    private int purchaseLottoCount;
    private ArrayList<Lotto> myLottos;
    ErrorMessages errorType;

    public LottoBuyer() {
        this.purchaseAmount = 0;
        this.purchaseLottoCount = 0;
        this.myLottos = new ArrayList<>();
    }

    public void inputPurchaseAmount() {
        System.out.print(REQUEST_INPUT_MESSAGE);
        buyLotto(Console.readLine());
    }

    public void buyLotto(String inputAmount) {
        purchaseAmount = validateInputAmount(inputAmount);
    }

    public int validateInputAmount(String inputAmount) {
        int resultAmount;
        try {
            resultAmount = Integer.parseInt(inputAmount);
        } catch (NumberFormatException e) {
            errorType = ErrorMessages.INPUT_IS_NOT_NUMBER;
            throw new IllegalArgumentException(errorType.getDescription());
        }

        if ((resultAmount % LOTTO_PURCHASE_UNIT) != 0 || (resultAmount < 0)) {
            errorType = ErrorMessages.INPUT_NOT_UNIT_MONEY;
            throw new IllegalArgumentException(errorType.getDescription());
        }

        return resultAmount;
    }

    public void addMyLottos(Lotto publishedLotto) {
        myLottos.add(publishedLotto);
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public int calculatePublishCount() {
        return purchaseAmount / LOTTO_PURCHASE_UNIT;
    }

    public ArrayList<Lotto> getMyLotts() {
        return myLottos;
    }

    public void printMyLottos() {
        System.out.printf(PRINT_MY_LOTTOS, calculatePublishCount());

        for (Lotto lotto : myLottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
