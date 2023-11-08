package lotto.constant;

public enum LottoCount {
    //    3개 일치 (5,000원) - 1개
//4개 일치 (50,000원) - 0개
//5개 일치 (1,500,000원) - 0개
//5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
//6개 일치 (2,000,000,000원) - 0개
    FIFTH(3,"3개 일치 (5,000원) - %d개"),
    FOURTH(4, "4개 일치 (50,000원) - %d개"),
    THIRD(5, "5개 일치 (1,500,000원) - %d개"),
    SECOND(7, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개"),
    FIRST(6, "6개 일치 (2,000,000,000원) - %d개");


    private final int grade;
    private final String message;
    LottoCount(int grade, String message) {
        this.grade = grade;
        this.message = message;
    }

    public int getGrade() {
        return grade;
    }
    public String getMessage(int count) {
        return String.format(message, count);
    }
}
