package lotto.model;

import java.util.Arrays;
import java.util.List;

public enum LottoResult {
    FIFTH(3, 0, "3개 일치", 5000),
    FOURTH(4, 0, "4개 일치", 50000),
    THIRD(5, 0, "5개 일치", 1500000),
    SECOND(5, 1, "5개 일치, 보너스 볼 일치", 30000000),
    FIRST(6, 0, "6개 일치", 2000000000),
    DEFAULT(0, 0, "초기값", 0);

    private final int winningCnt;
    private final int bonusCnt;
    private final String rule;
    private final long prize;

    LottoResult(int winningCnt, int bonusCnt, String rule, int prize) {
        this.winningCnt = winningCnt;
        this.bonusCnt = bonusCnt;
        this.rule = rule;
        this.prize = prize;
    }

    public String getRule() {
        return rule;
    }

    public long getPrize() {
        return prize;
    }

    public static LottoResult compareLottoNums(List<Integer> LottoNums, WinningLotto winningLotto) {
        int equalLottoCnt = countEqualLotto(LottoNums, winningLotto.getLottoNums());
        int equalBonusCnt = countEqualBonus(LottoNums, winningLotto.getBonus());
        return findCorrectLottoResult(equalLottoCnt, equalBonusCnt);
    }

    private static int countEqualBonus(List<Integer> LottoNums, int bonus) {
        return (int) LottoNums.stream()
                .filter(userLottoNum -> userLottoNum.equals(bonus))
                .count();
    }

    private static int countEqualLotto(List<Integer> LottoNums, List<Integer> winningLottoNums) {
        return (int) LottoNums.stream()
                .filter(winningLottoNums::contains)
                .count();
    }

    private static LottoResult findCorrectLottoResult(int equalLottoCnt, int equalBonusCnt) {
        LottoResult findLottoResult = null;
        List<LottoResult> lottoResults = getLottoResultExceptDefault();
        for (LottoResult lottoResult : lottoResults) {
            if (lottoResult.winningCnt != equalLottoCnt) {
                continue;
            }
            if (checkSecondGrade(equalBonusCnt, lottoResult)) {
                continue;
            }
            findLottoResult = lottoResult;
        }
        return findLottoResult;
    }

    private static List<LottoResult> getLottoResultExceptDefault() {
        return Arrays.stream(LottoResult.values())
                .filter(result -> result != LottoResult.DEFAULT)
                .toList();
    }

    private static boolean checkSecondGrade(int equalBonusCnt, LottoResult lottoResult) {
        return lottoResult.winningCnt == LottoResult.SECOND.winningCnt
                && lottoResult.bonusCnt != equalBonusCnt;
    }
}
