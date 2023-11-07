package model;

import java.util.ArrayList;
import java.util.List;
import util.RandomNumberGenerator;

public class LottoBundle {
    private static LottoBundle instance;
    private final List<Lotto> lottoBundle = new ArrayList<>();
    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    private LottoBundle() {
    }

    public static LottoBundle getInstance() {
        if (instance == null) {
            instance = new LottoBundle();
        }
        return instance;
    }

    public void addLotto(int lottoCount) {
        for (int i = 0; i < lottoCount; i++) {
            lottoBundle.add(new Lotto(randomNumberGenerator.createNewRandomNumber()));
        }
    }

    public List<Lotto> getLottoBundle() {
        return lottoBundle;
    }
}
