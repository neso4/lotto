package lotto.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Tickets;
import lotto.utils.Constants;
import lotto.utils.validation.Validator;

public class LottoManager {
    // Fields
    private final int TICKET_PRICE = Constants.TICKET_PRICE.getValue();
    private Validator validator = new Validator();
    private Tickets tickets = new Tickets();


    // Features
    public void buyTickets(String string) {
        int money = validator.validateMoney(string);
        int amount = calculateTicketAmount(money);
        tickets.generateTickets(amount);
    }

    public List<String> getTickets() {
        List<String> ticketList = new ArrayList<>();

        for (int i = 0; i < tickets.getTicketsCount(); i++) {
            String ticket = tickets.getTicketOfIndex(i);
            ticketList.add(ticket);
        }

        return ticketList;
    }

    public List<Integer> getTicketPoints(String winning, String bonus) {
        List<Integer> winningNumber = parseWinningNumbersToIntegerList(winning, bonus);
        List<Integer> points = tickets.getPointsForAllTickets(winningNumber);

        return points;
    }


    // Internal Implements
    // 문자열 형식으로 받은 당첨, 보너스 번호를 정수 리스트로 변환
    private List<Integer> parseWinningNumbersToIntegerList(String winning, String bonus) {
        List<Integer> numbers = Arrays
                .stream(winning.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int bonusNumber = Integer.parseInt(bonus);
        numbers.add(bonusNumber);

        return numbers;
    }

    private int calculateTicketAmount(int money) {
        int amount = money / TICKET_PRICE;

        return amount;
    }


    // testcode
    protected int getTicketsCount() {
        return tickets.getTicketsCount();
    }

}
