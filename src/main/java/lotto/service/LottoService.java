package lotto.service;

import lotto.model.LottoRank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    private static final int VALID_LOTTO_SIZE = 6;
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;

    private static final String COMMON_ERROR_MESSAGE = "[ERROR] ";
    private static final String LOTTO_NUMBER_MESSAGE = COMMON_ERROR_MESSAGE + "당첨 번호는 ";
    private static final String BONUS_NUMBER_MESSAGE = COMMON_ERROR_MESSAGE + "보너스 번호는 ";
    private static final String BUY_AMOUNT_MESSAGE = COMMON_ERROR_MESSAGE + "구입 가격은 ";

    private static final String INVALID_BUY_AMOUNT = BUY_AMOUNT_MESSAGE + "1000원 단위로 입력해야 합니다.";

    private static final String INVALID_LOTTO_SIZE_MESSAGE = "6자리의 숫자여야 합니다.";
    private static final String DUPLICATE_MESSAGE = "서로 중복 될 수 없습니다.";
    private static final String ONLY_NUMBER_MESSAGE = "숫자로만 이루어져야 합니다.";
    private static final String INVALID_NUMBER_RANGE_MESSAGE = "1~45 사이의 숫자여야 합니다.";

    private static final String BONUS_DUPLICATE_MESSAGE = BONUS_NUMBER_MESSAGE + "당첨 번호와 중복 될 수 없습니다.";

    public Map<String, String> setUserLottoNumbersAndBonusNumber(
            String inputUserLottoNumbers, String inputUserBonusNumber
    ) {
        Map<String, String> userLottoNumbersAndBonusNumber = new HashMap<>();
        userLottoNumbersAndBonusNumber.put("userLottoNumbers", inputUserLottoNumbers);
        userLottoNumbersAndBonusNumber.put("userBonusNumber", inputUserBonusNumber);

        return userLottoNumbersAndBonusNumber;
    }

    public Map<LottoRank, Integer> getLottoWinningResult(
            Map<String, String> userLottoNumbersAndBonusNumber, List<List<Integer>> lottoTickets
    ) {
        Map<LottoRank, Integer> lottoWinningResult = getLottoWinningResultInit();

        List<String> userLottoNumbers = Arrays.stream(
                userLottoNumbersAndBonusNumber.get("userLottoNumbers").split(",")
        ).toList();

        for (List<Integer> lottoTicket : lottoTickets) {
            boolean isMatchBonusNumber = isMatchBonusNumber(lottoTicket, userLottoNumbersAndBonusNumber);
            int matchNumberCount = getMatchNumberCount(lottoTicket, userLottoNumbers);
            LottoRank lottoRank = getLottoRank(matchNumberCount, isMatchBonusNumber);

            lottoWinningResult.put(lottoRank, lottoWinningResult.get(lottoRank) + 1);
        }
        return lottoWinningResult;
    }

    private Map<LottoRank, Integer> getLottoWinningResultInit() {
        Map<LottoRank, Integer> lottoWinningResult = new HashMap<>();
        for (LottoRank value : LottoRank.values()) {
            lottoWinningResult.put(value, 0);
        }
        return lottoWinningResult;
    }

    private int getMatchNumberCount(List<Integer> lottoTicket, List<String> userLottoNumbers) {
        int matchNumberCount = 0;
        for (String tempUserLottoNumber : userLottoNumbers) {
            int userLottoNumber = Integer.parseInt(tempUserLottoNumber);
            if (lottoTicket.contains(userLottoNumber)) {
                matchNumberCount++;
            }
        }
        return matchNumberCount;
    }

    private LottoRank getLottoRank(int matchCount, boolean isMatchBonus) {
        for (LottoRank lottoRank : LottoRank.values()) {
            if (matchCount == 5 && lottoRank.getMatchCount() == matchCount && isMatchBonus) {
                return LottoRank.SECOND;
            } else if (lottoRank.getMatchCount() == matchCount) {
                return lottoRank;
            }
        }
        return LottoRank.NONE;
    }

    private boolean isMatchBonusNumber(List<Integer> lottoTicket, Map<String, String> userLottoNumbersAndBonusNumber) {
        return lottoTicket.contains(
                Integer.parseInt(userLottoNumbersAndBonusNumber.get("userBonusNumber"))
        );
    }

    public void buyLottoAmountValidate(String inputBuyLottoAmount) {
        if (!isInteger(inputBuyLottoAmount)) {
            throw new IllegalArgumentException(BUY_AMOUNT_MESSAGE + ONLY_NUMBER_MESSAGE);
        }

        int buyLottoAmount = Integer.parseInt(inputBuyLottoAmount);
        if (buyLottoAmount % 1000 != 0 || buyLottoAmount == 0) {
            throw new IllegalArgumentException(INVALID_BUY_AMOUNT);
        }
    }

    public void userLottoNumbersValidate(String userLottoNumbers) {
        String[] splitUserLottoNumbers = userLottoNumbers.split(",");
        List<String> tempUserLottoNumbers = Arrays.asList(splitUserLottoNumbers);
        // 로또가 6자리인지 확인한다.
        if (tempUserLottoNumbers.size() != VALID_LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MESSAGE + INVALID_LOTTO_SIZE_MESSAGE);
        }

        for (String tempUserLottoNumber : tempUserLottoNumbers) {
            // 각 로또 번호가 숫자인지 체크한다.
            if (!isInteger(tempUserLottoNumber)) {
                throw new IllegalArgumentException(LOTTO_NUMBER_MESSAGE + ONLY_NUMBER_MESSAGE);
            }

            // 로또 번호가 1~45 사이의 값인지 체크한다.
            int pickUserLottoNumber = Integer.parseInt(tempUserLottoNumber);
            if (pickUserLottoNumber < LOTTO_MINIMUM_NUMBER || pickUserLottoNumber > LOTTO_MAXIMUM_NUMBER) {
                throw new IllegalArgumentException(LOTTO_NUMBER_MESSAGE + INVALID_NUMBER_RANGE_MESSAGE);
            }
        }

        // 로또 번호에 중복된 숫자가 있는지 체크
        if(tempUserLottoNumbers.size() != tempUserLottoNumbers.stream().distinct().count()){
            throw new IllegalArgumentException(LOTTO_NUMBER_MESSAGE + DUPLICATE_MESSAGE);
        }
    }

    public void userBonusNumberValidate(String inputUserLottoNumbers, String inputUserBonusNumber) {
        // 숫자인지 체크
        if (!isInteger(inputUserBonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_MESSAGE + ONLY_NUMBER_MESSAGE);
        }

        // 1~45 범위에 있는지 체크
        int bonusNumber = Integer.parseInt(inputUserBonusNumber);
        if (bonusNumber < LOTTO_MINIMUM_NUMBER || bonusNumber > LOTTO_MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(BONUS_NUMBER_MESSAGE + INVALID_NUMBER_RANGE_MESSAGE);
        }

        // 사용자가 입력한 당첨 번호와 중복되는지 체크
        String[] tempUserLottoNumbers = inputUserLottoNumbers.split(",");
        List<String> userLottoNumbers = Arrays.asList(tempUserLottoNumbers);
        if (userLottoNumbers.contains(inputUserBonusNumber)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE_MESSAGE);
        }
    }

    public boolean isInteger(String strValue) {
        try {
            Integer.parseInt(strValue);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public double getLottoRateOfReturn(String buyLottoAmount, Map<LottoRank, Integer> lottoWinningResult) {
        long inputBuyLottoAmount = Long.parseLong(buyLottoAmount);
        long totalPrize = 0L;
        for (LottoRank lottoRank : lottoWinningResult.keySet()) {
            Integer winningCount = lottoWinningResult.get(lottoRank);
            String lottoRankPrize = lottoRank.getPrize();
            String prize = lottoRankPrize.replaceAll(",", "");

            totalPrize += Long.parseLong(prize) * winningCount;
        }

        // 수익률 = 총상금 / 구매금액 * 100
        double rateOfReturn = (double) totalPrize / inputBuyLottoAmount * 100; // 수익률
        return Math.round(rateOfReturn * 10) / 10.0;
    }
}
