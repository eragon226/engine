package jadx.tests.integration.switches;

import jadx.core.dex.nodes.ClassNode;
import jadx.tests.api.IntegrationTest;
import org.junit.Test;

import static jadx.tests.api.utils.JadxMatchers.countString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestSwitch3 extends IntegrationTest {

    @Test
    public void test() {
        ClassNode cls = getClassNode(TestCls.class);
        String code = cls.getCode().toString();

        assertThat(code, countString(0, "break;"));
        assertThat(code, countString(3, "return;"));
    }

    public static class TestCls {
        private int i;

        void test(int a) {
            switch (a) {
                case 1:
                    i = 1;
                    return;
                case 2:
                case 3:
                    i = 2;
                    return;
                default:
                    i = 4;
                    break;
            }
            i = 5;
        }

        public void check() {
            test(1);
            assertThat(i, is(1));
            test(2);
            assertThat(i, is(2));
            test(3);
            assertThat(i, is(2));
            test(4);
            assertThat(i, is(5));
            test(10);
            assertThat(i, is(5));
        }
    }
}