package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.entity.Lotto;
import lotto.entity.LottoScore;
import lotto.service.LottoService;
import lotto.service.ScoreService;

public class LottoController {

    private final LottoService lottoService = new LottoService();
    private final ScoreService scoreService = new ScoreService();

    public Integer getCount(int money){
        return lottoService.getCount(money);
    }

    public List<Lotto> lottoGenerate(int count){
        return lottoService.lottoGenerate(count);
    }

    public LottoScore lottoScore(List<Integer> results, List<Lotto> lottos, int bonusNumber){
        return scoreService.lottoScore(results, lottos, bonusNumber);
    }

    public Double rateReturn(LottoScore lottoScore, int money){
        return lottoService.rateReturn(lottoScore,money);
    }

}
