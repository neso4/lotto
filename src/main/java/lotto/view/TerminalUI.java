package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lotto.constance.PrintConst;
import lotto.model.domain.Revenue;
import lotto.model.domain.lotto.Lotto;
import lotto.model.domain.result.LottoResult;
import lotto.view.io.Reader;
import lotto.view.io.Writer;

public class TerminalUI implements LottoGameUI {

    @Override
    public int getMoney() {
        Writer.printMessage(PrintConst.GUIDE_PURCHASE);
        return Reader.getMoney();
    }

    @Override
    public void printPurchasedLottos(List<Lotto> lottosDTO) {
        Writer.printUsingFormat(PrintConst.FORMAT_LOTTO_SIZE, lottosDTO.size());
        for (Lotto lotto : lottosDTO) {
            Writer.printUsingFormat(PrintConst.FORMAT_LOTTO_NUMBERS, lotto.getNumbers().toArray());
        }
    }

    @Override
    public List<Integer> getAnswerNumber() {
        Writer.printEmptyLine();
        Writer.printMessage(PrintConst.GUIDE_LOTTO_NUMBERS);
        return Reader.getAnswerNumbers();
    }

    @Override
    public Integer getBonusNumber() {
        Writer.printEmptyLine();
        Writer.printMessage(PrintConst.GUIDE_BONUS_NUMBERS);
        return Reader.getBonusNumber();
    }

    @Override
    public void printResult(List<Entry<LottoResult, Integer>> results) {
        Writer.printMessage(PrintConst.TITLE_RESULTS);
        for (Map.Entry<LottoResult, Integer> result : results) {
            LottoResult lottoResult = result.getKey();
            int count = result.getValue();
            Writer.printUsingFormat(PrintConst.FORMAT_RESULT, lottoResult, count);
        }
    }

    @Override
    public void printRevenue(Revenue revenue) {
        Writer.printUsingFormat(PrintConst.FORMAT_REVENUE,
                new DecimalFormat(PrintConst.DECIMAL_FORMAT_REVENUE)
                        .format(revenue.getRevenue()));
    }


}
