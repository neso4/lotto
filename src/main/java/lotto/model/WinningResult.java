package lotto.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WinningResult {
    private final ConcurrentHashMap<List<Integer>, Winning> winningResults = new ConcurrentHashMap<List<Integer>, Winning>();

    public WinningResult(List<List<Integer>> userNumbers, List<Long> equalCounts) {
        for (List<Integer> userNumber : userNumbers) {
            winningResults.put(userNumber, new Winning(equalCounts.get(userNumbers.indexOf(userNumber))));
        }
    }

    public ConcurrentHashMap<List<Integer>, Winning> getWinngResult() {
        return new ConcurrentHashMap<>(winningResults);
    }
}
