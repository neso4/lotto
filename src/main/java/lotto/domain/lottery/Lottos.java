package lotto.domain.lottery;

import lotto.domain.lottery.generator.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottoTickets;

    private Lottos(final Buyer buyer) {
        final int ticketCount = buyer.getTicketCount();
        this.lottoTickets = generateLottos(ticketCount);
    }

    public static Lottos create(final Buyer buyer) {
        return new Lottos(buyer);
    }

    private List<Lotto> generateLottos(final int ticketCount) {
        return Stream.generate(RandomNumberGenerator::generateOrderedLottoNumbers)
                .limit(ticketCount)
                .toList()

                .stream()
                .map(Lotto::new)
                .toList();
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }
}
