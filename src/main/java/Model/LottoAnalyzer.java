package Model;

import java.util.ArrayList;
import java.util.List;

public class LottoAnalyzer {
    Lotto prizeNumber;
    private int bonusNumber;
    private float yieldNum;
    private List<Integer> numberOfWins;
    private final List<Integer> yieldPrice = List.of(5000, 50000, 1500000, 30000000, 2000000000);

    public LottoAnalyzer(List<String> prizeNumbers, int bonusNumber) {
        prizeNumber = new Lotto(prizeNumber(prizeNumbers));
        this.bonusNumber = bonusNumber;
        this.yieldNum = 0;
        numberOfWins = List.of(0,0,0,0,0);
    }

    public List<Integer> getPrizeNumber() {
        return prizeNumber.getNumbers();
    }
    public int getBonusNumber() {
        return bonusNumber;
    }
    public float getYieldNum() {
        return yieldNum;
    }
    public List<Integer> getNumberOfWins() {
        return numberOfWins;
    }

    private List<Integer> prizeNumber(List<String> prizeNumbers) {
        List<Integer> prizeNumber = new ArrayList<>();
        for (String number : prizeNumbers) {
            int num = Integer.parseInt(number);
            prizeNumber.add(num);
        }
        return prizeNumber;
    }

    public void compareLotto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            int equalNum = compare(lotto.getNumbers());
            saveCompare(equalNum, lotto.getNumbers());
        }
    }

    public int compare(List<Integer> lottoNumber) {
        int equalNum = 0;
        for (Integer integer : lottoNumber) {
            equalNum = compareNumbers(integer, equalNum);
        }
        return equalNum;
    }

    private int compareNumbers(int lottoNumber, int equalNum) {
        for (Integer integer : prizeNumber.getNumbers()) {
            if (integer == lottoNumber) {
                equalNum += 1;
                return equalNum;
            }
        }
        return equalNum;
    }

    private void saveCompare(int equalNum, List<Integer> lottoNumber) {
        if (equalNum == 3) {
            addNumberOfWins(0);
        }
        if (equalNum == 4) {
            addNumberOfWins(1);
        }
        if (equalNum == 5) {
            if (compareBonusNumber(lottoNumber)) {
                addNumberOfWins(2);
            }
            if (!compareBonusNumber(lottoNumber)) {
                addNumberOfWins(3);
            }
        }
        if (equalNum == 6) {
            addNumberOfWins(4);
        }
    }

    private void addNumberOfWins(int i) {
        int num = numberOfWins.get(i) + 1;
        numberOfWins.set(i, num);
    }

    private boolean compareBonusNumber(List<Integer> lottoNumber) {
        for (Integer num : lottoNumber) {
            if (bonusNumber == num) {
                return false;
            }
        }
        return true;
    }

    public void yieldCalculation(int price) {
        for (int i = 0; i < numberOfWins.size(); i++) {
            yieldNum += (numberOfWins.get(i) * (float)yieldPrice.get(i));
        }
        yieldNum = ((yieldNum - price) / price) * 100;
    }
}
