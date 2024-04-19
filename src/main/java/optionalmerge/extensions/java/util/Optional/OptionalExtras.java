package optionalmerge.extensions.java.util.Optional;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class OptionalExtras {

    private OptionalExtras() {
        throw new UnsupportedOperationException("utility class");
    }

    public static <T> Optional<T> merge(Optional<T> first, Optional<T> second, BinaryOperator<T> operator) {
        if (first.isPresent() && second.isEmpty()) {
            return first;
        }

        if (first.isEmpty() && second.isPresent()) {
            return second;
        }

        return first.or(() -> second);
    }
}
