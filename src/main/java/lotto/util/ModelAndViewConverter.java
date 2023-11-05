package lotto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.dto.LottoBundleDto;
import lotto.domain.dto.LottoResultsDto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.player.Profit;

public class ModelAndViewConverter {
    public static final ModelAndViewConverter MODEL_AND_VIEW_CONVERTER = new ModelAndViewConverter();
    private static final String REMOVE_FROM_METHOD_NAME = "get";
    private static final String BLANK = "";
    private final Map<String, Object> modelAndView;

    public ModelAndViewConverter() {
        this.modelAndView = new HashMap<>();
    }

    public void addComponent(Object o) {
        String cast = o.getClass().getSimpleName();
        modelAndView.put(cast, o);
    }

    public int getNumberOfLottoBundle() {
        LottoBundleDto lottoBundleDto = getLottoBundleDto();
        return lottoBundleDto.getNumberOfLottoBundle();
    }

    public List<String> getLottoMessages() {
        LottoBundleDto lottoBundleDto = getLottoBundleDto();
        return convertLottoBundleDataToLottoDataMessages(lottoBundleDto);
    }

    private LottoBundleDto getLottoBundleDto() {
        String modelName = new Object() {
        }.getClass().getEnclosingMethod().getName().replace(REMOVE_FROM_METHOD_NAME, BLANK);

        return (LottoBundleDto) modelAndView.get(modelName);
    }

    private List<String> convertLottoBundleDataToLottoDataMessages(LottoBundleDto lottoBundleDto) {
        return lottoBundleDto.getLottoBundleData().stream()
                .map(Lotto::toString)
                .collect(Collectors.toList());
    }

    public Map<LottoResult, Integer> getLottoResultsData() {
        LottoResultsDto lottoResultsDto = getLottoResultsDto();
        return lottoResultsDto.getLottoResultsData();
    }

    private LottoResultsDto getLottoResultsDto() {
        String modelName = new Object() {
        }.getClass().getEnclosingMethod().getName().replace(REMOVE_FROM_METHOD_NAME, BLANK);

        return (LottoResultsDto) modelAndView.get(modelName);
    }

    public double getProfitMessage() {
        Profit profit = getProfit();
        return profit.getProfit();
    }

    private Profit getProfit() {
        String modelName = new Object() {
        }.getClass().getEnclosingMethod().getName().replace(REMOVE_FROM_METHOD_NAME, BLANK);

        return (Profit) modelAndView.get(modelName);
    }

    public String getErrorMessage() {
        IllegalArgumentException e = getIllegalArgumentException();
        return e.getMessage();
    }

    private IllegalArgumentException getIllegalArgumentException() {
        String modelName = new Object() {
        }.getClass().getEnclosingMethod().getName().replace(REMOVE_FROM_METHOD_NAME, BLANK);

        return (IllegalArgumentException) modelAndView.get(modelName);
    }
}
