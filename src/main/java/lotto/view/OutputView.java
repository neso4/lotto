package lotto.view;


import java.util.List;
import lotto.controller.dto.LottoResponseDto;
import lotto.controller.dto.LottoResponseDtos;

public class OutputView {

    public static final String INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String PURCHASED_LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다.\n";

    public static void printInputPrice() {
        System.out.println(INPUT_PURCHASE_MESSAGE);
    }

    public static void printPurchaseLotto(int lottoCount) {
        System.out.printf(PURCHASED_LOTTO_COUNT_MESSAGE, lottoCount);
    }

    public static void printLottosValue(LottoResponseDtos lottoResponseDtos) {
        List<LottoResponseDto> dtos = lottoResponseDtos.toLottoResponseDto();
        for (LottoResponseDto dto: dtos) {
            printLottoValue(dto);
        }
    }

    private static void printLottoValue(LottoResponseDto responseLottoDto) {
        List<Integer> lottoNumbers = responseLottoDto.getLottoNumber();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < lottoNumbers.size(); i++) {
            stringBuilder.append(lottoNumbers.get(i));
            if (i < lottoNumbers.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }
}
