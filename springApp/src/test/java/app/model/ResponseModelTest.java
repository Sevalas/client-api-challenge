package app.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ResponseModelTest {

    private ResponseModel responseModel = new ResponseModel();

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup(){
        responseModel = getResponseModelDummy();
    }

    @Test
    public void getResponseModel() {

        Assert.assertNotNull(responseModel.getCode());
        Assert.assertNotNull(responseModel.getMessage());
        Assert.assertNotNull(responseModel.getObjectType());
        Assert.assertNotNull(responseModel.getResponseObject());
        Assert.assertNotNull(responseModel.getException());
        Assert.assertNotNull(responseModel.toString());
    }

    private ResponseModel getResponseModelDummy(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode("test");
        responseModel.setMessage("test");
        responseModel.setObjectType("test");
        responseModel.setResponseObject("test");
        responseModel.setException("test");
        responseModel = new ResponseModel("test","test","test","test","test");
        return responseModel;
    }

}
