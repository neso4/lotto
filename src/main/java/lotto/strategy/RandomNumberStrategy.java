package lotto.strategy;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Stream;

public class RandomNumberStrategy implements NumberGenerationStrategy{

    @Override
    public List<Integer> generateNumbers(int count, int min, int max){
        return Stream.generate(() -> Randoms.pickNumberInRange(min,max))
                .distinct()
                .limit(count)
                .toList();
    }
}
