package lotto.util.parser;

import java.util.List;

public abstract class InputParser {

    public abstract List<Integer> parse(String input);

    abstract void validate(String input);

    protected String removeSpaces(String input) {
        return input.replaceAll(" ", "");
    }
}
