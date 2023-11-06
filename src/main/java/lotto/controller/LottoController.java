package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.util.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.sort;
import static lotto.Lotto.LottoRank.*;
import static lotto.view.OutputView.printPrizeResult;

public class LottoController {

    private List<Lotto> lotto = new ArrayList<>();
    private Lotto winLotto;
    private int bonusNumber;

    public int first;
    public int second;
    public int third;
    public int fourth;
    public int fifth;

    public void startLotto() {
        OutputView.printGetPurchasePriceMessage();
        int purchasePrice = InputView.inputPurchasePrice();

        int lottoNum = getLottoNum(purchasePrice);
        OutputView.printLottoNumMessage(lottoNum);

        for(int i = 0; i < lottoNum ; i++) {
            List<Integer> lottoNumber = getLottoNumber();
            OutputView.printLottoNumber(lottoNumber);
            lotto.add(new Lotto(lottoNumber));
        }

        System.out.println();

        OutputView.printWinNumberMessage();
        getWinLottoNumber();

        OutputView.printBonusNumberMessage();
        bonusNumber = getBonusNumber();

        for (int i = 0; i < lottoNum; i++) {
            List<Integer> lottoNumbers = lotto.get(i).getNumbers();
            List<Integer> winNumbers = winLotto.getNumbers();
            int sameNumberCount = compareLotto(lottoNumbers, winNumbers);
            setRank(lotto.get(i), sameNumberCount);
        }

        /**
         * 로또 배열
         * 그 중에 한 로또를 뽑는 걸 반복하는 함수
         * ture인 숫자의 갯수를 카운트
         * 뽑은 로또의 숫자들을 하나씩 win로또 배열에 비교하는 함수 -> true 리턴
         */

        for (int i = 0; i < lottoNum; i++) {

            switch (lotto.get(i).getRank()) {
                case FIRST:
                    first += 1;
                    break;
                case SECOND:
                    second += 1;
                    break;
                case THIRD:
                    third += 1;
                    break;
                case FOURTH:
                    fourth += 1;
                    break;
                case FIFTH:
                    fifth += 1;
                    break;
            }

        }

//        Map<Lotto.LottoRank, Integer> rates = new HashMap<>();
//        rates.put(FIRST, first);
//        rates.put(SECOND, second);
//        rates.put(THIRD, third);
//        rates.put(FOURTH, fourth);
//        rates.put(FIFTH, fifth);

        List<Integer> rates = new ArrayList<>();
        rates.add(first);
        rates.add(second);
        rates.add(third);
        rates.add(fourth);
        rates.add(fifth);

        long rateOfProfit = getRateOfProfit(purchasePrice, rates);

        OutputView.printPrizeResult(rates);
        OutputView.printRateOfProfit(rateOfProfit);

    }

    public int getLottoNum(int purchasePrice) {
        int lottoNum = purchasePrice / 1000;
        return lottoNum;
    }

    public List<Integer> getLottoNumber() {
        List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        sort(lottoNumber);
        return lottoNumber;
    }

    public void getWinLottoNumber() {
        List<Integer> winNumber = InputView.inputWinNumber();

        try {
            winLotto = new Lotto(winNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("당첨 번호를 6개 입력해주세요.");
            getWinLottoNumber();
        }
    }

    public int getBonusNumber() {
        int bonusNumber = InputView.inputBonusNumber();

        try {
            Validator.bonusNumberNum(bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("1개만 입력하세요.");
            getLottoNumber();
        }

        return bonusNumber;
    }

    public int compareLotto(List<Integer> lottoNumber, List<Integer> winNumber) {

        int count = 0;

        for(int i = 0; i < lottoNumber.size(); i++) {
            if (winNumber.contains(lottoNumber.get(i))) {
                count += 1;
            }
        }

        return count;

    }

    public boolean compareBonusNumber(Lotto lotto, int bonusNumber) {

        List<Integer> lottoNumber = lotto.getNumbers();

        if (lottoNumber.contains(bonusNumber)) {
            return true;
        }

        return false;
    }

    private void setRank(Lotto lotto, int sameNumberCount) {
        switch (sameNumberCount) {
            case 6:
                lotto.setRank(FIRST);
                break;
            case 5:
                if (compareBonusNumber(lotto, bonusNumber)) {
                    lotto.setRank(SECOND);
                    break;
                }
                lotto.setRank(THIRD);
                break;
            case 4:
                lotto.setRank(THIRD);
                break;
            case 3:
                lotto.setRank(FOURTH);
                break;
            case 2:
                lotto.setRank(FIFTH);
                break;
            case 1:
                lotto.setRank(LOOSE);
                break;
            case 0:
                lotto.setRank(LOOSE);
                break;
        }
    }

    private long getRateOfProfit(int purchasePrice, List<Integer> rates) {
        return getTotalAmount(rates) / purchasePrice;
    }

    private int getTotalAmount(List<Integer> rates) {
        int total = 0;

        for (int i = 0; i < 5; i++) {
            total += getRatePrice(i + 1, rates.get(i));
        }

        return total;
    }

    private int getRatePrice(int rate, int rateNum) {

        switch (rate) {
            case 5:
                return 5000 * rateNum;
            case 4:
                return 50000 * rateNum;
            case 3:
                return 1500000 * rateNum;
            case 2:
                return 30000000 * rateNum;
            case 1:
                return 2000000000 * rateNum;
        }

        return 0;

    }
}
