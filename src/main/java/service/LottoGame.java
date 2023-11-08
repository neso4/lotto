package service;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;
import lotto.LottoRank;
import util.LottoNumberGenerator;

import java.util.*;

import static util.LottoStatistics.printStatistics;
import static util.TicketPurchaseAmountValidator.validatePurchaseAmount;

public class LottoGame {
    static final int TICKET_PRICE = 1000;
    static final int MIN_LOTTO_NUMBER = 1;
    static final int MAX_LOTTO_NUMBER = 45;
    static final int NUMBER_OF_LOTTO_NUMBERS = 6;
    static final int NUMBER_OF_LOTTO_BONUS_NUMBER = 1;

    public void start() {
        int ticketPurchaseAmount = getTicketPurchaseAmount();
        int ticketQuantity = ticketPurchaseAmount / TICKET_PRICE;
        List<Lotto> tickets = getTickets(ticketQuantity);

        printGeneratedNumbers(ticketQuantity, tickets);

        Lotto winningNumbers = getWinningNumbers();
        Lotto bonusNumber = getBonusNumber();

        Map<LottoRank, Integer> rankCounter = getRankCounter(tickets, winningNumbers, bonusNumber);

        printStatistics(rankCounter, ticketPurchaseAmount);
    }

    private Map<LottoRank, Integer> getRankCounter(List<Lotto> tickets, Lotto winningNumbers, Lotto bonusNumber) {
        Map<LottoRank, Integer> rankCounter = new HashMap<>();

        for (var value : LottoRank.values()) {
            rankCounter.put(value, 0);
        }

        countMatchedNumbers(tickets, winningNumbers, bonusNumber, rankCounter);
        return rankCounter;
    }

    private void countMatchedNumbers(List<Lotto> tickets, Lotto winningNumbers, Lotto bonusNumber, Map<LottoRank, Integer> rankCounter) {
        for (Lotto ticket : tickets) {
            int matchedCount = countMatchedCount(ticket.getNumbers(), winningNumbers.getNumbers());

            LottoRank rank = getRank(bonusNumber, ticket, matchedCount);
            rankCounter.put(rank, rankCounter.get(rank) + 1);
        }
    }

    private LottoRank getRank(Lotto bonusNumber, Lotto ticket, int matchedCount) {
        if (matchedCount == LottoRank.FIRST.getMatchedCount()) {
            return LottoRank.FIRST;
        } else if (matchedCount == LottoRank.SECOND.getMatchedCount() && checkBonusNumber(ticket.getNumbers(), bonusNumber.getNumbers())) {
            return LottoRank.SECOND;
        } else if (matchedCount == LottoRank.THIRD.getMatchedCount()) {
            return LottoRank.THIRD;
        } else if (matchedCount == LottoRank.FOURTH.getMatchedCount()) {
            return LottoRank.FOURTH;
        } else if (matchedCount == LottoRank.FIFTH.getMatchedCount()) {
            return LottoRank.FIFTH;
        }

        return LottoRank.FAIL;
    }

    private boolean checkBonusNumber(List<Integer> ticket, List<Integer> bonusNumber) {
        return ticket.contains(bonusNumber.get(0));
    }

    private int countMatchedCount(List<Integer> ticket, List<Integer> winningNumbers) {
        int matchedCount = 0;

        for (var number : ticket) {
            if (winningNumbers.contains(number)) {
                matchedCount++;
            }
        }

        return matchedCount;
    }

    private Lotto getBonusNumber() {
        List<Integer> bonusNumber;
        Lotto lotto;
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                bonusNumber = new ArrayList<>();
                bonusNumber.add(Integer.parseInt(Console.readLine()));
                lotto = new Lotto(bonusNumber, MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, NUMBER_OF_LOTTO_BONUS_NUMBER);
                System.out.println();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto;
    }


    private Lotto getWinningNumbers() {
        List<Integer> winningNumbers;
        Lotto lotto;
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                winningNumbers = Arrays.stream(Console.readLine().split(",")).map(Integer::parseInt).toList();
                lotto = new Lotto(winningNumbers, MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, NUMBER_OF_LOTTO_NUMBERS);
                System.out.println();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto;
    }

    private void printGeneratedNumbers(int ticketQuantity, List<Lotto> tickets) {
        System.out.println(ticketQuantity + "개를 구매했습니다.");

        for (Lotto ticket : tickets) {
            System.out.println(ticket.getNumbers());
        }

        System.out.println();
    }

    private List<Lotto> getTickets(int ticketQuantity) {
        List<Lotto> tickets = new ArrayList<>();

        for (int i = 0; i < ticketQuantity; i++) {
            Lotto ticket = new Lotto(LottoNumberGenerator.generate(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, NUMBER_OF_LOTTO_NUMBERS), MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, NUMBER_OF_LOTTO_NUMBERS);
            tickets.add(ticket);
        }

        System.out.println();
        return tickets;
    }

    private int getTicketPurchaseAmount() {
        int ticketPurchaseAmount;

        while (true) {
            try {
                String input = getInput();
                validatePurchaseAmount(input);
                ticketPurchaseAmount = Integer.parseInt(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return ticketPurchaseAmount;
    }

    private String getInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }
}
