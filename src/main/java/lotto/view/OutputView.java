package lotto.view;

import lotto.model.User;
import lotto.view.message.PrintMessage;

public class OutputView {

    public void printPurchaseLotto(User user) {
        int lottoCount = user.getLottoCount();
        System.out.println(lottoCount + PrintMessage.LOTTO_COUNT_OUTPUT_MESSAGE.getMessage());
        System.out.println(user + "\n");
    }
}
