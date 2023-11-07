package lotto.controller;

import java.util.List;
import lotto.domain.Amount;
import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.Result;
import lotto.domain.WinningNumber;
import lotto.dto.LottoResponseDtos;
import lotto.dto.LottoResponseDtos.LottoResponseDto;
import lotto.dto.ResultResponseDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Amount amount = getAmount();
            List<LottoResponseDto> lottoDtos = createLottos(amount.getLottoCount());
            WinningNumber winningNumber = getWinningNumber();
            Result result = createResult(lottoDtos, winningNumber);
            getYield(amount, result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Amount getAmount() {
        String money = inputView.getPurchaseAmount();
        Amount amount = new Amount(money);
        return amount;
    }

    private List<LottoResponseDto> createLottos(int count) {
        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = new Lottos(lottoMachine.createAllLotto(count));
        LottoResponseDtos lottosDto = lottos.toResponseDto();
        outputView.printLottos(count, lottosDto);
        return lottosDto.getLottoResponseDtos();
    }

    private WinningNumber getWinningNumber() {
        String numbers = inputView.getWinningNumber();
        String bonusNumber = inputView.getBonusNumber();
        WinningNumber winningNumber = new WinningNumber(numbers, bonusNumber);
        return winningNumber;
    }

    private Result createResult(List<LottoResponseDto> lottoDtos, WinningNumber winningNumber) {
        Result result = new Result(lottoDtos, winningNumber);
        ResultResponseDto responseDto = result.toResponseDto();
        outputView.printWinningStatistics(responseDto);
        return result;
    }
    
    private void getYield(Amount amount, Result result) {
        int totalPrice = result.calculateTotalPrice();
        double yield = amount.calculateYield(totalPrice);
        outputView.printYield(yield);
    }
}
