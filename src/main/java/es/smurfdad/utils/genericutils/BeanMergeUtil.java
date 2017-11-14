package es.smurfdad.utils.genericutils;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;
import static org.springframework.beans.BeanUtils.isSimpleProperty;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BeanMergeUtil {

    public static <T> T merge(T a, T b) throws InstantiationException, IllegalAccessException {
        final List<Exception> excepcions = new ArrayList<>();

        @SuppressWarnings("unchecked")
        T result = (T) a.getClass().newInstance();

        Stream.of(getPropertyDescriptors(a.getClass())).parallel().forEach(pd -> {
            try {
                setValue(result, a, b, pd);
            } catch (Exception e) {

                excepcions.add(e);
            }
        });
        if (!excepcions.isEmpty()) {
            throw new RuntimeException(excepcions.get(0));
        }
        return result;
    }

    private static <T> void setValue(T result, T a, T b, PropertyDescriptor pd)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method getter = pd.getReadMethod();
        Method setter = pd.getWriteMethod();
        if (nonNull(getter) && nonNull(setter)) {

            Object getA = getter.invoke(a);
            Object getB = getter.invoke(b);
            Object value = getPriorizedValue(getA, getB);
            setter.invoke(result, value);
        }

    }

    private static Object getPriorizedValue(Object a, Object b) throws InstantiationException, IllegalAccessException {
        Object out = null;
        if (isNull(b)) {
            out = a;
        } else {
            if (isSimpleProperty(b.getClass())) {
                out = b;
            } else {
                out = merge(a, b);
            }
        }

        return out;
    }

}
