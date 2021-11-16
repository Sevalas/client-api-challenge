package app.constant;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ConstantTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void Constant() throws Exception {
        Constructor<Constant> constructor =  Constant.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        Constant constant = constructor.newInstance();
        Assert.assertNotNull(constant);
        for (Field field : constant.getClass().getDeclaredFields())
            if (field != null)
                Assert.assertFalse(field.getName() == null && field.get(constant) == null);
    }
}
