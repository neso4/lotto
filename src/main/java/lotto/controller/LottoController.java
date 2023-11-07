package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.LottoWinningRanking;
import lotto.dto.LottoWinningRakingCountMapDto;
import lotto.dto.LottosDto;
import lotto.vo.LottoWinningNumbers;
import lotto.exception.LottoException;
import lotto.exception.LottoStoreException;
import lotto.service.LottoProfitService;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoWinningRankingService;
import lotto.service.LottoWinningNumbersPickService;
import lotto.validation.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.vo.LottoWinningBonusNumber;

import java.util.EnumMap;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoWinningNumbersPickService lottoWinningNumbersPickService;
    private final LottoWinningRankingService lottoWinningRankingService;
    private final LottoProfitService lottoProfitService;

    public LottoController(InputView inputView, OutputView outputView, LottoPurchaseService lottoPurchaseService, LottoWinningNumbersPickService lottoWinningNumbersPickService, LottoWinningRankingService lottoWinningRankingService, LottoProfitService lottoProfitService) {
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

        Lotto winningLotto = processLottoWinningNumbersPickTransaction();
        LottoWinningBonusNumber lottoWinningBonusNumber = processLottoWinningBonusNumberTransaction(winningLotto);

        final EnumMap<LottoWinningRanking, Integer> winningRankingCountMap =
                processWinningRankingCalculationTransaction(userLottos, winningLotto, lottoWinningBonusNumber);
        displayWinningRankingCount(winningRankingCountMap);

        final double profit = processUserProfitCalculationTransaction(userLottos, winningRankingCountMap);
        displayProfit(profit);
    }

    private void displayUserLottosInformation(Lottos userLottos) {
        LottosDto lottosDto = convertLottosDto(userLottos);

        displayUserLottoPurchaseCount(lottosDto);
        displayUserLottos(lottosDto);
    }

    private int getUserPurchaseAmount() {
        String userPurchaseAmount = inputView.inputPurchaseAmount();
        Validator.validateLottoAmountNumeric(userPurchaseAmount);
        return Integer.parseInt(userPurchaseAmount);
    }

    private LottosDto convertLottosDto(Lottos userLottos) {
        return new LottosDto(userLottos);
    }

    private void displayUserLottoPurchaseCount(LottosDto userLottosDto) {
        outputView.responsePurchaseCount(userLottosDto.getLottoGroup().size());
    }

    private void displayUserLottos(LottosDto userLottosDto) {
        outputView.responseUserNumbersSet(userLottosDto);
    }

    private Lotto processLottoWinningNumbersPickTransaction() {
        while (true) {
            try {
                outputView.requestWinningNumbers();
                return getLottoWinningNumbers();
            } catch (LottoException exception) {
                outputView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private Lotto getLottoWinningNumbers() throws LottoException {
        String input = inputView.inputWinningNumbers();
        Validator.validatedWinningNumbersFormat(input);
        return lottoWinningNumbersPickService.pickWinningNumbers(input);
    }

    private LottoWinningBonusNumber processLottoWinningBonusNumberTransaction(Lotto lotto) {
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

    private LottoWinningRakingCountMapDto convertWinningRankingCountMapDto(EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        return new LottoWinningRakingCountMapDto(winningRankingCountMap);
    }

    private EnumMap<LottoWinningRanking, Integer> processWinningRankingCalculationTransaction(Lottos userLottos, Lotto winningLotto, LottoWinningBonusNumber lottoWinningBonusNumber) {
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(winningLotto, lottoWinningBonusNumber);
        return lottoWinningRankingService.countWinningRankings(userLottos, lottoWinningNumbers);
    }

    private void displayWinningRankingCount(EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        outputView.responseWinningStatisticsHeader();
        outputView.responseWinningStatisticsBody(convertWinningRankingCountMapDto(winningRankingCountMap));
    }

    private void displayProfit(final double profit) {
        outputView.responseProfit(profit);
    }

    private double processUserProfitCalculationTransaction(Lottos userLottos, EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        return lottoProfitService.calculateProfit(winningRankingCountMap, userLottos.getSize());
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
}
