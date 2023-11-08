package lotto.Controller;

import lotto.Domain.LottoGameDecisionMachine;
import lotto.Domain.Prize;
import lotto.View.LottoGameView;
import lotto.Domain.*;


import java.util.*;

import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.Console.readLine;


public class LottoGameController {
    private LottoGameDecisionMachine machine;
    private LottoGameView view;

    public LottoGameController() {
        machine = new LottoGameDecisionMachine();
        view = new LottoGameView();
    }

    public void run() {

        // 구입 금액 입력
        int purchaseAmount = getPurchaseAmountFromUser();
        int numTickets = purchaseAmount / 1000;

        machine.generateTickets_(purchaseAmount);

        // 발행한 로또 번호 출력
        view.displayNumberOfTickets(machine.getTickets().size());
        view.displayTickets(machine.getTickets());

        // 당첨 번호 입력
        List<Integer> winningNumbers = getWinningNumbersFromUser();
        int bonusNumber = getBonusNumberFromUser();

        machine.setWinningNumbers(winningNumbers, bonusNumber);

        // 당첨 확인 및 결과 출력
        Map<Integer, Integer> results = machine.checkTickets();
        view.showWinning(results);

        // 수익률 계산 및 출력
        double revenueRate = calculateRevenueRate(purchaseAmount, results);
        view.displayTotalRevenueRate(revenueRate);
    }

    private int getPurchaseAmountFromUser() {
        String ERROR_MESSAGE = "[ERROR]";
        int purchaseAmount;
        do {
            System.out.println("구입금액을 입력해 주세요: ");
            String input = readLine();

            try {
                int money = Integer.parseInt(input);

            } catch (NoSuchElementException e) {
                System.err.println(ERROR_MESSAGE);
                throw new NoSuchElementException(ERROR_MESSAGE);
            }
            purchaseAmount =Integer.parseInt(input);
        }while (purchaseAmount % 1000 != 0);
        return purchaseAmount;
    }

    private List<Integer> getWinningNumbersFromUser() {
        System.out.println("당첨 번호를 입력해 주세요 (1~45 사이 숫자, 쉼표로 구분): ");
        String input = readLine();
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int getBonusNumberFromUser() {
        System.out.print("보너스 번호를 입력해 주세요 (1~45 사이 숫자): ");
        return Integer.parseInt(readLine());
    }

    private double calculateRevenueRate(int purchaseAmount, Map<Integer, Integer> results) {
        int totalPrize = results.entrySet().stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
        int totalPrize_ = Prize.FIFTH_PLACE.getPrizeMoney() * Prize.FIFTH_PLACE.getCount() +
                Prize.FOURTH_PLACE.getPrizeMoney() * Prize.FOURTH_PLACE.getCount() +
                Prize.THIRD_PLACE.getPrizeMoney() * Prize.THIRD_PLACE.getCount() +
                Prize.SECOND_PLACE.getPrizeMoney() * Prize.SECOND_PLACE.getCount() +
                Prize.FIRST_PLACE.getPrizeMoney() * Prize.FIRST_PLACE.getCount();
        return (double) totalPrize_ / purchaseAmount * 100 ;
    }

}

