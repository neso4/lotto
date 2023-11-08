package lotto.view;

import java.util.List;
import lotto.dto.LottoInfo;
import lotto.dto.LottoInfos;
import lotto.utils.StringUtil;

public class CreatedLottosView {
    private static final String HEADER_FORM = "\n%d개를 구매했습니다.\n";

    public static void viewCreatedLottos(LottoInfos lottoInfos) {
        String header = buildHeader(lottoInfos);
        String content = buildContent(lottoInfos);
        System.out.println(header + content);
    }

    private static String buildHeader(LottoInfos lottoInfos) {
        return String.format(HEADER_FORM, lottoInfos.getLottoInfos().size());
    }

    private static String buildContent(LottoInfos lottoInfos) {
        StringBuilder stringBuilder = new StringBuilder();
        lottoInfos.getLottoInfos().stream()
                .map(LottoInfo::getNumbers)
                .forEach((numbers) -> stringBuilder.append(toContent(numbers)).append("\n")
        );
        return stringBuilder.toString();
    }

    private static String toContent(List<Integer> numbers) {
        String content = StringUtil.divideNumsByCommas(numbers);
        return StringUtil.coverWithBrackets(content);
    }
}
