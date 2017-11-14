package es.smurfdad.utils.genericutils;

import static es.smurfdad.utils.genericutils.BeanMergeUtil.merge;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BeanMergeUtilTest {

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    public static class Bean1 {
        private String attr1;

        private Integer attr2;

        private Bean2 attr3;

        public String getAttr1() {
            return this.attr1;
        }

        public void setAttr1(String attr1) {
            this.attr1 = attr1;
        }

        public Integer getAttr2() {
            return this.attr2;
        }

        public void setAttr2(Integer attr2) {
            this.attr2 = attr2;
        }

        public Bean2 getAttr3() {
            return this.attr3;
        }

        public void setAttr3(Bean2 attr3) {
            this.attr3 = attr3;
        }

    }

    public static class Bean2 {
        private String attr1;

        private boolean attr2;

        public String getAttr1() {
            return this.attr1;
        }

        public void setAttr1(String attr1) {
            this.attr1 = attr1;
        }

        public boolean getAttr2() {
            return this.attr2;
        }

        public void setAttr2(boolean attr2) {
            this.attr2 = attr2;
        }

    }

    private Bean1 getFirstBean() {
        Bean1 bean = new Bean1();
        bean.setAttr1("A");
        bean.setAttr2(1);
        bean.setAttr3(new Bean2());
        bean.getAttr3().setAttr1("B");
        bean.getAttr3().setAttr2(true);
        return bean;
    }

    @Before
    public void setUp() {
        System.out.println("SetUp");
    }

    @Test
    @Repeat(times = 10000000)
    public void test() throws Exception {

        Bean1 bean = new Bean1();
        bean.setAttr1("B");
        bean.setAttr3(new Bean2());
        bean.getAttr3().setAttr1("C");

        Bean1 result = merge(getFirstBean(), bean);

        assertNotNull(result);
        assertEquals("Atributo 1", "B", result.getAttr1());
        assertEquals("Atributo 2", Integer.valueOf(1), result.getAttr2());
        assertEquals("Atributo 3.1", "C", result.getAttr3().getAttr1());
        assertEquals("Atributo 3.2", false, result.getAttr3().getAttr2());
    }

}
