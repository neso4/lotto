package lotto.service;

import java.util.List;
import lotto.dto.MoneyDTO;
import lotto.enums.Constant;
import lotto.enums.OutputMessage;
import lotto.model.Lotto;
import lotto.repository.LottoRepository;
import lotto.utils.RandomGenerator;

public class OrderLottoServiceImpl implements OrderLottoService {
    private final LottoRepository lottoRepository;

    public OrderLottoServiceImpl(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    @Override
    public Integer order(MoneyDTO moneyDTO) {
        Integer countOfLotto = getCountOfLotto(moneyDTO);

        for (int i = 0; i < countOfLotto; ++i) {
            Lotto lotto = new Lotto(createLotto());
            lottoRepository.save(lotto);
        }

        return countOfLotto;
    }

    @Override
    public String getOrderList() {
        List<Lotto> entireLotto = lottoRepository.findAll();
        String result = "";

        for (Lotto lotto : entireLotto) {
            result += String.format(OutputMessage.LOTTO_NUMBERS.getMessage(), lotto);
            result += Constant.LINE_FEED.getContent();
        }

        return result;
    }

    private Integer getCountOfLotto(MoneyDTO moneyDTO) {
        return moneyDTO.getAmount() / Constant.MONEY_UNITS.getContentToInteger();
    }

    private List<Integer> createLotto() {
        return RandomGenerator.getRandomNumberList();
    }
}
