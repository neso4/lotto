package lotto;

import lotto.repository.LottoRepository;
import lotto.repository.WinningLottoRepository;
import lotto.service.CalculateLotteryService;
import lotto.service.CalculateLotteryServiceImpl;
import lotto.service.OrderLottoService;
import lotto.service.OrderLottoServiceImpl;
import lotto.service.SelectWinningLottoService;
import lotto.service.SelectWinningLottoServiceImpl;

public class AppConfig {
    private final LottoRepository lottoRepository;
    private final WinningLottoRepository winningLottoRepository;

    private OrderLottoService orderLottoService;
    private SelectWinningLottoService selectWinningLottoService;
    private CalculateLotteryService calculateLotteryService;

    public AppConfig(LottoRepository lottoRepository, WinningLottoRepository winningLottoRepository) {
        this.lottoRepository = lottoRepository;
        this.winningLottoRepository = winningLottoRepository;
    }

    public OrderLottoService getOrderLottoServiceInstance() {
        if (orderLottoService == null) {
            orderLottoService = new OrderLottoServiceImpl(lottoRepository);
        }

        return orderLottoService;
    }
    public SelectWinningLottoService getSelectWinningLottoServiceInstance() {
        if (selectWinningLottoService == null) {
            selectWinningLottoService = new SelectWinningLottoServiceImpl(winningLottoRepository);
        }

        return selectWinningLottoService;
    }

    public CalculateLotteryService getCalculateLotteryServiceInstance() {
        if (calculateLotteryService == null) {
            calculateLotteryService = new CalculateLotteryServiceImpl(lottoRepository, winningLottoRepository);
        }

        return calculateLotteryService;
    }
}
