package app.service.Impl;

import app.constant.Constant;
import app.mapper.ClientMapper;
import app.mapper.ResponseModelMapper;
import app.model.ClientModel;
import app.model.ResponseModel;
import app.repository.CreateClientRepository;
import app.repository.DeleteClientRepository;
import app.repository.FindClientRepository;
import app.repository.UpdateClientRepository;
import app.service.ClientService;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private FindClientRepository findClientRepository;

    @Autowired
    private CreateClientRepository createClientRepository;

    @Autowired
    private UpdateClientRepository updateClientRepository;

    @Autowired
    private DeleteClientRepository deleteClientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public ResponseModel getListOfAllClients(){
        try {
            List<ClientModel> listOfAllClients = returnListOfAllClients();
            String objectType = List.class.getSimpleName() + "<" + ClientModel.class.getSimpleName() + ">";

            return ResponseModelMapper.buildSuccessfulResponseModel(objectType,listOfAllClients);

        } catch(Exception exceptionObject) {
            LOGGER.error("Error obtening a list of all clients, ClientServiceImpl.getListOfAllClients",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }

    @Override
    public ResponseModel getClientByEmail(String clientEmail){
        try {
            throwClientExistenceExceptionByEmail(clientEmail, false);

            ClientModel client = returnClientByEmail(clientEmail);
            String objectType = ClientModel.class.getSimpleName();

            return ResponseModelMapper.buildSuccessfulResponseModel(objectType,client);

        } catch(Exception exceptionObject) {
            LOGGER.error("Error obtening a client by email, ClientServiceImpl.getClientByEmail",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }

    @Override
    public ResponseModel getClientById(String clientId){
        try {
            throwClientExistenceExceptionById(clientId, false);

            ClientModel client = returnClientById(clientId);
            String objectType = ClientModel.class.getSimpleName();

            return ResponseModelMapper.buildSuccessfulResponseModel(objectType,client);
        } catch(Exception exceptionObject) {
            LOGGER.error("Error obtening a client by id, ClientServiceImpl.getClientById",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }

    @Override
    public ResponseModel createAClient(Map<String, Object> clientModelMap){
        try {
            ClientModel requestClientModel = ClientMapper.returnClientModelFromMapWithoutId(clientModelMap);

            throwClientExistenceExceptionByEmail(requestClientModel.getEmail(), true);

            ClientModel insertedClient = returnInsertedClient(requestClientModel);
            String objectType = ClientModel.class.getSimpleName();

            return ResponseModelMapper.buildSuccessfulResponseModel(objectType,insertedClient);

        } catch(Exception exceptionObject) {
            LOGGER.error("Error creating a client, ClientServiceImpl.createAClient",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }

    }

    @Override
    public ResponseModel updateClientByEmail(String clientEmail, Map<String, Object> clientModelMap) {
        try {
            ClientModel requestClientModel = ClientMapper.returnClientModelFromMapWithoutId(clientModelMap);

            throwClientExistenceExceptionByEmail(clientEmail,false);

            if(!clientEmail.equals(requestClientModel.getEmail())){
                throwClientExistenceExceptionByEmail(requestClientModel.getEmail(),true);
            }
            UpdateResult updateResult = returnUpdatedResultOfUpdateClientByEmail(clientEmail,requestClientModel);
            String responseObject = Constant.UPDATE_PROCESS_MESSAGE.
                    replace("{modifiedCount}", String.valueOf(updateResult.getModifiedCount())).
                    replace("{key and value}", "email "+clientEmail);

            return ResponseModelMapper.buildSuccessfulResponseModel(String.class.getSimpleName(),responseObject);

        } catch(Exception exceptionObject) {
            LOGGER.error("Error updating a client by email, ClientServiceImpl.updateClientByEmail",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }

    @Override
    public ResponseModel updateClientById(String clientId, Map<String, Object> clientModelMap) {
        try {
            ClientModel requestClientModel = ClientMapper.returnClientModelFromMapWithoutId(clientModelMap);

            throwClientExistenceExceptionById(clientId,false);
            ClientModel actualClientModel = returnClientById(clientId);

            if(!actualClientModel.getEmail().equals(requestClientModel.getEmail())){
                throwClientExistenceExceptionByEmail(requestClientModel.getEmail(),true);
            }

            UpdateResult updateResult = returnUpdatedResultOfUpdateClientById(clientId,requestClientModel);
            String responseObject = Constant.UPDATE_PROCESS_MESSAGE.
                    replace("{modifiedCount}", String.valueOf(updateResult.getModifiedCount())).
                    replace("{key and value}", "email "+clientId);

            return ResponseModelMapper.buildSuccessfulResponseModel(String.class.getSimpleName(),responseObject);

        } catch(Exception exceptionObject) {
            LOGGER.error("Error updating a client by id, ClientServiceImpl.updateClientById",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }

    @Override
    public ResponseModel deleteClientByEmail(String clientEmail) {
        try {
            throwClientExistenceExceptionByEmail(clientEmail,false);
            ClientModel deletedClient = returnDeletedClientByEmail(clientEmail);
            String objectType = ClientModel.class.getSimpleName();

            return ResponseModelMapper.buildSuccessfulResponseModel(objectType,deletedClient);
        } catch(Exception exceptionObject) {
            LOGGER.error("Error deleting a client by email, ClientServiceImpl.deleteClientByEmail",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }

    @Override
    public ResponseModel deleteClientById(String clientId) {
        try {
            throwClientExistenceExceptionById(clientId, false);
            ClientModel deletedClient = returnDeletedClientById(clientId);
            String objectType = ClientModel.class.getSimpleName();

            return ResponseModelMapper.buildSuccessfulResponseModel(objectType,deletedClient);
        } catch (Exception exceptionObject) {
            LOGGER.error("Error deleting a client by id, ClientServiceImpl.deleteClientById",
                    exceptionObject.getMessage(), exceptionObject);
            return ResponseModelMapper.buildExceptionResponseModel(exceptionObject);
        }
    }
    private List<ClientModel> returnListOfAllClients() {
        return findClientRepository.findAll();
    }

    private ClientModel returnClientByEmail(String clientEmail) {
        return findClientRepository.findClientByEmail(clientEmail);
    }

    private ClientModel returnClientById(String clientId) {
        return findClientRepository.findClientById(clientId);
    }

    private ClientModel returnInsertedClient(ClientModel requestClientModel) {
        return createClientRepository.insert(requestClientModel);
    }

    private UpdateResult returnUpdatedResultOfUpdateClientByEmail(String clientEmail, ClientModel requestClientModel) {
        return updateClientRepository.updateClientByEmail(clientEmail, requestClientModel);
    }

    private UpdateResult returnUpdatedResultOfUpdateClientById(String clientEmail, ClientModel requestClientModel) {
        return updateClientRepository.updateClientById(clientEmail, requestClientModel);
    }

    private ClientModel returnDeletedClientByEmail(String clientEmail) {
        return deleteClientRepository.deleteClientByEmail(clientEmail);
    }

    private ClientModel returnDeletedClientById(String clientId) {
        return deleteClientRepository.deleteClientById(clientId);
    }

    private void throwClientExistenceExceptionByEmail(
            String clientEmail, boolean throwSwitch) throws Exception {
        if(throwSwitch && findClientRepository.clientExistByEmail(clientEmail)) {
            throw new Exception(Constant.CLIENT_EMAIL_ALREADY_EXIST_MESSAGE);
        }
        else if(!throwSwitch && !findClientRepository.clientExistByEmail(clientEmail)) {
            throw new Exception(Constant.CLIENT_EMAIL_NOT_EXIST_MESSAGE);
        }
    }

    private void throwClientExistenceExceptionById(
            String clientId, boolean throwSwitch) throws Exception {
        if(throwSwitch && findClientRepository.clientExistById(clientId)) {
            throw new Exception(Constant.CLIENT_ID_ALREADY_EXIST_MESSAGE);
        }
        else if(!throwSwitch && !findClientRepository.clientExistById(clientId)) {
            throw new Exception(Constant.CLIENT_ID_NOT_EXIST_MESSAGE);
        }
    }

}
