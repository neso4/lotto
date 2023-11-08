package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.Number;

public class LottoDatas {
    private List<Lotto> lottoNumbers;
    private BonusNumber bonusNumber;
    private PurChase purchase;
    private Lotto winnigNumber;
    private List<WinningData> winningData;

    public LottoDatas() { //초기화 시키기 위해작성
        this.lottoNumbers = new ArrayList<>();
        this.bonusNumber = null;
        this.purchase = null;
        this.winnigNumber = null;
        this.winningData = new ArrayList<>();
    }


    public void saveCount(int purchase) {
        this.purchase = PurChase.inputPurchase(purchase);
    }

    public void saveLottoNumbers(final List<Integer> generatedLottoNumbers) {
        Lotto lotto = Lotto.createLotto(generatedLottoNumbers);
        lottoNumbers.add(lotto);
    }

    public void inputWinningNumber(final List<Integer> generatedLottoNumbers) {
        Lotto lotto = Lotto.createLotto(generatedLottoNumbers);
        this.winnigNumber = lotto;
    }

    public void inputBonusNumber(final int bonusNumber) {
        this.bonusNumber = BonusNumber.inputBonusNumber(bonusNumber);
    }

    public void saveWinningDatas(final List<Integer> generatedLottoNumbers) {
        WinningData winningData = WinningData.inputWinningDatas(generatedLottoNumbers);
        this.winningData.add(winningData);
    }

    public List<Integer> getlottoNumbers(int index) {
        List<Integer> lottoNumbersIntegers = new ArrayList<>();
        lottoNumbersIntegers.addAll(lottoNumbers.get(index).getNumbers());
        return lottoNumbersIntegers;
    }

    public int getLottoCount() {
        return purchase.getLottoCount();
    }

    public List<Integer> getWinningNumbers() {
        return winnigNumber.getNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getBonusNumber();
    }

    public void generateLottoNumbers() {
        int lottoCount = purchase.getLottoCount();
        for (int generateLottoCount = 0; generateLottoCount < lottoCount; generateLottoCount++) {
            saveLottoNumbers(generateLottoNumber());
        }
    }

    private List<Integer> generateLottoNumber() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(
                Number.THE_SMALLEST_LOTTO_NUMBER.getMessage(),
                Number.THE_BIGGEST_LOTTO_NUMBER.getMessage(),
                Number.LOTTO_LENGTH_LIMIT.getMessage());
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
