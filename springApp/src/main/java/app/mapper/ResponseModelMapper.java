package app.mapper;

import app.constant.Constant;
import app.model.ResponseModel;

public class ResponseModelMapper {

    public static ResponseModel buildSuccessfulResponseModel(String objectType, Object Object) {
        ResponseModel responseModel = new ResponseModel(
                Constant.SUCCESSFUL_PROCESS_CODE, Constant.SUCCESS_PROCESS_MESSAGE,
                objectType, Object, Constant.NO_EXCEPTION
        );

        return responseModel;
    }

    public static ResponseModel buildExceptionResponseModel(Exception exceptionObject) {
        String descriptionMessage =
                exceptionObject == null ? Constant.UNDEFINED_EXCEPTION_MESSAGE : exceptionObject.toString();

        ResponseModel responseModel = new ResponseModel(
                Constant.ERROR_CODE, Constant.ERROR_MESSAGE,
                Constant.NO_RESPONSE_OBJECT_TYPE, Constant.NO_RESPONSE_OBJECT,descriptionMessage
        );

        return responseModel;
    }
}
