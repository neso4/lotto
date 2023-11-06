package lotto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGameService {

    private static final int LOTTO_PRICE = 1000;
    private static final int INITIAL_COUNT = 0;
    private static final int ZERO_VALUE = 0;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String CONTAIN_NON_DIGIT_EXCEPTION = "숫자만 입력할 수 있습니다.";
    private static final String NOT_DIVIDING_BY_LOTTO_PRICE_EXCEPTION = "금액은 1,000원 단위로 입력할 수 있습니다.";
    private static final String NOT_SIX_DIGITS_SEPARATED_BY_COMMAS_EXCEPTION = "쉼표(,)로 구분된 숫자 6개를 입력하세요.";
    private static final String DUPLICATE_DIGIT_EXCEPTION = "중복된 숫자는 입력할 수 없습니다.";
    private static final String WRONG_RANGE_EXCEPTION = "1에서 45사이의 숫자만 입력할 수 있습니다.";
    private static final String NON_DIGIT_REGEX = "\\D+";
    private static final String WINNING_NUMBERS_REGEX = "^\\d+(,\\d+){5}$";
    private final List<List<Integer>> purchasedLottoNumbers = new ArrayList<>();
    private final EnumMap<LottoRank, Integer> lottoRakingMap = new EnumMap<>(LottoRank.class);

    public LottoGameService() {
        initLottoRakingMap();
    }

    private void initLottoRakingMap() {
        for (LottoRank rank : LottoRank.values()) {
            lottoRakingMap.put(rank, INITIAL_COUNT);
        }
    }

    public void generateLottoNumbers() {
        Lotto lotto = new Lotto(LottoNumbersGenerator.generateLottoNumbers());
        purchasedLottoNumbers.add(lotto.getNumbers());
    }

    public void validatePurchaseAmount(String lottoPurchaseAmount) {
        if (isStringContainNonDigit(lottoPurchaseAmount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + CONTAIN_NON_DIGIT_EXCEPTION);
        }
        if (isMultipleOfLottoPrice(lottoPurchaseAmount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + NOT_DIVIDING_BY_LOTTO_PRICE_EXCEPTION);
        }
    }

    private boolean isStringContainNonDigit(String lottoPurchaseAmount) {
        return Pattern.compile(NON_DIGIT_REGEX).matcher(lottoPurchaseAmount).find();
    }

    private boolean isMultipleOfLottoPrice(String lottoPurchaseAmount) {
        return Integer.parseInt(lottoPurchaseAmount) % LOTTO_PRICE != ZERO_VALUE;
    }

    public int getLottoTicketCount(String lottoPurchaseAmount) {
        return Integer.parseInt(lottoPurchaseAmount) / LOTTO_PRICE;
    }

    public List<List<Integer>> getPurchasedLottoNumbers() {
        return purchasedLottoNumbers;
    }

    public List<Integer> convertWinningNumbersToCollection(String lottoWinningNumbers) {
        return Stream.of(lottoWinningNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void validateWinningNumbers(String lottoWinningNumbers) {
        if (isLottoNumbersFormat(lottoWinningNumbers)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + NOT_SIX_DIGITS_SEPARATED_BY_COMMAS_EXCEPTION);
        }
    }

    private boolean isLottoNumbersFormat(String lottoWinningNumbers) {
        return !Pattern.compile(WINNING_NUMBERS_REGEX).matcher(lottoWinningNumbers).matches();
    }

    public void validateWinningNumbersList(List<Integer> winningNumbersList) {
        if (isLottoNumbersWrongRange(winningNumbersList)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + WRONG_RANGE_EXCEPTION);
        }
        if (isContainDuplicateDigits(winningNumbersList)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + DUPLICATE_DIGIT_EXCEPTION);
        }
    }

    private boolean isLottoNumbersWrongRange(List<Integer> winningNumbersList) {
        return winningNumbersList.stream()
                .anyMatch(number -> number < 1 || number > 45);
    }

    private boolean isContainDuplicateDigits(List<Integer> winningNumbersList) {
        return winningNumbersList.stream()
                .distinct()
                .count() != LOTTO_NUMBER_COUNT;
    }

    public void validateBonusNumber(String bonusNumber) {
        if (isBonusNumberNonDigit(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + CONTAIN_NON_DIGIT_EXCEPTION);
        }
        if (isBonusNumberWrongRange(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + WRONG_RANGE_EXCEPTION);
        }
    }

    private static boolean isBonusNumberWrongRange(String bonusNumber) {
        return Integer.parseInt(bonusNumber) < 1 || Integer.parseInt(bonusNumber) > 45;
    }

    private static boolean isBonusNumberNonDigit(String bonusNumber) {
        return Pattern.compile(NON_DIGIT_REGEX).matcher(bonusNumber).matches();
    }

    public long getCollectNumberCount(List<Integer> purchasedLotto, List<Integer> winningLotto) {
        return winningLotto.stream()
                .filter(purchasedLotto::contains)
                .count();
    }

    public boolean isContainBonusNumber(List<Integer> purchasedLotto, int bonusNumber) {
        return purchasedLotto.contains(bonusNumber);
    }

    public LottoRank determineWinningRank(List<Integer> purchasedLotto,
                                          List<Integer> winningLotto,
                                          int bonusNumber) {
        return LottoRank.determineRank(getCollectNumberCount(purchasedLotto, winningLotto),
                isContainBonusNumber(purchasedLotto, bonusNumber));
    }

    public void updateWinningCount(LottoRank lottoRank) {
        int currentCount = lottoRakingMap.getOrDefault(lottoRank, 0);
        lottoRakingMap.put(lottoRank, currentCount + 1);
    }

    public double calculateProfitRate(String purchaseAmount, EnumMap<LottoRank, Integer> lottoRakingMap) {
        return (double) lottoRakingMap.keySet().stream()
                .filter(rank -> rank != LottoRank.LAST_PLACE)
                .mapToInt(rank ->
                        rank.getPrizeMoney() * lottoRakingMap.get(rank))
                .sum() / Integer.parseInt(purchaseAmount) * 100;
    }

    public EnumMap<LottoRank, Integer> getLottoRakingMap() {
        return lottoRakingMap;
    }
}
