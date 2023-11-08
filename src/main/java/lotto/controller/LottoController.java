package lotto.controller;

import static lotto.constant.Rank.SAME_NUMBER_3;
import static lotto.constant.Rank.SAME_NUMBER_4;
import static lotto.constant.Rank.SAME_NUMBER_5_BONUSE_O;
import static lotto.constant.Rank.SAME_NUMBER_5_BONUSE_X;
import static lotto.constant.Rank.SAME_NUMBER_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottosList;
import lotto.service.LottoService;
import lotto.view.ConsolePrint;

public class LottoController {
    private LottosList lottoBox;
    private Lotto winningNumber;
    private BonusNumber bonus;

    public LottoController() {

    }

    public void start() {
        LottoService service = new LottoService();

        readPrice(service);
        printLottos();
        readWinningNum(service);
        readBonusNum(service, winningNumber);
        List<Integer> sames = compareNumbers();
        makeRank(sames);
        double revenue = calculateRevenue(sumRevenues(sames), lottoBox.getLottos().size());
        printRevenue((float) revenue);
    }

    void readPrice(LottoService service) {
        lottoBox = service.readPrice();
    }

    void readWinningNum(LottoService service) {
        winningNumber = service.readWinningNum();
    }

    void readBonusNum(LottoService service, Lotto winningNumber) {
        bonus = service.readBonusNum(winningNumber);
    }

    public List<Integer> compareNumbers() {
        List<Integer> countList = new ArrayList<>();
        List<Lotto> lottos = lottoBox.getLottos();

        IntStream.range(0, lottos.size())
                .filter(index -> {
                    int count = countSameNumbers(lottos.get(index).getNumbers(), winningNumber.getNumbers());
                    countList.add(count); // 모든 count를 리스트에 추가
                    return count == 5;
                })
                .forEach(index -> {
                    processCountIs5(index);
                });
        return countList;
    }

    public int processCountIs5(int index) {
        Lotto lotto = lottoBox.getLottos().get(index);

        if (lotto.getNumbers().contains(bonus.getNumber())) {
            lottoBox.checkBonus(index);
        }
        return index;
    }

    public int countSameNumbers(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int result = (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();

        System.out.println(result);
        return result;
    }

    void printLottos() {
        ConsolePrint.printPurchased(lottoBox.getLottos().size());

        for (Lotto lotto : lottoBox.getLottos()) {
            ConsolePrint.printLotto(lotto);
        }
    }

    public void makeRank(List<Integer> sames) {
        System.out.println(sames);
        ConsolePrint.printStatics();
        ConsolePrint.print3Same(Collections.frequency(sames, 3));
        ConsolePrint.print4Same(Collections.frequency(sames, 4));

        int result = Collections.frequency(sames, 5);
        int bonusSame = Collections.frequency(lottoBox.getBonusCheck(), true);

        ConsolePrint.printBonusX(result - bonusSame);
        ConsolePrint.printBonusO(bonusSame);
        ConsolePrint.print6Same(Collections.frequency(sames, 6));
    }

    void printRevenue(float result) {
        ConsolePrint.printRevenue(result);
    }

    public List<Integer> sumRevenues(List<Integer> sames) {
        List<Integer> revenues = new ArrayList<>();
        revenues.add(Collections.frequency(sames, 3) * SAME_NUMBER_3.getPrice());
        revenues.add(Collections.frequency(sames, 4) * SAME_NUMBER_4.getPrice());

        int result = Collections.frequency(sames, 5);
        int bonusSame = Collections.frequency(lottoBox.getBonusCheck(), true);

        revenues.add((result - bonusSame) * SAME_NUMBER_5_BONUSE_X.getPrice());
        revenues.add(bonusSame * SAME_NUMBER_5_BONUSE_O.getPrice());
        revenues.add(Collections.frequency(sames, 6) * SAME_NUMBER_6.getPrice());

        return revenues;
    }

    public double calculateRevenue(List<Integer> revenues, int num) {
        int sum = 0;

        for (int price : revenues) {
            sum += price;
        }

        double result = (double) sum / (num * 1000) * 100;
        result = Math.round(result * 10.0) / 10.0; // 소수점 둘째 자리에서 반올림

        return result;
    }
}
