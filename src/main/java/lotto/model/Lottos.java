package lotto.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {

    private final List<Lotto> lottos;

    public Lottos(PurchaseAmount purchaseAmount) {
        this.lottos = new ArrayList<>();
        addLotto(purchaseAmount.getNumberOfTickets());
    }

    private void addLotto(int numberOfTickets) {
        for (int i = 0; i < numberOfTickets; i++) {
            Lotto newLotto = new Lotto(RandomNumbersGenerator.generate());
            lottos.add(newLotto);
        }
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}
