package lotto.domain;

import static lotto.utils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.constants.NumberConstant.ZERO;

import java.util.List;
import java.util.stream.IntStream;

public class Lotteries {
    
    private final List<Lotto> lotteries;
    
    private Lotteries(final List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }
    
    public static Lotteries from(final Payment payment) {
        final List<Lotto> result = createLotteries(payment);
        return new Lotteries(result);
    }
    
    private static List<Lotto> createLotteries(final Payment payment) {
        return IntStream.range(ZERO, payment.receiveIssuedLottoCount())
                .mapToObj(index -> new Lotto(pickUniqueNumbersInRange()))
                .toList();
    }
    
    public List<String> receiveLotteriesInfo() {
        return lotteries.stream()
                .map(Lotto::toString)
                .toList();
    }

    public MatchingResults generateGameResult(final WinningLotto winningLotto) {
        final List<MatchingResult> results = lotteries.stream()
                .map(lotto -> MatchingResult.of(lotto, winningLotto))
                .toList();

        return MatchingResults.from(results);
    }
}
