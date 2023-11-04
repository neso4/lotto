package lotto.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static Map<Integer, LottoNumber> lottoNumberCacheMap = new HashMap<>();
    private Integer number;

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(i -> lottoNumberCacheMap.put(i, new LottoNumber(i)));
    }

    private LottoNumber(Integer number) {
        validateNumberInRange(number);
        this.number = number;
    }

    public static LottoNumber generateLottoNumber(int number) {
        return Optional.ofNullable(lottoNumberCacheMap.get(number))
                .orElseThrow(IllegalArgumentException::new);
    }

    private void validateNumberInRange(int number) {
        validateNumberIsLessThanMinThreshold(number);
        validateNumberIsMoreThanMaxThreshold(number);
    }

    private void validateNumberIsLessThanMinThreshold(int number){
        if(number < MIN_LOTTO_NUMBER) throw new IllegalArgumentException();
    }

    private void validateNumberIsMoreThanMaxThreshold(int number){
        if(number > MAX_LOTTO_NUMBER) throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LottoNumber lottoNumber = (LottoNumber) o;
        return Objects.equals(number, lottoNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
