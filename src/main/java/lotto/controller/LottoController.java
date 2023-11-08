package lotto.controller;

import lotto.domain.StatisticsMachine;
import lotto.dto.request.AnswerNumberRequestDto;
import lotto.dto.response.LottosResponseDto;
import lotto.service.LottoService;
import lotto.util.BaseResponse;

public class LottoController {
    private final LottoService lottoService = new LottoService();
    public BaseResponse<LottosResponseDto> buyLotto(int money) {
        LottosResponseDto lottosResponseDto = lottoService.buyLotto(money);
        return new BaseResponse<>(lottosResponseDto.lottos()
                .size() + "개를 구매했습니다.", lottosResponseDto);
    }

    public BaseResponse<StatisticsMachine> getStatistics(AnswerNumberRequestDto answerNumberRequestDto) {

        return new BaseResponse<>("당첨 통계", lottoService.result(answerNumberRequestDto));

    }
}
