package es.smurfdad.utils.genericutils;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Interfaz que implementa un metodo que devuelve una instancia del tipo parametrizado.
 *
 * @author @Smurf_Dad
 * @param <T>
 */
public interface GenericBuilder<T> {

    @SuppressWarnings("unchecked")
    default T createInstance() throws InstantiationException, IllegalAccessException {
        T out = null;
        Optional<ParameterizedType> type = Stream.of(getClass().getGenericInterfaces()).map(t -> ParameterizedType.class.cast(t))
                .filter(t -> t.getRawType().equals(GenericBuilder.class)).findFirst();
        if (type.isPresent()) {
            Class<T> clazz = (Class<T>) type.get().getActualTypeArguments()[0];
            out = clazz.newInstance();
        }
        return out;
    }
}
