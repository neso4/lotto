package lotto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {
    @DisplayName("돈 액수에 맞는 로또 개수 생성")
    @Test
    void correctCreateLottoIndex(){
        LottoController lottoController = new LottoController();

        assertEquals(13,lottoController.lottoIndexGenerate(13000).size());
    }
}
