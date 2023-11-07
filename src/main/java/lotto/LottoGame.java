package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class LottoGame {
    public void play() {
        //request price
        System.out.println("구입금액을 입력해 주세요.");
        String inputPrice = Console.readLine();

        //parseToInt
        Integer ticketCount = Integer.parseInt(inputPrice) / 1000;

        //create Lottos
        Lottos randomLottos = Lottos.create(ticketCount);

        //request lotto numbers
        System.out.println("당첨 번호를 입력해 주세요.");
        String userLottoNumbers = Console.readLine();

        List<Integer> numbers = Arrays.stream(userLottoNumbers.split(","))
                .map(Integer::parseInt)
                .toList();

        Lotto userLotto = Lotto.create(numbers);

        //request bonus number
        System.out.println("보너스 번호를 입력해 주세요.");
        Integer userBonus = Integer.parseInt(Console.readLine());

        //compare lotto and bonusNumber
        Buyer buyer = Buyer.create(userLotto, userBonus);

        Result result = randomLottos.calcuateResult(buyer);
    }
}
