package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.Exceptions;
import lotto.model.Customer;
import lotto.model.Lotto;
import lotto.model.Ranking;
import lotto.model.WinningNumber;

import java.util.*;
import java.util.stream.Collectors;
public class LottoService {
    Exceptions exceptions = new Exceptions();

    private static final int lottoStartNumber = 1;
    private static final int lottoStopNumber = 45;
    private static final int lottoLength = 6;
    private static final int purchaseOneTicketValue = 1000;
    private static final int zero = 0;
    private static final int plusRanking = 1;

    private int buyLottoTicket(int money) {
        exceptions.isInvalidPurchaseMoneyAmount(money, purchaseOneTicketValue);
        int lottoTicket = money / purchaseOneTicketValue;
        return lottoTicket;
    }

    public void buyLottoByTicket(Customer customer) {
        int ticket = buyLottoTicket(customer.getPurchaseMoney());
        for (int i = ticket; i > zero; i--) {
            buyOneLotto(customer);
        }
    }

    private void buyOneLotto(Customer customer) {
        Lotto lotto = generateLottoNumber();
        customer.buyLotto(lotto);
    }

    private Lotto generateLottoNumber() {
        List<Integer> numbers = new ArrayList<>(lottoNumberGenerator(lottoStartNumber, lottoStopNumber, lottoLength));
        return new Lotto(numbers);
    }

    private List<Integer> lottoNumberGenerator(int start, int end, int length) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(start, end, length);
        return numbers;
    }

    public static List<Integer> sortLottoOrder(List<Integer> list) {
        List<Integer> sortLotto = new ArrayList<Integer>(list);
        Collections.sort(sortLotto);
        return sortLotto;
    }

    public List<Integer> integerList(String input){
        return Arrays.stream(intArray(input)).boxed().collect(Collectors.toList());
    }

    private int[] intArray(String input){
        return Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public void checkLottoRanking(Customer customer, WinningNumber winningNumber) {
        HashMap<Ranking,Integer> result = customer.getLottoResult();
        for(Lotto lotto : customer.getPurchaseLotteries()) {
            Ranking ranking = lottoRanking(winningNumber,lotto);
            result.put(ranking, result.getOrDefault(ranking, zero) + plusRanking);
        }
    }

    private Ranking lottoRanking(WinningNumber winningNumber, Lotto lotto){
        int count = countLottoNumber(winningNumber,lotto);
        boolean bonusCount = checkBonusNumberInLotto(winningNumber, lotto);
        Ranking ranking = Ranking.values()[count]; // 열거형 check
        if(ranking == Ranking.FIVE && bonusCount){
            return Ranking.FIVE_BONUS;
        }
        return ranking;
    }

    private int countLottoNumber(WinningNumber winningNumber, Lotto lotto){
        return checkSameNumber(winningNumber.getWinningNumbers(),lotto.getNumbers());
    }

    private boolean checkBonusNumberInLotto(WinningNumber winningNumber, Lotto lotto){
        return lotto.getNumbers().contains(winningNumber.getBonusNumber());
    }

    private int checkSameNumber(List<Integer> list1, List<Integer> list2) {
        int count = zero;
        for (int number : list2) {
            if (list1.contains(number)) {
                count++;
            }
        }
        return count;
    }
}
