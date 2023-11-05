package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lotto.model.dto.WinningNumDTO;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos buyLottos(int numberOfLotto) {
        List<Lotto> new_lottos = new ArrayList<>();
        IntStream.range(0, numberOfLotto).forEach(i -> new_lottos.add(createLotto()));
        return new Lottos(new_lottos);
    }

    private static Lotto createLotto() {
        List<Integer> numbers = new ArrayList<>(pickRandomNumbers());
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    private static List<Integer> pickRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public String getPurchasedLottos() {
        StringBuilder sb = new StringBuilder();
        lottos.forEach(lotto -> sb.append(lotto.getSelectedNumbers()).append("\n"));
        return sb.toString();
    }

    public Result checkResult(WinningNumDTO winningNumDTO) {
        List<Ranking> rankings = new ArrayList<>();
        lottos.forEach(lotto -> {
            rankings.add(lotto.checkResult(winningNumDTO));
        });
        return new Result(rankings);
    }
}
