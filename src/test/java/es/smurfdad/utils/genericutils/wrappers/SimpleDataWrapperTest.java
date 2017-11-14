package es.smurfdad.utils.genericutils.wrappers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SimpleDataWrapperTest {

    @Test
    public void test() {
        Nif nif = new Nif();
        nif.set("AAAAAAA");
        assertNotNull(nif.get());
    }

}
