package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.util.LottoVO;
import lotto.util.UiVO;
import lotto.util.UiVO.MatchType;
import camp.nextstep.edu.missionutils.Console;

public class LottoServiceImpl implements LottoService {

    /**
     * 구입 금액 입력 받는 함수
     *
     * @return : 로또 구입 개수
     */
    @Override
    public int calculatePurchaseCount() {
        while (true) {
            try {
                String inputPurchaseAmount = Console.readLine();

                int amount = stringToInt(inputPurchaseAmount);

                validatePurchaseAmount(amount);

                return calculateLottoCount(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 랜덤 로또 값 생성해서 반환
     *
     * @return : 생성된 Lotto List
     */
    @Override
    public List<Lotto> generateLottos(int lottoCount) {

        return Stream.generate(this::generateLottoNumbers)
                .limit(lottoCount)
                .collect(Collectors.toList());
    }

    /**
     * 사용자로부터 당첨 번호 입력 받고 반환 1~45 예외 처리 필요
     *
     * @return : 당첨 번호
     */
    @Override
    public List<Integer> inputWinningNumbers() {
        return null;
    }

    /**
     * 사용자로부터 보너스 번호 입력 받고 반환 1~45 예외 처리 필요
     *
     * @return : 보너스 번호
     */
    @Override
    public int inputBonusNumber() {
        return 0;
    }

    /**
     * 당첨 통계 계산하기
     *
     * @param lottoNumbers : 로또값 배열
     * @return : 당첨 통계
     */
    @Override
    public Map<MatchType, Integer> calculateWinningStatistics(List<Integer>[] lottoNumbers) {
        return null;
    }

    /**
     * 수익률 계산하기
     *
     * @param earnings : 수익
     * @return : 수익률
     */
    @Override
    public double calculateReturnRate(int earnings) {
        return 0;
    }

    private int stringToInt(String str) {

        int result = 0;

        try {
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(UiVO.getInputNumberFormatException());
        }

        return result;
    }

    private void validatePurchaseAmount(int amount) {

        if (!isNonNegative(amount)) {
            throw new IllegalArgumentException(UiVO.getPurchaseAmountInputMinException());
        }

        if (!isDivisibleByThousand(amount)) {
            throw new IllegalArgumentException(UiVO.getPurchaseAmountInputUnitsException());
        }
    }

    private boolean isDivisibleByThousand(int amount) {
        return amount % 1000 == 0;
    }

    private boolean isNonNegative(int amount) {
        return amount > 0;
    }

    private int calculateLottoCount(int amount) {

        int lottoPrice = LottoVO.getLottoPrice();
        return amount / lottoPrice;
    }

    private Lotto generateLottoNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

        return new Lotto(numbers);
    }
}
