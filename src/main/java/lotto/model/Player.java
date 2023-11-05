package lotto.model;

import java.util.ArrayList;
import java.util.List;
import lotto.util.NumberGenerator;
import lotto.vo.TicketQuantity;

public class Player {

    private final List<Lotto> lotteries;

    private Player(final List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public static Player createPlayer(final TicketQuantity ticketQuantity, final NumberGenerator numberGenerator) {
        List<Lotto> lotteries = new ArrayList<>();
        for (int i = 0; i < ticketQuantity.quantity(); i++) {
            lotteries.add(Lotto.createPlayerLotto(numberGenerator));
        }
        return new Player(lotteries);
    }

    public List<Lotto> getLotteries() {
        return lotteries;
    }
}
