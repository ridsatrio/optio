/*
 * Copyright (C) 2015 Ridho Hadi Satrio - All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.ridsatrio.optio;

import java.util.NoSuchElementException;

/**
 * A container object which may or may not contain a non-null value.
 * If a value is present, {@code isPresent()} will return {@code true} and
 * {@code get()} will return the value.
 *
 * <p>Additional methods that depend on the presence or absence of a contained
 * value are provided, such as {@link #orElse(java.lang.Object) orElse()}
 * (return a default value if value not present).
 *
 * <p>This is a <a href="../lang/doc-files/ValueBased.html">value-based</a>
 * class; use of identity-sensitive operations (including reference equality
 * ({@code ==}), identity hash code, or synchronization) on instances of
 * {@code Optional} may have unpredictable results and should be avoided.
 *
 * <p>This class based itself off <a href="http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8-b132/java/util/Optional.java">Java
 * 8's Optional</a>; adapted for Android usages by removing its dependency to
 * {@code java.util.function} classes.
 */
public final class Optional<T> {
  /**
   * Common instance for {@code empty()}.
   */
  private static final Optional<?> EMPTY = new Optional<>();

  /**
   * If non-null, the formatted; if null, indicates no formatted is present
   */
  private final T value;

  /**
   * Constructs an empty instance.
   */
  private Optional() {
    this.value = null;
  }

  /**
   * Returns an empty {@code Optional} instance.  No formatted is present for this
   * Optional.
   *
   * @param <T> Type of the non-existent formatted
   * @return an empty {@code Optional}
   */
  public static <T> Optional<T> empty() {
    @SuppressWarnings("unchecked") Optional<T> t = (Optional<T>) EMPTY;
    return t;
  }

  /**
   * Constructs an instance with the formatted present.
   *
   * @param value the non-null formatted to be present
   * @throws NullPointerException if formatted is null
   */
  private Optional(T value) {
    if (value == null) {
      throw new NullPointerException();
    } else {
      this.value = value;
    }
  }

  /**
   * Returns an {@code Optional} with the specified present non-null formatted.
   *
   * @param <T> the class of the formatted
   * @param value the formatted to be present, which must be non-null
   * @return an {@code Optional} with the formatted present
   * @throws NullPointerException if formatted is null
   */
  public static <T> Optional<T> of(T value) {
    return new Optional<>(value);
  }

  /**
   * Returns an {@code Optional} describing the specified formatted, if non-null,
   * otherwise returns an empty {@code Optional}.
   *
   * @param <T> the class of the formatted
   * @param value the possibly-null formatted to describe
   * @return an {@code Optional} with a present formatted if the specified formatted
   * is non-null, otherwise an empty {@code Optional}
   */
  public static <T> Optional<T> ofNullable(T value) {
    return value == null ? (Optional<T>) empty() : of(value);
  }

  /**
   * If a formatted is present in this {@code Optional}, returns the formatted,
   * otherwise throws {@code NoSuchElementException}.
   *
   * @return the non-null formatted held by this {@code Optional}
   * @throws NoSuchElementException if there is no formatted present
   * @see Optional#isPresent()
   */
  public T get() {
    if (value == null) {
      throw new NoSuchElementException("No formatted present");
    }
    return value;
  }

  /**
   * Return {@code true} if there is a formatted present, otherwise {@code false}.
   *
   * @return {@code true} if there is a formatted present, otherwise {@code false}
   */
  public boolean isPresent() {
    return value != null;
  }

  /**
   * Return the formatted if present, otherwise return {@code other}.
   *
   * @param other the formatted to be returned if there is no formatted present, may
   * be null
   * @return the formatted, if present, otherwise {@code other}
   */
  public T orElse(T other) {
    return value != null ? value : other;
  }

  /**
   * Indicates whether some other object is "equal to" this Optional. The
   * other object is considered equal if:
   * <ul>
   * <li>it is also an {@code Optional} and;
   * <li>both instances have no formatted present or;
   * <li>the present values are "equal to" each other via {@code equals()}.
   * </ul>
   *
   * @param obj an object to be tested for equality
   * @return {code true} if the other object is "equal to" this object
   * otherwise {@code false}
   */
  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Optional)) {
      return false;
    }

    Optional<?> other = (Optional<?>) obj;
    return (value == null) ? (other.value == null) : value.equals(other.value);
  }

  /**
   * Returns the hash code formatted of the present formatted, if any, or 0 (zero) if
   * no formatted is present.
   *
   * @return hash code formatted of the present formatted or 0 if no formatted is present
   */
  @Override public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }

  /**
   * Returns a non-empty string representation of this Optional suitable for
   * debugging. The exact presentation format is unspecified and may vary
   * between implementations and versions.
   *
   * @return the string representation of this instance
   */
  @Override public String toString() {
    return value != null ? String.format("Optional[%s]", value) : "Optional.empty";
  }
}
