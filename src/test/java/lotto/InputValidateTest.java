package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputValidateTest {
    private InputValidate inputValidate;
    @BeforeEach
    void setUp(){
        inputValidate = new InputValidate();
    }
    @Test
    void 보너스_숫자의_갯수가_1개인지_확인(){
        assertThrows(IllegalArgumentException.class, () -> inputValidate.validateIsNumber(" 1 2"));
        assertThrows(IllegalArgumentException.class, () -> inputValidate.validateIsNumber("1,2,3"));
        assertThrows(IllegalArgumentException.class, () -> inputValidate.validateIsNumber(""));
        assertEquals("1", inputValidate.validateIsNumber("1"));
    }

    @Test
    void String이_int로_리턴되는지_확인(){
        assertEquals(8000, inputValidate.changeInt("8000"));
    }

    @Test
    void 여섯개의_숫자가_List로_변환되는지_확인(){
        assertEquals(List.of("1","2","3","4","5","6"), inputValidate.changeList("1,2,3,4,5,6"));
        assertEquals(List.of("1","2","3","4","5","6"), inputValidate.changeList("1,2,3,4,5 3,6"));
    }

    @Test
    void 여섯개의_숫자에_공백이_있는지_확인(){
        assertThrows(IllegalArgumentException.class, () -> inputValidate.changeInt("1,2,3 3,4,5 3,6"));
    }
}
