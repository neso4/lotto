package lotto.controller;

import java.util.function.Supplier;

public class LottoController {
    public static void run() {
        
    }
    protected static Object repeatUntilNoInternalException(Supplier<Object> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
