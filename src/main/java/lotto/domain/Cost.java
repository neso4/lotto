package lotto.domain;

import static lotto.constant.CostConstant.UNIT;

import lotto.exception.InvalidInput;

public class Cost {
    private final int paidCost;

    public Cost(int paidCost) {
        validate(paidCost);
        this.paidCost = paidCost;;
    }

    public int getQuantity() {
        return paidCost / UNIT.getValue();
    }

    private void validate(int paidCost) {
        InvalidInput invalidInput = new InvalidInput();

        if (invalidInput.notThousandUnitException(paidCost)) {
            throw new IllegalArgumentException();
        }
    }

}
