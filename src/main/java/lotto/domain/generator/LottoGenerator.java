package lotto.domain.generator;

import static lotto.domain.constant.DomainConstant.COMMA;
import static lotto.domain.constant.DomainConstant.MAX_RANGE;
import static lotto.domain.constant.DomainConstant.MIN_RANGE;
import static lotto.domain.constant.DomainConstant.NUMBERS_FORMAT_REGEX;
import static lotto.domain.constant.DomainConstant.ONE_LOTTO_PRICE;
import static lotto.domain.constant.DomainConstant.SIX;
import static lotto.domain.constant.DomainConstant.ZERO;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.DUPLICATED_BONUS_NUMBER;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.NEGATIVE_COUNT;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.NOT_EXIST_INPUT_ERROR;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.NOT_SIX_NUMBERS;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.NOT_THOUSAND_UNIT;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.OVER_RANGE;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.UNDER_THOUSAND_AMOUNT;
import static lotto.domain.generator.LottoGenerator.LottoMakerErrorMessage.WINNING_NUMBERS_INVALID_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.money.Money;

public record LottoGenerator(LottoNumberGenerator lottoNumberGenerator) {
    private static final long START_ORDER = 1L;

    public LottoGenerator() {
        this(new LottoNumberGenerator());
    }

    public WinningLotto createWinningLottoFromInput(String numbers, int bonusNumber) {
        validateNumbers(numbers);
        validateBonusNumber(numbers, bonusNumber);
        return new WinningLotto(lottoNumberGenerator.createByInput(numbers), bonusNumber);
    }

    private void validateNumbers(String numbers) {
        if (numbers.isBlank()) {
            throw new IllegalArgumentException(NOT_EXIST_INPUT_ERROR.getErrorMessage());
        }
        if (isInvalidNumbersFormat(numbers)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INVALID_FORMAT.getErrorMessage());
        }
        if (hasNotSixNumbers(numbers)) {
            throw new IllegalArgumentException(NOT_SIX_NUMBERS.getErrorMessage());
        }
    }

    private boolean isInvalidNumbersFormat(String numbers) {
        return !numbers.matches(NUMBERS_FORMAT_REGEX);
    }

    private boolean hasNotSixNumbers(String numbers) {
        return numbers.split(COMMA).length != SIX;
    }

    private void validateBonusNumber(String numbers, int bonusNumber) {
        if (bonusNumber < MIN_RANGE || bonusNumber > MAX_RANGE) {
            throw new IllegalArgumentException(OVER_RANGE.getErrorMessage());
        }
        if (isBonusNumberDuplicated(numbers, bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_NUMBER.getErrorMessage());
        }
    }

    private boolean isBonusNumberDuplicated(String numbers, int bonusNumber) {
        return Arrays.stream(numbers.split(COMMA))
                .mapToInt(Integer::parseInt)
                .anyMatch(number -> number == bonusNumber);
    }


    private Lotto createOneLotto() {
        List<Integer> lottoNumbers = lottoNumberGenerator.createLottoNumbers();
        return new Lotto(lottoNumbers);
    }

    public List<Lotto> createLottoByPrice(Money price) {
        validatePrice(price);

        long lottoCount = price.amount() / ONE_LOTTO_PRICE;
        return createLottoByCount(lottoCount);
    }

    private void validatePrice(Money price) {
        if (price.isLessThan(new Money(ONE_LOTTO_PRICE))) {
            throw new IllegalArgumentException(UNDER_THOUSAND_AMOUNT.getErrorMessage());
        }
        if (price.cantDividedBy(new Money(ONE_LOTTO_PRICE))) {
            throw new IllegalArgumentException(NOT_THOUSAND_UNIT.getErrorMessage());
        }
    }

    private List<Lotto> createLottoByCount(long lottoCount) {
        validateCount(lottoCount);
        return LongStream.rangeClosed(START_ORDER, lottoCount)
                .mapToObj(order -> createOneLotto())
                .toList();
    }

    private void validateCount(long lottoCount) {
        if (lottoCount < ZERO) {
            throw new IllegalArgumentException(NEGATIVE_COUNT.getErrorMessage());
        }
    }

    enum LottoMakerErrorMessage {
        NOT_EXIST_INPUT_ERROR("[ERROR] 입력이 존재하지 않습니다."),
        NOT_THOUSAND_UNIT("[ERROR] 금액은 1000원 단위로 입력해야 합니다."),
        UNDER_THOUSAND_AMOUNT("[ERROR] 최소 1000원의 금액을 입력해야 합니다."),
        WINNING_NUMBERS_INVALID_FORMAT("[ERROR] 입력 형식이 올바르지 않습니다."),
        NOT_SIX_NUMBERS("[ERROR] 입력 숫자는 6개여야 합니다."),
        DUPLICATED_BONUS_NUMBER("[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다."),
        OVER_RANGE("[ERROR] 숫자 범위는 1부터 45까지 가능합니다."),
        NEGATIVE_COUNT("[ERROR] 갯수는 음수일 수 없습니다.");

        private final String errorMessage;

        LottoMakerErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
