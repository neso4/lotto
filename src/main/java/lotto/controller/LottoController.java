package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import lotto.service.LottoService;
import lotto.utils.InputValidator;
import lotto.view.InputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController() {
        this.lottoService = new LottoService();
    }

    public void start() {
        String lottoPayAmount;
        while (true) {
            try {
                InputView.printPayAmountInputMessage();
                lottoPayAmount = Console.readLine();
                InputValidator.checkLottoPayAmountInput(lottoPayAmount);

                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        lottoService.publishLottos(Integer.parseInt(lottoPayAmount));

        String jackpotNumberInput;
        while (true) {
            try {
                InputView.printJackpotNumberInputMessage();
                jackpotNumberInput = Console.readLine();
                InputValidator.checkJackpotNumberInput(jackpotNumberInput);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        List<Integer> jackpotNumbers = converteToList(jackpotNumberInput);

        InputView.printBonusNumberInputMessage();
        String bonusNumberInput = Console.readLine();
        // TODO : 보너스 번호 인풋 Validate

        long returnAmount = lottoService.announceWinningResult(jackpotNumbers, Integer.parseInt(bonusNumberInput));

        lottoService.announceRateOfReturn(Integer.parseInt(lottoPayAmount), returnAmount);

    }

    private List<Integer> converteToList(String jackpotNumberInput) {
        List<Integer> jackpotNumbers = new ArrayList<>();
        for (String number : jackpotNumberInput.split(",")) {
            jackpotNumbers.add(Integer.parseInt(number));
        }
        return jackpotNumbers;
    }
}
