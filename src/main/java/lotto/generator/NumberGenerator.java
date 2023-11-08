package lotto.generator;

import java.util.List;

public interface NumberGenerator {

    List<Integer> generate(int start, int end, int size);
}
