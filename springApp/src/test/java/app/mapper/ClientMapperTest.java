package app.mapper;

import app.model.ClientModel;
import app.service.Impl.ClientServiceImpl;
import app.util.Util;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

public class ClientMapperTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private Map<String, Object> clientMapDummy = new HashMap<String, Object>() {{
        put("email", "testing3");
        put("names", "testing");
        put("lastNames", "testing");
        put("type", "testing");
        put("phone", "testing");
        put("country", "testing");
    }};

    @Test
    public void returnClientModelFromMapWithoutId() {
        ClientMapper.returnClientModelFromMapWithoutId(clientMapDummy);
    }
}
