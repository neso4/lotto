package lotto.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Bonus;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.exception.LottoException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoService {
	private Lottos lottos;
	private LottoWinningNumbers lottoWinningNumbers;
	private Bonus bonus;

	public void createLotto(int numbers) {
		lottos = new Lottos(createLottos(numbers));
	}

	public void printLottos(int number) {
		OutputView.printLottos(number, lottos.getLotts());
		System.out.println();
	}

	public void setUpWinningAndBonusLotto() {
		setUpWinningLotto();
		System.out.println();
		setUpBonusLotto();
	}

	private List<Lotto> createLottos(int numbers) {
		try {
			return Stream.generate(Lotto::createLotto)
				.limit(numbers)
				.collect(Collectors.toList());
		} catch(LottoException e) {
			System.out.println(e.getMessage());
			createLotto(numbers);
		}
		return null;
	}

	private void setUpWinningLotto() {
		try {
			InputView.askWinningNumbers();
			lottoWinningNumbers = new LottoWinningNumbers(Console.readLine());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			setUpWinningLotto();
		}
	}

	private void setUpBonusLotto() {
		try {
			InputView.askBonusNumber();
			bonus = new Bonus(Console.readLine());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			setUpBonusLotto();
		}
	}
}
