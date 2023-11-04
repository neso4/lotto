package lotto.controller;

import lotto.controller.dto.input.BuyLottosDto;
import lotto.controller.dto.input.DrawLottosDto;
import lotto.controller.dto.output.LottosBuyingResult;
import lotto.controller.dto.output.LottosDrawingResult;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public final class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(
            final InputView inputView,
            final OutputView outputView,
            final LottoService lottoService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    /**
     * 로또 프로그램은 총 2회의 입출력으로 이루어져 있음. 첫 번째는 금액 입력 및 로또 구매 후, 구매한 로또 번호 출력 두 번째는 당첨 번호 및 보너스 번호 입력 및 당첨 결과 확인, 그리고 당첨 통계
     * 출력
     * <p>
     * 이를 2개의 메소드로 나누어 흐름을 보기 좋게 정리
     */
    public void run() {
        buyLottos();
        drawLottos();
    }

    /**
     * 첫 번째 입출력을 처리
     * <p>
     * 1. 로또 구매 금액 입력을 받는다.
     * <p>
     * 2. 로직 처리를 서비스에 위임한다
     * <p>
     * 3. 로또 구매 결과를 출력한다.
     */
    private void buyLottos() {
        final BuyLottosDto inputDto = inputView.inputBuyLottosDto();
        final LottosBuyingResult outputDto = lottoService.buyLottos(inputDto);
        outputView.printLottosBuyingResult(outputDto);
    }

    /**
     * 두 번째 입출력을 처리
     * <p>
     * 1. 로또 당첨 번호 및 보너스 번호를 입력 받는다.
     * <p>
     * -> 웹 페이지로 생각하면, 로그인 시에 이메일을 입력하고 다음 버튼을 누르면 페이지 이동이 아닌, 클라이언트 사이드 렌더링이 이루어 진 뒤, url 이동 없이 비밀번호 입력 창으로 살짝 전환되는
     * 느낌으로 하나의 페이지에서 2개의 입력을 모아서 받는다고 생각하여 1개의 컨트롤러에서 처리. 2. 로직 처리를 서비스에 위임한다 3. 로또 구매 결과를 출력한다.
     * <p>
     * 2. 저장된 모든 로또에 대한 추첨 로직 처리를 서비스에 위임한다.
     * <p>
     * 3. 로또 당첨 통계 결과를 출력한다.
     */
    private void drawLottos() {
        final DrawLottosDto inputDto = inputView.inputDrawLottosDto();
        final LottosDrawingResult outputDto = lottoService.drawAllLottos(inputDto);
        outputView.printLottosDrawingResult(outputDto);
    }
}
