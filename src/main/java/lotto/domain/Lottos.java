package lotto.domain;

import java.util.List;

public class Lottos {

    private final List<Lotto> elements;

    public Lottos(List<Lotto> elements) {
        this.elements = List.copyOf(elements);
    }

    public List<Lotto> toElements() {
        return elements;
    }
}
