package lotto.controller.display;

import lotto.domain.UserMoney;
import lotto.repository.LottoRepository;
import lotto.view.LottoScreen;

public class DisplayLottoController implements DisplayController {
    private final UserMoney userMoney;
    private final LottoRepository lottoRepository;
    private final LottoScreen lottoScreen;

    public DisplayLottoController(UserMoney userMoney, LottoRepository lottoRepository,
                                  LottoScreen lottoScreen) {
        this.userMoney = userMoney;
        this.lottoRepository = lottoRepository;
        this.lottoScreen = lottoScreen;
    }

    @Override
    public void process() {
        lottoScreen.displayGeneratedLotto(userMoney, lottoRepository);
    }
}
