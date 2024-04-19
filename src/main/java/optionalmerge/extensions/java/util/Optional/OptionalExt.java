package optionalmerge.extensions.java.util.Optional;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import java.util.Optional;
import java.util.function.BinaryOperator;

@Extension
public class OptionalExt {

  public static <T> Optional<T> merge(@This Optional<T> thiz, Optional<T> other, BinaryOperator<T> operator) {
    if (thiz.isEmpty() && other.isEmpty()) {
      return Optional.empty();
    }
    if (thiz.isPresent() && other.isEmpty()) {
      return thiz;
    }
    if (thiz.isEmpty()) {
      return other;
    }
    return Optional.ofNullable(operator.apply(thiz.get(), other.get()));
  }

  public static <T> Optional<T> merge(@This Optional<T> thiz, T other, BinaryOperator<T> operator) {
    if (thiz.isEmpty() && other == null) {
      return Optional.empty();
    }
    if (thiz.isPresent() && other == null) {
      return thiz;
    }
    if (thiz.isEmpty()) {
      return Optional.of(other);
    }
    return Optional.ofNullable(operator.apply(thiz.get(), other));
  }
}