package lotto.controller.controllers;

import lotto.controller.ErrorHandler;
import lotto.dto.BuyLottoDto;
import lotto.dto.InputDto;
import lotto.dto.OutputDto;
import lotto.service.LottoBuyService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.ParameterConfig;

import java.util.Map;

public final class LottoBuyController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoBuyService service;

    public LottoBuyController(InputView inputView, OutputView outputView, LottoBuyService service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.service = service;
    }

    @Override
    public void process(Map<String, InputDto> inputs,
                        Map<String, OutputDto> outputs) {

        ErrorHandler.tryUntilNoError(() -> runViewByLottoPrice(inputs, outputs));
    }

    private void runViewByLottoPrice(Map<String, InputDto> inputs,
                                     Map<String, OutputDto> outputs) {
        Long price = getBuyLottoPrice(inputs, outputs);
        viewBuyLottoNumbers(outputs, price);
    }

    private void viewBuyLottoNumbers(Map<String, OutputDto> outputs, Long price) {
        BuyLottoDto.Output buyLottoDto = service.getBuyLottoDto(price);
        outputs.put(ParameterConfig.BUY_PRICE, buyLottoDto);
        outputView.view(outputs);
    }

    private Long getBuyLottoPrice(Map<String, InputDto> inputs,
                                  Map<String, OutputDto> outputs) {

        inputs.put(ParameterConfig.BUY_PRICE, new BuyLottoDto.Input());
        outputView.view(outputs);
        inputView.read(inputs);
        return ((BuyLottoDto.Input) inputs.get(ParameterConfig.BUY_PRICE)).getBuyPrice();
    }
}
