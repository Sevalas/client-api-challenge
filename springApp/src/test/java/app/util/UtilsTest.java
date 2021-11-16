package app.util;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UtilsTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void responseModelUnexpectedErrorHandlerOK() {
        Object testObject = "test";
        Assert.assertEquals("",Util.returnMapValueObjectAsString(null));
        Assert.assertEquals("test",Util.returnMapValueObjectAsString(testObject));

    }

}
