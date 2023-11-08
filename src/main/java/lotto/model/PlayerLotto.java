package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.NumberConst;

public class PlayerLotto {
    private List<Integer> playerLotto;

    public PlayerLotto() {
        this.playerLotto = new ArrayList<>(
                Randoms.pickUniqueNumbersInRange(NumberConst.MIN_RANGE, NumberConst.MAX_RANGE,
                        NumberConst.LOTTO_SIZE));
        Collections.sort(playerLotto);
    }

    public boolean hasBonus(Bonus bonus) {
        return playerLotto.contains(bonus.getBonus());
    }

    public int countIntersection(Lotto lotto) {
        Set<Integer> winningNumbers = new HashSet<>(lotto.getNumbers());
        Set<Integer> playerNumbers = new HashSet<>(playerLotto);

        winningNumbers.retainAll(playerNumbers);

        return winningNumbers.size();
    }

    @Override
    public String toString() {
        return playerLotto.toString();
    }
}
