package lotto.service;

import static lotto.util.validation.ModelCensor.validateAnnouncementNumber;
import static lotto.util.validation.ModelCensor.validatePurchaseUnit;
import static lotto.util.rule.GameRule.RANK_SIZE;
import static lotto.util.rule.GameRule.TICKET_PRICE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Ticket;
import lotto.domain.WinningTicket;
import lotto.repository.MemoryTicketRepository;
import lotto.util.AutomaticGenerator;

public class TicketService {

    private final MemoryTicketRepository memoryTicketRepository;
    private final Map<Integer, Integer> prizeMap;

    public TicketService(MemoryTicketRepository memoryTicketRepository) {
        this.memoryTicketRepository = memoryTicketRepository;
        prizeMap = new HashMap<>();
        prizeMap.put(6, 0);
        prizeMap.put(5, 1);
        prizeMap.put(4, 3);
        prizeMap.put(3, 4);
    }

    public Integer getPurchaseAmount() {
        return memoryTicketRepository.findAll().size() * TICKET_PRICE.getValue();
    }

    public Integer purchaseAmount(String input) {
        Integer money = Integer.parseInt(input);
        validatePurchaseUnit(money);
        return money / TICKET_PRICE.getValue();
    }

    public Ticket automaticPurchase() {
        Ticket ticket = new Ticket();
        ticket.setTicket(new Lotto(AutomaticGenerator.generateLottoNumbers()));
        return memoryTicketRepository.purchase(ticket);
    }

    public WinningTicket announcementNumber(String input) {
        WinningTicket ticket = new WinningTicket();
        List<Integer> numbers = mapWinningNumbers(input);
        validateAnnouncementNumber(numbers);
        ticket.setNumbers(new Lotto(numbers));
        return memoryTicketRepository.announcement(ticket);
    }

    public Lotto getWinningTicket() {
        return memoryTicketRepository.findNumbers().getLotto();
    }

    private List<Integer> mapWinningNumbers(String input) {
        String[] numberStrings = input.split(",");
        return Arrays.stream(numberStrings)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int[] prizeCount() {
        int[] prizeCount = new int[RANK_SIZE.getValue()];
        List<Ticket> tickets = memoryTicketRepository.findAll();
        List<Integer> winningNumbers = memoryTicketRepository.findNumbers().getLotto().getNumbers();
        Integer bonusNumber = memoryTicketRepository.findBonusNumber().getBonusNumber();

        for (Ticket ticket : tickets) {
            int matchCount = matchNumbers(ticket, winningNumbers);
            boolean hasBonus = hasBonus(ticket, bonusNumber);
            int prizeRank = calculatePrizeRank(matchCount, hasBonus);

            if (prizeRank >= 0) {
                prizeCount[prizeRank]++;
            }
        }

        return prizeCount;
    }

    private int matchNumbers(Ticket ticket, List<Integer> winningNumbers) {
        List<Integer> ticketNumbers = ticket.getLotto().getNumbers();
        return (int) ticketNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean hasBonus(Ticket ticket, Integer bonusNumber) {
        List<Integer> ticketNumbers = ticket.getLotto().getNumbers();
        return ticketNumbers.contains(bonusNumber);
    }

    private int calculatePrizeRank(int matchCount, boolean hasBonus) {
        return prizeMap.getOrDefault(matchCount, -1);
    }

}
