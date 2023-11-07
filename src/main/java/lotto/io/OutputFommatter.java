package lotto.io;

import java.util.EnumMap;
import lotto.constant.LottoResultRule;
import lotto.constant.ProcessMessage;
import lotto.constant.ResultMessage;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Ticket;
import lotto.domain.WinningStatistic;

public class OutputFommatter {

    private static final int NUNECESSARY_INDEX = 2;

    public String toNumberOfTicket(final Ticket ticket) {
        return String.format(ProcessMessage.NUMBER_OF_LOTTO_TICKET.toMessage(), ticket.toValue());
    }

    public String toLottos(final Lottos lottos) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos.toElements()) {
            stringBuilder.append(ProcessMessage.LEFT_SQUARE_BRACKET.toMessage());
            for (Integer number : lotto.getNumbers()) {
                stringBuilder.append(number).append(ProcessMessage.COMMA.toMessage());
            }
            stringBuilder.delete(stringBuilder.length() - NUNECESSARY_INDEX, stringBuilder.length());
            stringBuilder.append(ProcessMessage.RIGHT_SQUARE_BRACKET.toMessage() + ProcessMessage.NEW_LINE.toMessage());
        }
        return stringBuilder.toString();
    }

    public String toLottoResult(final WinningStatistic winningStatistic) {
        final StringBuilder stringbuilder = new StringBuilder();
        EnumMap<LottoResultRule, Integer> enumMap = winningStatistic.toelements();
        stringbuilder.append(String.format(ResultMessage.THREE_MATCH.toMessage(),
                        enumMap.get(LottoResultRule.THREE_MATCH).toString()))
                .append(ProcessMessage.NEW_LINE.toMessage());
        stringbuilder.append(String.format(ResultMessage.FOUR_MATCH.toMessage(),
                        enumMap.getOrDefault(LottoResultRule.FOUR_MATCH, 0).toString()))
                .append(ProcessMessage.NEW_LINE.toMessage());
        stringbuilder.append(String.format(ResultMessage.FIVE_MATCH.toMessage(),
                        enumMap.getOrDefault(LottoResultRule.FIVE_MATCH, 0).toString()))
                .append(ProcessMessage.NEW_LINE.toMessage());
        stringbuilder.append(String.format(ResultMessage.FIVE_MATCH_WITH_BONUS.toMessage(),
                        enumMap.getOrDefault(LottoResultRule.FIVE_MATCH_WITH_BONUS, 0).toString()))
                .append(ProcessMessage.NEW_LINE.toMessage());
        stringbuilder.append(String.format(ResultMessage.SIX_MATCH.toMessage(),
                        enumMap.getOrDefault(LottoResultRule.SIX_MATCH, 0).toString()));
        return stringbuilder.toString();
    }

    public String toLottoProfit(final String profit) {
        return String.format(ResultMessage.Profit.toMessage(), profit);
    }
}
