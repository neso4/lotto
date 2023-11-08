package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static final int ERROR = -1;
    GameService gameService = new GameService();

    public void start() {
        int ticketCount = cashToTicket();
        buyTicket(ticketCount);
        List<Integer> winNumber = pickWinNumber();
        int bonusNumber = pickBonusNumber(winNumber);
        showResult();
    }

    public int cashToTicket() {
        int ticketCount;

        while (true) {
            String rawCash = inputCash();
            ticketCount = gameService.validCash(rawCash);
            if (ticketCount != ERROR) {
                break;
            }
        }
        return ticketCount;
    }

    public String inputCash() {
        System.out.println("구입 금액을 입력해 주세요.");
        return Console.readLine();
    }

    public void buyTicket(int ticketCount) {
        System.out.println("\n" + ticketCount + "개를 구매했습니다.");
        for (int count = 1; count <= ticketCount; count++) {
            String generateResult = gameService.generateNumber();
            System.out.println(generateResult);
        }
        System.out.println();
    }

    public String inputWinNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public List<Integer> pickWinNumber() {
        List<Integer> winNumber;

        while (true) {
            String rawWinNumber = inputWinNumber();
            winNumber = gameService.validWinNumber(rawWinNumber);
            if (winNumber != null) {
                break;
            }
        }
        System.out.println();
        return winNumber;
    }

    public String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public int pickBonusNumber(List<Integer> winNumber) {
        int bonusNumber;

        while (true) {
            String rawBonusNumber = inputBonusNumber();
            bonusNumber = gameService.validBonusNumber(rawBonusNumber, winNumber);
            if (bonusNumber != ERROR) {
                break;
            }
        }
        System.out.println();
        return bonusNumber;
    }

    public void showResult() {
        System.out.println("당첨 통계\n---");
        List<String> scoreResultAndEarnRate = gameService.makeScoreResultAndEarnRate(gameService.calcTotalScore());
        scoreResultAndEarnRate.forEach(System.out::println);
    }
}
