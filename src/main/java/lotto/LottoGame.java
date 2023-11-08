package lotto;

import java.util.*;

public class LottoGame {
    private static final InputResolver inputResolver = new InputResolver();
    private static final LottoGenerator lottoGenerator = new LottoGenerator();
    private static LottoWinningNumber lottoWinningNumber;
    private static Map<LottoWinningSpec, Integer> winningResult = new HashMap<>();
    public static void play() {
        lottoWinningNumber = new LottoWinningNumber(
                lottoGenerator.buyLotto(inputResolver.inputLottoBuy()),
                inputResolver.inputWinningNumber(),
                inputResolver.inputBonusNumber());
        checkLottoResult(lottoWinningNumber.getLottoList());
        System.out.println(winningResult);
    }

    private static void checkLottoResult(List<Lotto> lottoList) {
        for (Lotto lotto: lottoList) {
            Optional<LottoWinningSpec> optionalWinNumber = checkWinningNumber(lotto);
            if (optionalWinNumber.isPresent()) {
                LottoWinningSpec winningSpec = optionalWinNumber.get();
                setInitLottoResult(winningSpec);
                int winCount = winningResult.get(winningSpec);
                winningResult.put(winningSpec, ++winCount);
            }
        }
    }

    private static void setInitLottoResult(LottoWinningSpec winningSpec) {
        if (Optional.ofNullable(winningResult.get(winningSpec)).isEmpty()) {
            winningResult.put(winningSpec, 0);
        }
    }

    private static Optional<LottoWinningSpec> checkWinningNumber(Lotto lotto) {
        int count = 0;
        boolean isBonus = false;
        for (Integer winNumber : lotto.getNumbers()) {
            if (lottoWinningNumber.getWinningNumber().contains(winNumber)) {
                count++;
            }
            if (lottoWinningNumber.getBonusNumber() == winNumber) {
                isBonus = true;
            }
        }
        if (count == 5 && isBonus) {
            return Optional.of(LottoWinningSpec.SECOND_PRIZE);
        }
        return LottoWinningSpec.getRankByMatchingCount(count);
    }
}
