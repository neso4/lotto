package lotto.application;


import lotto.domain.Lotto;
import lotto.domain.User;

import java.util.List;

public class UserService {
    private User user;

    public UserService() {
        this.user = new User();
    }

    public void updateUser(List<Lotto> lottos, int cnt) {
        user.setLottoCnt(cnt);
        user.setLottos(lottos);
    }

    public User getUser() {
        return this.user;
    }
}
