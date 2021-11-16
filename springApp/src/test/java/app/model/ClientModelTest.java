package app.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ClientModelTest {

    private ClientModel clientModel = new ClientModel();

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup(){
        clientModel = getClientModelDummy();
    }

    @Test
    public void getClientModel() {

        Assert.assertNull(clientModel.getId());
        Assert.assertNotNull(clientModel.getEmail());
        Assert.assertNotNull(clientModel.getNames());
        Assert.assertNotNull(clientModel.getLastNames());
        Assert.assertNotNull(clientModel.getType());
        Assert.assertNotNull(clientModel.getPhone());
        Assert.assertNotNull(clientModel.toString());
    }

    private ClientModel getClientModelDummy(){
        ClientModel clientModel = new ClientModel();
        clientModel.setEmail("test");
        clientModel.setNames("test");
        clientModel.setLastNames("test");
        clientModel.setType("test");
        clientModel.setPhone("test");
        return clientModel;
    }
}
