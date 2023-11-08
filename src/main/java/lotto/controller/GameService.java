package lotto.controller;

import lotto.domain.Jackpot;
import lotto.domain.Lotto;
import lotto.view.Output;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    private Setting setting;
    private Judgement judgement;
    private List<Lotto> lottos;
    private Jackpot jackpot;

    public GameService() {
        setting = new Setting();
    }

    public void play() {
        int repetitions = setting.purchaseLotto();

        lottos = setting.pickLottos(repetitions);
        jackpot = setting.pickJackpot();

        judgement = new Judgement(lottos, jackpot);
        judgement.confirmJackpot();
        judgement.printRate();
        judgement.printRevenue(repetitions * 1000);
    }
}
