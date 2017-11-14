package es.smurfdad.utils.genericutils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GenericBuilderTest implements GenericBuilder<ExampleBean> {

    @Test
    public void test() throws Exception {
        ExampleBean resultado = createInstance();
        assertNotNull(resultado);
        assertTrue(resultado instanceof ExampleBean);
    }
}
