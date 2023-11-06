package lotto.controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.stream.Stream;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.PurchaseAmount;
import lotto.util.generator.LottoNumberGenerator;
import lotto.util.generator.NumberGenerator;
import lotto.util.mapper.DtoModelMapper;
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
        PurchaseAmount purchaseAmount = getValidPurchaseAmount();
        Lottos lottos = issueLottos(new LottoNumberGenerator(), purchaseAmount.calculateLottoCount());
        printLottosIssued(lottos);
    }

    private PurchaseAmount getValidPurchaseAmount() {
        while (true) {
            try {
                return DtoModelMapper.dtoToPurchaseAmount(inputView.readPurchaseAmount());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lottos issueLottos(NumberGenerator numberGenerator, int lottoCount) {
        return Stream.generate(() -> Lotto.issue(numberGenerator.generate()))
                .limit(lottoCount)
                .collect(collectingAndThen(toList(), Lottos::assemble));
    }

    private void printLottosIssued(Lottos lottos) {
        outputView.printLottosIssued(DtoModelMapper.LottosToDto(lottos));
    }
}
