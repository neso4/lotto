package lotto.Domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoPapers{
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_NUMBERS = 6;
    private final List<Lotto> lottoPapers;

    public LottoPapers(int amount){
        List<Lotto> lottoPapers = new ArrayList<>();
        this.lottoPapers = lottoPapers;
    }
}
