package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private List<Integer> winningNumbers;
    private Integer bonusNumber;
    private List<Lotto> lottos;


    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void generateLottos() {
        this.lottos = new ArrayList<>();
    }
}
