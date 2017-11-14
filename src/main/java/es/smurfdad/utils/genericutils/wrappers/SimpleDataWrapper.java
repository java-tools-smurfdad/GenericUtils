package es.smurfdad.utils.genericutils.wrappers;

public abstract class SimpleDataWrapper<T> {

    private T value;

    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.value.equals(obj);
    }

}
