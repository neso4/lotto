package lotto;

public enum LottoNotice {
    FIRST_PLACE("6개 일치 (2,000,000,000원) - "),
    SECOND_PLACE("5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    THIRD_PLACE("5개 일치 (1,500,000원) - "),
    FORTH_PLACE("4개 일치 (50,000원) - "),
    FIFTH_PLACE("3개 일치 (5,000원) - ");

    private final String string;
    LottoNotice(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
