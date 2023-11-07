package lotto.domain;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constant.NumbersOption;

public class RandomNumberCreator {

	public List<Integer> create() {
		return Randoms.pickUniqueNumbersInRange(NumbersOption.MIN, NumbersOption.MAX, NumbersOption.SIZE);
	}

}
