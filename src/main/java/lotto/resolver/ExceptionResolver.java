package lotto.resolver;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionResolver {

    private ExceptionResolver() {
    }

    public static <T> T resolveInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            return resolveInput(supplier);
        }
    }

    public static <T> void resolveProcess(final Consumer<T> consumer, T t) {
        try {
            consumer.accept(t);
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            resolveProcess(consumer, t);
        }
    }

    private static void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
