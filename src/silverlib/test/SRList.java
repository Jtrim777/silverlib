package silverlib.test;

import java.util.function.Function;
import java.util.function.BiFunction;

public abstract class SRList<T> {

  public abstract int size();
  public abstract <E> SRList<E> map(Function<T, E> op);
  public abstract <E> E fold(BiFunction<T, E, E> op, E baseCase);
  public abstract SRList<T> filter(Function<T, Boolean> predicate);
  public abstract SRList<T> prepend(T elem);
  public abstract SRList<T> append(T elem);
  public abstract SRList<T> insert(T elem, int index);
  public abstract SRList<T> insert(T elem, int index, int init);
  public abstract SRList<T> insertSorted(T elem, BiFunction<T, T, Boolean> doesFollow);
  public abstract SRList<T> sort(BiFunction<T, T, Boolean> doesFollow);
  public abstract SRList<T> sortHelp(BiFunction<T, T, Boolean> doesFollow, SRList<T> soFar);
  public abstract SRList<T> concat(SRList<T> other);

  public SRList<T> empty() {
    return new Empty<>();
  }

  static class Empty<N> extends SRList<N> {

    @Override
    public int size() {
      return 0;
    }

    @Override
    public <E> SRList<E> map(Function<N, E> op) {
      return new Empty<>();
    }

    @Override
    public <E> E fold(BiFunction<N, E, E> op, E baseCase) {
      return baseCase;
    }

    @Override
    public SRList<N> filter(Function<N, Boolean> predicate) {
      return this;
    }

    @Override
    public SRList<N> prepend(N elem) {
      return new Cons<>(elem, this);
    }

    @Override
    public SRList<N> append(N elem) {
      return new Cons<>(elem, this);
    }

    @Override
    public SRList<N> insert(N elem, int index) {
      if (index == 0) {
        return new Cons<>(elem);
      } else {
        System.out.println("Error: Index ["+index+"] is out-of-bounds for list of length 0");
        System.exit(1);
        return this;
      }
    }

    @Override
    public SRList<N> insert(N elem, int index, int init) {
      if (index == 0) {
        return new Cons<>(elem);
      } else {
        System.out.println("Error: Index ["+index+"] is out-of-bounds for list of length "
            +(init-index));
        System.exit(1);
        return this;
      }
    }

    @Override
    public SRList<N> insertSorted(N elem, BiFunction<N, N, Boolean> doesFollow) {
      return new Cons<>(elem);
    }

    @Override
    public SRList<N> sort(BiFunction<N, N, Boolean> doesFollow) {
      return this;
    }

    @Override
    public SRList<N> sortHelp(BiFunction<N, N, Boolean> doesFollow, SRList<N> soFar) {
      return soFar;
    }

    @Override
    public SRList<N> concat(SRList<N> other) {
      return other;
    }
  }

  static class Cons<M> extends SRList<M> {
    M element;
    SRList<M> rest;

    public Cons(M elem, SRList<M> rest) {
      this.element = elem;
      this.rest = rest;
    }

    public Cons(M elem) {
      this.element = elem;
      this.rest = empty();
    }


    @Override
    public int size() {
      return 1 + rest.size();
    }

    @Override
    public <E> SRList<E> map(Function<M, E> op) {
      return new Cons<>(op.apply(this.element), rest.map(op));
    }

    @Override
    public <E> E fold(BiFunction<M, E, E> op, E baseCase) {
      return op.apply(this.element, this.rest.fold(op, baseCase));
    }

    @Override
    public SRList<M> filter(Function<M, Boolean> predicate) {
      if (predicate.apply(this.element)) {
        return new Cons<>(this.element, this.rest.filter(predicate));
      } else {
        return this.rest.filter(predicate);
      }
    }

    @Override
    public SRList<M> prepend(M elem) {
      return new Cons<>(elem, new Cons<>(this.element, this.rest));
    }

    @Override
    public SRList<M> append(M elem) {
      return new Cons<>(this.element, rest.append(elem));
    }

    @Override
    public SRList<M> insert(M elem, int index) {
      if (index == 0) {
        return new Cons<>(elem, new Cons<>(this.element, this.rest));
      } else {
        return new Cons<>(this.element, this.rest.insert(elem, index-1, index));
      }
    }

    @Override
    public SRList<M> insert(M elem, int index, int init) {
      if (index == 0) {
        return new Cons<>(elem, new Cons<>(this.element, this.rest));
      } else {
        return new Cons<>(this.element, this.rest.insert(elem, index-1, init));
      }
    }

    @Override
    public SRList<M> insertSorted(M elem, BiFunction<M, M, Boolean> doesFollow) {
      if (doesFollow.apply(elem, this.element)) {
        return new Cons<>(this.element, this.rest.insertSorted(elem, doesFollow));
      } else {
        return new Cons<>(elem , new Cons<>(this.element, this.rest));
      }
    }

    @Override
    public SRList<M> sort(BiFunction<M, M, Boolean> doesFollow) {
      return null;
    }

    @Override
    public SRList<M> sortHelp(BiFunction<M, M, Boolean> doesFollow, SRList<M> soFar) {
      return soFar.insertSorted(this.element, doesFollow);
    }

    @Override
    public SRList<M> concat(SRList<M> other) {
      return new Cons<>(this.element, this.rest.concat(other));
    }
  }
}
