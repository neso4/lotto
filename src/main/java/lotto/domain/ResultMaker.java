package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ResultMaker {
    private static final int LESS_THAN_THOUSAND = 6;
    private static final int DECIMAL_POINT = 2;
    private final LottoBundle bundle;
    private final SelectedLotto selectedLottos;
    private final Map<Rank, Integer> lottoResult = new HashMap<>();
    private long totalPrize = 0;

    public ResultMaker(LottoBundle bundle, SelectedLotto selectedLottos) {
        this.bundle = bundle;
        this.selectedLottos = selectedLottos;
        initializeResult();
    }

    public void updateResult() {
        List<Lotto> userLottos = bundle.getBundle();
        double corrects = 0;
        for (Lotto lotto : userLottos) {
            corrects = counting(lotto);
            if (corrects < Rank.FIFTH_PLACE.getCorrects()) {
                continue;
            }
            int oldCount = lottoResult.get(Rank.valueOfCorrects(corrects));
            lottoResult.put(Rank.valueOfCorrects(corrects), ++oldCount);
        }
    }

    public String calculateRateOfReturn() {
        int investment = bundle.showInvestment();
        calculateTotalPrize();
        double rateOfReturn = (double) totalPrize / investment * 100;
        String rawRateOfReturn = String.format("%.1f", rateOfReturn);
        return formattingNumber(rawRateOfReturn);
    }

    private String formattingNumber(String rawNumber) {
        if (rawNumber.length() < LESS_THAN_THOUSAND) {
            return rawNumber;
        }
        StringBuilder formattedNumber = new StringBuilder(rawNumber);
        for (int i = 0; i < rawNumber.length() - DECIMAL_POINT; i++) {
            if (i % 3 == 0 && i != 0) {
                formattedNumber.insert(rawNumber.length() - DECIMAL_POINT - i, ",");
            }
        }
        return formattedNumber.toString();
    }

    public Map<Rank, Integer> giveResult() {
        return Collections.unmodifiableMap(lottoResult);
    }

    private void calculateTotalPrize() {
        Iterator<Entry<Rank, Integer>> entry = lottoResult.entrySet().iterator();
        while (entry.hasNext()) {
            Map.Entry<Rank, Integer> element = entry.next();
            Rank rank = element.getKey();
            int count = element.getValue();
            totalPrize = rank.sumPrize(totalPrize, count);
        }
    }

    private double counting(Lotto lotto) {
        List<Integer> answerLottos = selectedLottos.getSelectedNumbers();
        int bonus = selectedLottos.getBonus();
        double corrects = lotto.compareWithSelected(answerLottos);
        if (corrects == Rank.THIRD_PLACE.getCorrects() && lotto.findBonus(bonus)) {
            corrects = Rank.SECOND_PLACE.getCorrects();
        }
        return corrects;
    }

    private void initializeResult() {
        for (int i = (int) Rank.FIFTH_PLACE.getCorrects(); i <= (int) Rank.FIRST_PLACE.getCorrects(); i++) {
            lottoResult.put(Rank.valueOfCorrects((double) i), 0);
        }
        lottoResult.put(Rank.valueOfCorrects(Rank.SECOND_PLACE.getCorrects()), 0);
    }
}
