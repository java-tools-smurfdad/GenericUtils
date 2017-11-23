package es.smurfdad.utils.genericutils.semanticwrappers;

import static java.util.Objects.nonNull;

import java.io.Serializable;

public abstract class SemanticWrapper<T extends Serializable> {

    private T value;

    public SemanticWrapper(T value) {
        assert nonNull(value) : "value no puede ser nulo";
        this.value = value;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return this.value.equals(((SemanticWrapper) obj).value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public T get() {
        return this.value;
    }

}
