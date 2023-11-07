package lotto.config;

import lotto.CommaParser;
import lotto.ConsoleReceiver;
import lotto.ConsoleWriter;
import lotto.InputReceiver;
import lotto.InputView;
import lotto.OutputWriter;
import lotto.Parser;
import lotto.utils.NumberGenerator;
import lotto.utils.RandomNumberGenerator;

public class AppConfig {

    public InputView inputView() {
        return new InputView(inputReceiver());
    }

    public Parser parser() {
        return new CommaParser();
    }

    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator(
                LottoGameRule.LOTTO_MIN_VALUE.value(),
                LottoGameRule.LOTTO_MAX_VALUE.value());
    }

    public InputReceiver inputReceiver() {
        return new ConsoleReceiver();
    }

    public OutputWriter outputWriter() {
        return new ConsoleWriter();
    }

}
