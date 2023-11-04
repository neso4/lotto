package lotto.configuration;

import io.output.ConsoleWriter;
import lotto.controller.LottoController;
import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoStore;
import lotto.domain.lotto.RandomLottoGenerator;
import lotto.mapper.LottoMapper;
import lotto.mapper.WalletMapper;
import lotto.repository.LottoRepository;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public final class Configuration {

    private Configuration() {
    }

    public static LottoController createLottoController() {

        final InputView inputView = new InputView(new ConsoleWriter());
        final OutputView outputView = new OutputView(new ConsoleWriter());
        final LottoGenerator lottoGenerator = new RandomLottoGenerator();
        final LottoStore lottoStore = new LottoStore(lottoGenerator);
        final LottoRepository lottoRepository = new LottoRepository();
        final WalletMapper walletMapper = new WalletMapper();
        final LottoMapper lottoMapper = new LottoMapper();

        final LottoService lottoService = new LottoService(
                lottoStore,
                lottoRepository,
                walletMapper,
                lottoMapper
        );

        return new LottoController(inputView, outputView, lottoService);
    }

}
