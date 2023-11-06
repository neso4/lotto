package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.User;
import lotto.domain.WinningLottoNumbers;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        User user = buyLottoTicket();
        Lotto winningNumbers = getWinningNumbers();
        WinningLottoNumbers winningLottoNumbers = getBonusNumber(winningNumbers);
        LottoResult lottoResult = calculateLottoResult(user, winningLottoNumbers);
        calculateReturnRate(user, lottoResult);
    }

    public User buyLottoTicket() {
        int buyAmount = validateBuyAmount();
        User user = lottoService.buyLottoTicket(buyAmount);
        outputView.printLottoTicket(user.getLottoTicket());
        return user;
    }

    public Lotto getWinningNumbers() {
        return validateWinningNumbers();
    }

    public WinningLottoNumbers getBonusNumber(Lotto winningNumbers) {
        return validateBonusNumber(winningNumbers);
    }

    public LottoResult calculateLottoResult(User user, WinningLottoNumbers winningLottoNumbers) {
        LottoResult lottoResult = lottoService.calculateLottoResult(user, winningLottoNumbers);
        outputView.printLottoResult(lottoResult.getLottoResult());
        return lottoResult;
    }

    public void calculateReturnRate(User user, LottoResult lottoResult) {
        double returnRate = lottoService.calculateReturnRate(user, lottoResult);
        outputView.printReturnRate(returnRate);
    }

    private int validateBuyAmount() {
        int buyAmount;
        while (true) {
            try {
                buyAmount = inputView.inputBuyAmount();
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
        return buyAmount;
    }

    private Lotto validateWinningNumbers() {
        List<Integer> winningNumbers;
        Lotto validatedWinningNumbers;
        while (true) {
            try {
                winningNumbers = inputView.inputWinningNumbers();
                validatedWinningNumbers = lottoService.getWinningNumbers(winningNumbers);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
        return validatedWinningNumbers;
    }

    private WinningLottoNumbers validateBonusNumber(Lotto winningNumbers) {
        int bonusNumber;
        WinningLottoNumbers winningLottoNumbers;
        while (true) {
            try {
                bonusNumber = inputView.inputBonusNumber();
                winningLottoNumbers = lottoService.getBonusNumber(winningNumbers, bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
        return winningLottoNumbers;
    }
}