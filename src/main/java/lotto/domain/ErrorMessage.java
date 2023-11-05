package lotto.domain;

public enum ErrorMessage {

    SIZE("로또 번호는 6글자만 가능합니다."),
    DUPLICATE("중복되는 숫자가 존재합니다.");

    private static final String ERROR_MESSAGE = "[ERROR]";
    private static final String BLANK = " ";
    private final String description;

    ErrorMessage(String description) {
        this.description = description;
    }

    public static void getDescription(ErrorMessage errorMessage) {
        System.out.println(ERROR_MESSAGE + BLANK + errorMessage.description);
    }
}
