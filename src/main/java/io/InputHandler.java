package io;

import camp.nextstep.edu.missionutils.Console;
import verifier.BonusNumberVerifier;
import verifier.MoneyVerifier;
import verifier.Verifier;
import verifier.WinnerNumberVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputHandler {

    private static final Verifier moneyVerifier = new MoneyVerifier();
    private static final Verifier bonusNumVerifier = new BonusNumberVerifier();
    private static final Verifier winnerNumberVerifier = new WinnerNumberVerifier();

    private InputHandler() {

    }

    public static int readMoney() {
        System.out.println("구입금액을 입력해 주세요");
        String input = Console.readLine();
        moneyVerifier.check(input);

        return Integer.parseInt(input);
    }

    public static List<Integer> readWinnerNumber() {
        System.out.println("당첨 번호를 입력해 주세요");
        String input = Console.readLine();
        winnerNumberVerifier.check(input);

        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static Integer readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        bonusNumVerifier.check(input);

        return Integer.parseInt(input);
    }


}
