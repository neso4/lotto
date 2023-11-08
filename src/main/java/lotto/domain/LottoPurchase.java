package lotto.domain;

import lotto.Lotto;
import lotto.constant.LottoNumber;
import lotto.util.InputUtil;
import lotto.util.MessageUtil;
import lotto.util.NumberUtil;
import lotto.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {

    private final InputUtil inputUtil = new InputUtil();
    private final MessageUtil messageUtil = new MessageUtil();
    private final ValidationUtil validationUtil = new ValidationUtil();
    private final NumberUtil numberUtil = new NumberUtil();

    private List<Lotto> userLottos;
    private int purchaseAmount;

    public LottoPurchase getPurchaseInfo() {
        while (true) {
            try {
                int purchaseAmount = getUserPurchaseAmount();
                List<Lotto> userLottos = new ArrayList<>();
                int purchaseCount = purchaseAmount / LottoNumber.PURCHASE_AMOUNT_COND.getNumber();
                messageUtil.printPurchaseCount(purchaseCount);
                for (int i = 0; i < purchaseCount; i++) {
                    Lotto lotto = generateLottoNumbers();
                    userLottos.add(lotto);
                }
                this.userLottos = userLottos;
                this.purchaseAmount = purchaseAmount;
                return this;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public int getPurchaseCount (int price){
        return price/1000;
    }

    private int getUserPurchaseAmount() {
        String purchaseAmount = inputUtil.getUserInput();
        return validationUtil.validatePurchase(purchaseAmount);
    }

    private Lotto generateLottoNumbers() {
        List<Integer> lottoNumbers = numberUtil.getLottoNumbers();
        return new Lotto(lottoNumbers);
    }

    public List<Lotto> getUserLottos() {
        return userLottos;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
