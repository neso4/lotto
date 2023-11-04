package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMoney {
    private final int inputMoney;
    private final int count;
    private List<List<Integer>> numbers = new ArrayList<>();

    public LottoMoney(int inputMoney, int count) {
        this.inputMoney = inputMoney;
        this.count = count;
    }

    public int getInputMoney() {
        return inputMoney;
    }

    public void setNumbers() {
        System.out.println();
        for (int i = 0; i < count; i++) {
            numbers.add(getRandomLotto());
        }
        for (List<Integer> number : numbers) {
            System.out.println(number);
        }
        System.out.println();
    }

    private List<Integer> getRandomLotto() {
        List<Integer> list = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        list.sort((o1, o2) -> o1 - o2);
        return list;
    }
}
