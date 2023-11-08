package lotto.domain;

import java.util.Iterator;
import java.util.stream.IntStream;

import static lotto.constants.Constants.Integers.ITERATE_START_NUMBER;
import static lotto.constants.Constants.Integers.LOTTO_PRICE;

public class PurchaseQuantity implements Iterable<Integer> {

    private final int purchaseQuantity;

    public PurchaseQuantity(int budget) {
        this.purchaseQuantity = budget / LOTTO_PRICE.getValue();
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.rangeClosed(ITERATE_START_NUMBER.getValue(), purchaseQuantity).iterator();
    }

    @Override
    public String toString() {
        return String.valueOf(purchaseQuantity);
    }
}
