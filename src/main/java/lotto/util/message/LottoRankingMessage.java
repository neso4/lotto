package lotto.util.message;

import lotto.domain.RankInfo;

import java.text.NumberFormat;
import java.util.Arrays;

public enum LottoRankingMessage {
    FIRST("6개 일치 (%s원) - %s개", RankInfo.FIRST),
    SECOND("5개 일치, 보너스 볼 일치 (%s원) - %s개", RankInfo.SECOND),
    THIRD("5개 일치 (%s원) - %d개", RankInfo.THIRD),
    FOURTH("4개 일치 (%s원) - %d개", RankInfo.FOURTH),
    FIFTH("3개 일치 (%s원) - %d개", RankInfo.FIFTH);

    private final String message;
    private final RankInfo lottoRanking;

    LottoRankingMessage(String message, final RankInfo lottoRanking) {
        this.message = message;
        this.lottoRanking = lottoRanking;
    }

    public static String findLottoRankingMessage(final RankInfo rankInfo, Integer numberOfWins) {
        return Arrays.stream(LottoRankingMessage.values())
                .filter(message -> message.lottoRanking == rankInfo)
                .map(utils -> getFormat(rankInfo, numberOfWins, utils))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }


    private static String getFormat(RankInfo lottoRanking, Integer numberOfWins, LottoRankingMessage utils) {
        String prizeFormat = NumberFormat.getInstance().format(lottoRanking.getPrizeMoney());
        return String.format(utils.message, prizeFormat, numberOfWins);
    }
}
