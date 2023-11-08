package lotto;

import lotto.domain.buyer.model.Buyer;
import lotto.domain.buyer.service.BuyerService;
import lotto.domain.lotteryCommittee.model.LotteryCommittee;
import lotto.domain.lotteryCommittee.service.LotteryCommitteeService;

public class Application {
    public static void main(String[] args) {

        Buyer buyer = new Buyer();
        BuyerService buyerService = new BuyerService();
        LotteryCommittee committee = new LotteryCommittee();
        LotteryCommitteeService committeeService = new LotteryCommitteeService();

        buyerService.tryBuy(buyer);
        buyer.printLottos();
        committeeService.getWeeklyNumber();
//        buyerService.tryCheck(buyer);
//        buyer.printResult();
//        buyerService.tryCalculate(buyer);
//        buyer.printProfitRate();
    }
}
