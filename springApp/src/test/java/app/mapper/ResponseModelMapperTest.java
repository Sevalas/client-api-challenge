package app.mapper;

import app.constant.Constant;
import app.model.ResponseModel;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ResponseModelMapperTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void buildSuccessfulResponseModel() {
        ResponseModel responseModel = ResponseModelMapper.buildSuccessfulResponseModel("test",new Object());
        Assert.assertEquals(responseModel.getCode(),Constant.SUCCESSFUL_PROCESS_CODE);
    }
    @Test
    public void buildExceptionResponseModel() {
        ResponseModel responseModel = ResponseModelMapper.buildExceptionResponseModel(new Exception());
        Assert.assertEquals(responseModel.getCode(),Constant.ERROR_CODE);
    }
}
