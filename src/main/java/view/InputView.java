package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String INPUT_WINNING_LOTTO_MESSAGE = "당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

	public String enterAmount() {
		System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
		return Console.readLine();
	}

	public String enterWinningLotto() {
		System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
		return Console.readLine();
	}

	public String enterBonusNumber() {
		System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
		return Console.readLine();
	}
}
