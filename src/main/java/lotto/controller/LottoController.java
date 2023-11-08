package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoWinningRanking;
import lotto.domain.Lottos;
import lotto.dto.LottoWinningRakingCountMapDto;
import lotto.dto.LottosDto;
import lotto.exception.LottoException;
import lotto.exception.LottoStoreException;
import lotto.service.LottoProfitService;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoWinningNumbersPickService;
import lotto.service.LottoWinningRankingService;
import lotto.validation.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.vo.LottoWinningBonusNumber;
import lotto.vo.LottoWinningNumbers;

import java.util.EnumMap;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoWinningNumbersPickService lottoWinningNumbersPickService;
    private final LottoWinningRankingService lottoWinningRankingService;
    private final LottoProfitService lottoProfitService;

    public LottoController(final InputView inputView, final OutputView outputView, LottoPurchaseService lottoPurchaseService, LottoWinningNumbersPickService lottoWinningNumbersPickService, LottoWinningRankingService lottoWinningRankingService, LottoProfitService lottoProfitService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoPurchaseService = lottoPurchaseService;
        this.lottoWinningNumbersPickService = lottoWinningNumbersPickService;
        this.lottoWinningRankingService = lottoWinningRankingService;
        this.lottoProfitService = lottoProfitService;
    }

    public void run() {
        final Lottos userLottos = processLottoPurchaseTransaction();
        displayUserLottosInformation(userLottos);

        final LottoWinningNumbers lottoWinningNumbers = processLottoWinningNumbersPickTransaction();

        final EnumMap<LottoWinningRanking, Integer> winningRankingCountMap =
                processWinningRankingCalculationTransaction(userLottos, lottoWinningNumbers);
        displayWinningRankingCount(winningRankingCountMap);

        final double profit = processUserProfitCalculationTransaction(userLottos, winningRankingCountMap);
        displayProfit(profit);
    }

    private Lottos processLottoPurchaseTransaction() {
        while (true) {
            try {
                outputView.requestPurchaseAmount();
                int userPurchaseAmount = getUserPurchaseAmount();
                return lottoPurchaseService.purchaseAutoLottos(userPurchaseAmount);
            } catch (LottoException | LottoStoreException exception) {
                outputView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private int getUserPurchaseAmount() {
        String userPurchaseAmount = inputView.inputPurchaseAmount();
        Validator.validateLottoAmountNumeric(userPurchaseAmount);
        return parseInt(userPurchaseAmount);
    }

    private int parseInt(final String input) {
        return Integer.parseInt(input);
    }

    private void displayUserLottosInformation(final Lottos userLottos) {
        LottosDto lottosDto = convertLottosDto(userLottos);
        displayUserLottoPurchaseCount(lottosDto);
        displayUserLottos(lottosDto);
    }

    private LottosDto convertLottosDto(final Lottos userLottos) {
        return new LottosDto(userLottos);
    }

    private void displayUserLottoPurchaseCount(final LottosDto userLottosDto) {
        outputView.responsePurchaseCount(userLottosDto.getLottoGroup().size());
    }

    private void displayUserLottos(final LottosDto userLottosDto) {
        outputView.responseUserNumbersSet(userLottosDto);
    }

    private LottoWinningNumbers processLottoWinningNumbersPickTransaction() {
        Lotto winningLotto = processLottoWinningNumberPickTransaction();
        LottoWinningBonusNumber bonusNumber = processLottoWinningBonusNumberPickTransaction(winningLotto);
        return new LottoWinningNumbers(winningLotto, bonusNumber);
    }

    private Lotto processLottoWinningNumberPickTransaction() {
        while (true) {
            try {
                outputView.requestWinningNumbers();
                return getLottoWinningNumber();
            } catch (LottoException exception) {
                outputView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private Lotto getLottoWinningNumber() throws LottoException {
        String input = inputView.inputWinningNumbers();
        Validator.validatedWinningNumbersFormat(input);
        return lottoWinningNumbersPickService.pickWinningNumbers(input);
    }

    private LottoWinningBonusNumber processLottoWinningBonusNumberPickTransaction(Lotto lotto) {
        while (true) {
            try {
                outputView.requestBonusNumber();
                LottoWinningBonusNumber lottoWinningBonusNumber = getBonusNumber();
                lotto.validateAndThrowIfBonusNumberExists(lottoWinningBonusNumber);
                return lottoWinningBonusNumber;
            } catch (LottoException exception) {
                outputView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private LottoWinningBonusNumber getBonusNumber() throws LottoException {
        String input = inputView.inputBonusNumber();
        Validator.validateBonusNumberNumeric(input);
        return lottoWinningNumbersPickService.pickBonusNumber(input);
    }

    private EnumMap<LottoWinningRanking, Integer> processWinningRankingCalculationTransaction
            (final Lottos userLottos, LottoWinningNumbers lottoWinningNumbers) {

        return lottoWinningRankingService.countWinningRankings(userLottos, lottoWinningNumbers);
    }

    private void displayWinningRankingCount(final EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        outputView.responseWinningStatisticsHeader();
        outputView.responseWinningStatisticsBody(convertWinningRankingCountMapDto(winningRankingCountMap));
    }

    private LottoWinningRakingCountMapDto convertWinningRankingCountMapDto(final EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        return new LottoWinningRakingCountMapDto(winningRankingCountMap);
    }

    private double processUserProfitCalculationTransaction(final Lottos userLottos, EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        return lottoProfitService.calculateProfit(winningRankingCountMap, userLottos.getSize());
    }

    private void displayProfit(final double profit) {
        outputView.responseProfit(profit);
    }
}
