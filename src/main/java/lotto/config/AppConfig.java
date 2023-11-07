package lotto.config;

import lotto.controller.LottoController;
import lotto.model.LottoModel;
import lotto.view.LottoBuyer;
import lotto.view.LottoDrawer;

public class AppConfig {
    private static LottoController lottoController;
    private static LottoBuyer lottoBuyer;
    private static LottoDrawer lottoDrawer;
    private static LottoModel lottoModel;

    public static AppConfig init(){
        return new AppConfig();
    }

    private AppConfig(){
        setLottoModel();
        setController(lottoModel);
        setLottoBuyer();
        setLottoDrawer();

        LottoProcess.start(lottoBuyer, lottoDrawer);
    }

    private static void setController(LottoModel lottoModel) {
        if (lottoController == null) {
            lottoController = new LottoController(lottoModel);
        }
    }

    private static void setLottoBuyer() {
        if (lottoBuyer == null) {
            lottoBuyer = new LottoBuyer();
        }
    }

    private static void setLottoDrawer() {
        if (lottoDrawer == null) {
            lottoDrawer = new LottoDrawer();
        }
    }

    private static void setLottoModel() {
        if (lottoModel == null) {
            lottoModel = new LottoModel();
        }
    }

    public static LottoController getController() {
        return lottoController;
    }
}
