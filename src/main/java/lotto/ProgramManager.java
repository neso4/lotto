package lotto;

import java.util.List;

public class ProgramManager {
	InputView inputView;
	OutputView outputView;
	ProgramManager() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	void startProgram() {

		int price = getPrice();
		showAmount(price);
		List<Lotto> lottoList = showBuyLotto();
		Customer customer = new Customer(price, lottoList);

		getLottoNumber();
		getBonusNumber();
		LottoHost lottoHost = new LottoHost();

		showResult();
	}

	int getPrice() {
		return 0;
	}

	void showAmount(int price) {

	}



	List<Lotto> showBuyLotto() {
		return null;
	}

	void getLottoNumber() {

	}

	void getBonusNumber() {

	}

	void showResult() {

	}
}
