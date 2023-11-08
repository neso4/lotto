package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.view.OutputView.PrintLottoNumbers;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        PlayerLottoAmount playerLottoAmount = getPlayerLottoAmount();
        int count = playerLottoAmount.calculateLottoCount();
        OutputView.printTicketCount(count);

        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(getLottoList());
        }

        List<Integer> selectLottoNumber = getSelectLottoNumbers();
        int bonus = getbonus(selectLottoNumber).getBonus();
        int[] lottoPrize = getLottoPrize(lottoList, selectLottoNumber, bonus);
        OutputView.result(lottoPrize, getRate(lottoPrize));

    }

    private static PlayerLottoAmount getPlayerLottoAmount() {
        while (true) {
            try {
                return new  PlayerLottoAmount(InputView.inputPlayPay());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Lotto getLottoList() {
        while (true) {
            try {
                return new Lotto(makeLotto());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Integer> makeLotto() throws IllegalArgumentException {
        List<Integer> numbers = getLottoRandom();
        PrintLottoNumbers(numbers);
        return numbers;
    }

    private static List<Integer> getLottoRandom() {
        List<Integer> numbers = new LottoNumbers().LottoNumbers();
        return numbers;
    }

    private static List<Integer> getSelectLottoNumbers() {
        while (true) {
            try {
                List<Integer> selectLottoNumber = getSelectLottoNumber(InputView.inputLottoNumber());
                getValidateLotto(selectLottoNumber);
                return selectLottoNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void getValidateLotto(List<Integer> selectLottoNumber) {
        new Lotto(selectLottoNumber);
    }

    private static List<Integer> getSelectLottoNumber(String InputLotto) {
        return Arrays.stream(InputLotto.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static Bonus getbonus(List<Integer> selectLottoNumber) {
        while (true) {
            try {
                String inputBonusNumber = InputView.getInputBonusNumber();
                 return getValidateBonus(selectLottoNumber, inputBonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    private static Bonus getValidateBonus(List<Integer> selectLottoNumber, String inputBonusNumber) {
        return new Bonus(inputBonusNumber, selectLottoNumber);
    }

    public static int[] getLottoPrize(List<Lotto> lottoNumbers, List<Integer> selectLottoNumber, int bonus) {
        int[] lottoPrize = new int[8];
        for (Lotto lotto : lottoNumbers) {
            long lottoCount = lotto.getNumbers().stream().filter(selectLottoNumber::contains).count();
            if (lottoCount == 5 && lotto.getNumbers().contains(bonus)) { //보너스
                lottoPrize[LottoPrice.FIVE_AND_BONUS.getLottoNum()]++;
                continue;
            }
            lottoPrize[(int) lottoCount]++;
        }
        return lottoPrize;
    }

    public static float getRate(int[] lottoPrize) {
        float totalPrice = 0;
        LottoPrice[] values = LottoPrice.values();
        for (int i = 3; i < values.length; i++) {
            totalPrice += lottoPrize[i] * values[i].getLottoPriceValue();
        }
        totalPrice /= PlayerLottoAmount.getAmount();
        totalPrice *= 100;
        return totalPrice;
    }


}
