package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoFinalResult {
    private static final int INIT_VALUE = 0;
    public static final int ADD_VALUE = 1;

    private final Map<LottoResult, Integer> finalResultMap = new EnumMap<>(LottoResult.class);

    public LottoFinalResult() {
        initLottoResult();
    }

    public void addLottoResult(LottoResult lottoResult) {
        Integer count = finalResultMap.get(lottoResult);
        finalResultMap.replace(lottoResult, count + ADD_VALUE);
    }

    public int calculateReturn() {
        return finalResultMap.entrySet()
                .stream()
                .mapToInt((set) -> set.getKey().getWinningAmount() * set.getValue())
                .sum();
    }

    private void initLottoResult() {
        Arrays.stream(LottoResult.values())
                .forEach((value) -> finalResultMap.put(value, INIT_VALUE));
    }

    public Map<LottoResult, Integer> getFinalResultMap() {
        return Collections.unmodifiableMap(finalResultMap);
    }
}
