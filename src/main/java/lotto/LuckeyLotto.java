package lotto;

import java.util.List;

public class LuckeyLotto {
    private enum ErrorMessage {
        WRONG_TYPE("[ERROR] 보너스 로또 번호의 타입은 숫자이어야 합니다."),
        WRONG_SAME("[ERROR] 보너스 로또 번호는 중복없는 숫자이어야 합니다."),
        WRONG_RANGE("[ERROR] 보너스 로또 번호는 1부터 45사이의 숫자여야 합니다.");

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private static final int LOTTO_NUMBER_RANGE_START = 1;
    private static final int LOTTO_NUMBER_RANGE_END = 45;
    private int luckeyBonusNumber;
    private List<Integer> luckeyNumbers;

    public LuckeyLotto(List<Integer> luckeyNumbers, String inputBonusNumber) {
        validate(luckeyNumbers, inputBonusNumber);
        int luckeyBonusNumber = Integer.valueOf(inputBonusNumber);
        luckeyNumbers.add(luckeyBonusNumber);
        this.luckeyNumbers = luckeyNumbers;
        this.luckeyBonusNumber = luckeyBonusNumber;
    }

    private void validate(List<Integer> luckeyNumbers, String inputBonusNumber) {
        if (!inputBonusNumber.matches("\\d+")) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_TYPE.getMessage());
        } else if (luckeyNumbers.contains(Integer.valueOf(inputBonusNumber))) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_SAME.getMessage());
        } else if (!isWithValidRange(Integer.valueOf(inputBonusNumber))) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_RANGE.getMessage());
        }
    }

    private boolean isWithValidRange(int inputBonusNumber) {
        return inputBonusNumber >= LOTTO_NUMBER_RANGE_START
                && inputBonusNumber <= LOTTO_NUMBER_RANGE_END;
    }

    public int getLuckeyBonusNumber() {
        return luckeyBonusNumber;
    }

    public List<Integer> getluckeyNumbers() {
        return luckeyNumbers;
    }
}
