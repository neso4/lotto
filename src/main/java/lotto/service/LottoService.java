package lotto.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import lotto.config.LottoConfig;
import lotto.domain.Lotto;
import lotto.domain.LottoWinning;
import lotto.dto.LottoStatistics;
import lotto.repository.LottoRepository;
import lotto.repository.LottoWinningRepository;
import lotto.util.LottoGenerator;

public class LottoService {
    private final LottoGenerator lottoGenerator;
    private final LottoRepository lottoRepository;
    private final LottoWinningRepository lottoWinningRepository;

    public LottoService(LottoGenerator lottoGenerator, LottoRepository lottoRepository,
                        LottoWinningRepository lottoWinningRepository) {
        this.lottoGenerator = lottoGenerator;
        this.lottoRepository = lottoRepository;
        this.lottoWinningRepository = lottoWinningRepository;
    }

    public void buyLotto(int purchaseAmount){
        int lottoCount = purchaseAmount / LottoConfig.LOTTO_BUYING_UNIT.getValue();

        List<Lotto> myLotto = Stream
                .generate(lottoGenerator::generate)
                .limit(lottoCount)
                .toList();

        lottoRepository.save(myLotto);
    }

    public void saveWinningNumbers(List<Integer> winningNumbers){
        lottoWinningRepository.saveWinningNumbers(winningNumbers);
    }

    public void saveBonusNumber(int bonusNumber) {
        lottoWinningRepository.saveBonusNumber(bonusNumber);
    }

    public LottoStatistics calcLotto(){
        List<Lotto> myLotto = lottoRepository.getMyLotto();
        LottoWinning lottoWinning = lottoWinningRepository.getLottoWinning();

        int firstCount = 0;
        int secondCount = 0;
        int thirdCount = 0;
        int fourthCount = 0;
        int fifthCount = 0;

        for(Lotto lotto : myLotto){
            int sameCount = getSameCount(lotto, lottoWinning);
            boolean isContainsBonusNumber = isContainsBonusNumber(lotto, lottoWinning);

            if(sameCount == LottoConfig.LOTTO_FIRST_WINNER_CONDITION.getValue()){
                firstCount++;
            } else if(sameCount == LottoConfig.LOTTO_SECOND_WINNER_CONDITION.getValue() && isContainsBonusNumber){
                secondCount++;
            } else if(sameCount == LottoConfig.LOTTO_THIRD_WINNER_CONDITION.getValue()){
                thirdCount++;
            } else if(sameCount == LottoConfig.LOTTO_FOURTH_WINNER_CONDITION.getValue()){
                fourthCount++;
            } else if(sameCount == LottoConfig.LOTTO_FIFTH_WINNER_CONDITION.getValue()){
                fifthCount++;
            }
        }

        int winningAmount = firstCount * LottoConfig.LOTTO_FIRST_PRIZE.getValue()
                + secondCount * LottoConfig.LOTTO_SECOND_PRIZE.getValue()
                + thirdCount * LottoConfig.LOTTO_THIRD_PRIZE.getValue()
                + fourthCount * LottoConfig.LOTTO_FOURTH_PRIZE.getValue()
                + fifthCount * LottoConfig.LOTTO_FIFTH_PRIZE.getValue();

        double returnRate = getReturnRate(winningAmount, LottoConfig.LOTTO_BUYING_UNIT.getValue() * myLotto.size());

        LottoStatistics lottoStatistics = new LottoStatistics(firstCount, secondCount, thirdCount, fourthCount, fifthCount, returnRate);

        return lottoStatistics;
    }

    public List<Lotto> getMyLotto(){
        return lottoRepository.getMyLotto();
    }

    private double getReturnRate(int winningAmount, int originalAmount){
        return (double) winningAmount / originalAmount * 100.0;
    }

    private int getSameCount(Lotto lotto, LottoWinning lottoWinning){
        Set<Integer> set = new HashSet<>();

        set.addAll(lotto.numbers());
        set.addAll(lottoWinning.getNumbers());

        return LottoConfig.LOTTO_NUMBER_COUNT.getValue() * 2 - set.size();
    }

    private boolean isContainsBonusNumber(Lotto lotto, LottoWinning lottoWinning){
        return lotto.numbers().contains(lottoWinning.getBonusNumber());
    }
}
