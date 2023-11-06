package lotto.controller;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.NumberCandidateString;

public class NumberCandidateStrings {

    private final List<NumberCandidateString> numberCandidateStrings;

    private NumberCandidateStrings(List<NumberCandidateString> numberCandidateStrings) {
        this.numberCandidateStrings = numberCandidateStrings;
    }

    public List<LottoNumber> toLottoNumberList(){
        return numberCandidateStrings.stream().map(NumberCandidateString::getNumber).map(LottoNumber::new).toList();
    }

    public static NumberCandidateStrings valueOf(String[] splitStrings){
        List<NumberCandidateString> numberCandidateStrings = Arrays.stream(splitStrings)
                .map(NumberCandidateString::new)
                .toList();
        return new NumberCandidateStrings(numberCandidateStrings);
    }
}
