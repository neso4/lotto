package lotto.model;

import lotto.controller.LottoMarket;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final UserWallet userWallet;
    private final LottoMarket lottoMarket;

    List<Lotto> myLottoNumbers = new ArrayList<>();
    public User(){
        this.userWallet = new UserWallet();
        this.lottoMarket = new LottoMarket();
    }

    public int useMoney(){
        return userWallet.buyLotto();
    }

    public void buyLotto(){
        useMoney();
    }

}
