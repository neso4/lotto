package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Ranking;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_TICKET_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String WINNING_STASTISTICS_MESSAGE = "당첨 통계";
    private static final String WINNING_RESULT_MESSAGE = "%s - %d개";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%s입니다.";
    private static final String LINE = "---";
    private static final String PERCENTAGE = "%";
    
    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    public void printTicketCount(int ticketCount) {
        String sentence = String.format(PURCHASE_TICKET_COUNT_MESSAGE, ticketCount);
        System.out.println(sentence);
    }
    
    public void printTickets(List<Lotto> lottoTickets) {
        lottoTickets.stream()
                .map(this::sortByAscending)
                .forEach(System.out::println);
        printNewLine();
    }
    
    private List<Integer> sortByAscending(Lotto ticket) {
        return ticket.getNumbers().stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));
    }
    
    public void printWinningstatistics(Map<Ranking, Integer> winningResult) {
        System.out.println(WINNING_STASTISTICS_MESSAGE);
        System.out.println(LINE);
        System.out.println(makeWinningStatisticsText(winningResult));
    }
    
    private String makeWinningStatisticsText(Map<Ranking, Integer> winningResult) {
        return winningResult.entrySet().stream()
                .map(entry -> String.format(WINNING_RESULT_MESSAGE, entry.getKey().getMessage(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
    
    public void printProfitRate(double profitRate) {
        String sentence = String.format(PROFIT_RATE_MESSAGE, profitRate, PERCENTAGE);
        System.out.println(sentence);
    }
    
    public void printNewLine() {
        System.out.println();
    }
}
