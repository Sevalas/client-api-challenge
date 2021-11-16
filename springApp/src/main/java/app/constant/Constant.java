package app.constant;

public class Constant {

    //Response Codes
    public final static String SUCCESSFUL_PROCESS_CODE = "00";
    public final static String ERROR_CODE = "01";

    //Response Messages
    public final static String SUCCESS_PROCESS_MESSAGE =
            "Process completed successfully";
    public final static String CLIENT_EMAIL_ALREADY_EXIST_MESSAGE =
            "Process present a error, a client with this email already exist";
    public final static String CLIENT_EMAIL_NOT_EXIST_MESSAGE =
            "Process present a error, a client with this email not exist";
    public final static String CLIENT_ID_ALREADY_EXIST_MESSAGE =
            "Process present a error, a client with this ID already exist";
    public final static String CLIENT_ID_NOT_EXIST_MESSAGE =
            "Process present a error, a client with this ID not exist";
    public final static String ERROR_MESSAGE =
            "Process present an unexpected error";
    public final static String UPDATE_PROCESS_MESSAGE =
            "{modifiedCount} client with the {key and value} was updated";

    public final static String NO_EXCEPTION = null;
    public final static String NO_RESPONSE_OBJECT = null;
    public final static String NO_RESPONSE_OBJECT_TYPE = null;
    public final static String UNDEFINED_EXCEPTION_MESSAGE = "Undefined error exception";

    public final static String EMPTY_STRING = "";

}
