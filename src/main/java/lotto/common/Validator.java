package lotto.common;


public class Validator {
    public void verifyNullAndBlank(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("공백혹은 빈 문자열은 허용하지 않습니다.");
        }

        s.chars().filter(Character::isWhitespace)
                .findAny().ifPresent((character) -> {
                    throw new IllegalArgumentException("공백이 포함될 수 없습니다.");
                });
    }

    public void verifyInRangeClosed(int start, int end, int value) {
        if (value < start || value > end) {
            throw new IllegalArgumentException("범위안에 속하지 않습니다.");
        }
    }

    public void verifyDivisible(Integer target, Integer unit) {
        if (target % unit != 0) {
            throw new IllegalArgumentException("범위안에 속하지 않습니다.");
        }
    }

    public void verifyNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("숫자형식이 아닙니다.");
        }
    }
}
