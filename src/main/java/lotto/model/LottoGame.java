package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.Lotto;

public class LottoGame implements Game {
    public static final int lottoNumbers = 6;
    private static final int lottoPirce = 1000;
    private int lottoPurchaseAmount;
    private Lotto winningLotto;
    private int bonusNumber;

    private final List<Lotto> purchasedLottos = new ArrayList<>();

    private final int[] winningLottos = new int[5];

    private double returnOnInvestment;

    public void checkWinningLottos() {
        int profit = 0;

        for (Lotto lotto : purchasedLottos) {
            Winning winning;
            winning = lotto.checkWinning(winningLotto, bonusNumber);
            if (winning != Winning.LOSE) {
                winningLottos[winning.getValue()] += 1;
                profit += winning.getWinningAmount();
            }
        }

        returnOnInvestment = Math.round(((double) profit / (lottoPurchaseAmount * lottoPirce)) * 100 * 10) / 10.0D;
    }

    @Override
    public void play() {
        checkWinningLottos();
    }

    @Override
    public boolean continues() {
        return false;
    }

    public void createLottoTickets() {
        for (int i = 0; i < lottoPurchaseAmount; i++) {
            List<Integer> purchasedLottoNumbers;
            purchasedLottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            purchasedLottoNumbers = new ArrayList<>(purchasedLottoNumbers);
            Collections.sort(purchasedLottoNumbers);
            purchasedLottos.add(new Lotto(purchasedLottoNumbers));
        }
    }

    public int validateLottoPurchaseAmount(String input) {
        int lottoPurchasePrice;
        lottoPurchasePrice = validateNumber(input);
        if (lottoPurchasePrice % lottoPirce != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 " + lottoPirce + "원 단위입니다.");
        }

        return lottoPurchasePrice / lottoPirce;
    }


    private Lotto validateWinningNumbers(String winningNumbers) {
        List<Integer> numberArray;
        String[] winningNumbersArray;
        numberArray = new ArrayList<>();
        winningNumbersArray = winningNumbers.split(",");
        for (String number : winningNumbersArray) {
            numberArray.add(validateNumber(number));
        }

        return new Lotto(numberArray);
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력은 숫자여야합니다.", e);
        }
    }

    public int getLottoPurchaseAmount() {
        return lottoPurchaseAmount;
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }

    public int[] getWinningLottos() {
        return winningLottos;
    }

    public double getReturnOnInvestment() {
        return returnOnInvestment;
    }

    public void setLottoPurchaseAmount(int lottoPurchaseAmount) {
        this.lottoPurchaseAmount = lottoPurchaseAmount;
    }

    public void setWinningLotto(List<Integer> winningNumbers) {
        this.winningLotto = new Lotto(winningNumbers);
    }

    public void setBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}