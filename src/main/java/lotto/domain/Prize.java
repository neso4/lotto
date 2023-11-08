package lotto.domain;

import lotto.constants.GameMessage;

public class Prize {
    private int firstPrize = 0;
    private int secondPrize = 0;
    private int thirdPrize = 0;
    private int forthPrize = 0;
    private int fifthPrize = 0;

    public void countPrize(Lottos lottos, WinningNumber winningNumber, BonusNumber bonusNumber) {
        for (int i = 0; i < lottos.getLottoCnt(); ++i) {
            Lotto lotto = lottos.getLotto(i);
            int matchCnt = compare(lotto, winningNumber);
            boolean hasBonus = false;

            if (matchCnt == 5) {
                hasBonus = lotto.contain(bonusNumber.getBonusNumber());
            }
            updatePrizeCount(matchCnt, hasBonus);
        }
    }

    public int compare(Lotto lotto, WinningNumber winningNumber) {
        return lotto.getMatchCnt(winningNumber);
    }

    private void updatePrizeCount(int matchCnt, boolean hasBonus) {
        if (matchCnt == 3) {
            fifthPrize++;
        } else if (matchCnt == 4) {
            forthPrize++;
        } else if (matchCnt == 5 && hasBonus) {
            secondPrize++;
        } else if (matchCnt == 5 ) {
            thirdPrize++;
        } else if (matchCnt == 6) {
            firstPrize++;
        }
    }

    public String getStatisticMessage() {
        return String.format(GameMessage.FIFTH_PRIZE_MESSAGE.getMessage(), fifthPrize) + "\n"
                + String.format(GameMessage.FORTH_PRIZE_MESSAGE.getMessage(), forthPrize) + "\n"
                + String.format(GameMessage.THIRD_PRIZE_MESSAGE.getMessage(), thirdPrize) + "\n"
                + String.format(GameMessage.SECOND_PRIZE_MESSAGE.getMessage(), secondPrize) + "\n"
                + String.format(GameMessage.FIRST_PRIZE_MESSAGE.getMessage(), firstPrize) + "\n";
    }
}
