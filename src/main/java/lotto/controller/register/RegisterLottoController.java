package lotto.controller.register;

import lotto.domain.Lotto;
import lotto.dto.UserLottoDTO;
import lotto.view.LottoScreen;

public class RegisterLottoController extends RegisterAbstractController<Lotto> {
    private final LottoScreen lottoScreen;

    public RegisterLottoController(LottoScreen lottoScreen) {
        this.lottoScreen = lottoScreen;
    }

    @Override
    Lotto doProcess() {
        UserLottoDTO userLottoDTO = lottoScreen.registerLotto();
        return userLottoDTO.toLotto();
    }
}
