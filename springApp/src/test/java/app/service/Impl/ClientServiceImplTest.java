package app.service.Impl;

import app.model.ClientModel;
import app.model.ResponseModel;
import app.repository.CreateClientRepository;
import app.repository.DeleteClientRepository;
import app.repository.FindClientRepository;
import app.repository.UpdateClientRepository;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonValue;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import app.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientServiceImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    ClientServiceImpl clientServiceImpl;

    @Mock
    private FindClientRepository findClientRepository;

    @Mock
    private CreateClientRepository createClientRepository;

    @Mock
    private UpdateClientRepository updateClientRepository;

    @Mock
    private DeleteClientRepository deleteClientRepository;

    @Test
    public void getListOfAllClientsOk() {
        List<ClientModel> listOfAllClients = new ArrayList<ClientModel>();
        Mockito.when(findClientRepository.findAll()).thenReturn(listOfAllClients);
        ResponseModel responseModel = clientServiceImpl.getListOfAllClients();
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void getListOfAllClientsNoOk() {
        Mockito.when(findClientRepository.findAll()).thenThrow(new RuntimeException());
        ResponseModel responseModel = clientServiceImpl.getListOfAllClients();
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
    }

    @Test
    public void getClientByEmailOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(findClientRepository.findClientByEmail(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.getClientByEmail(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void getClientByEmailNoOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(findClientRepository.findClientByEmail(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.getClientByEmail(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_EMAIL_NOT_EXIST_MESSAGE));
    }

    @Test
    public void getClientByIdOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistById(Mockito.anyString())).thenReturn(true);
        Mockito.when(findClientRepository.findClientById(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.getClientById(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void getClientByIdNoOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistById(Mockito.anyString())).thenReturn(false);
        Mockito.when(findClientRepository.findClientById(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.getClientById(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_ID_NOT_EXIST_MESSAGE));
    }

    @Test
    public void createAClientOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(createClientRepository.insert(Mockito.any(ClientModel.class))).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.createAClient(Mockito.anyMap());
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void createAClientNoOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(createClientRepository.insert(Mockito.any(ClientModel.class))).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.createAClient(Mockito.anyMap());
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_EMAIL_ALREADY_EXIST_MESSAGE));
    }

    @Test
    public void updateClientByEmailOk1() {
        String actualClientEmail = "test";
        Map<String, Object> clientMapRequest = new HashMap<>();
        clientMapRequest.put("email","test");
        UpdateResult updateResult = generateAUpdateResult();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(
                updateClientRepository.updateClientByEmail(
                        Mockito.anyString(),
                        Mockito.any(ClientModel.class))
        ).thenReturn(updateResult);
        ResponseModel responseModel = clientServiceImpl.updateClientByEmail(actualClientEmail,clientMapRequest);
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void updateClientByEmailOk2() {
        String actualClientEmail = "test";
        Map<String, Object> clientMapRequest = new HashMap<>();
        clientMapRequest.put("email","test2");
        UpdateResult updateResult = generateAUpdateResult();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString()))
                .thenReturn(true)
                .thenReturn(false);
        Mockito.when(
                updateClientRepository.updateClientByEmail(
                        Mockito.anyString(),
                        Mockito.any(ClientModel.class))
        ).thenReturn(updateResult);
        ResponseModel responseModel = clientServiceImpl.updateClientByEmail(actualClientEmail,clientMapRequest);
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void updateClientByEmailNoOk1() {
        String actualClientEmail = "test";
        Map<String, Object> clientMapRequest = new HashMap<>();
        clientMapRequest.put("email","test");
        UpdateResult updateResult = generateAUpdateResult();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(
                updateClientRepository.updateClientByEmail(
                        Mockito.anyString(),
                        Mockito.any(ClientModel.class))
        ).thenReturn(updateResult);
        ResponseModel responseModel = clientServiceImpl.updateClientByEmail(actualClientEmail,clientMapRequest);
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_EMAIL_NOT_EXIST_MESSAGE));
    }

    @Test
    public void updateClientByEmailNoOk2() {
        String actualClientEmail = "test";
        Map<String, Object> clientMapRequest = new HashMap<>();
        clientMapRequest.put("email","test2");
        UpdateResult updateResult = generateAUpdateResult();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString()))
                .thenReturn(true)
                .thenReturn(true);
        Mockito.when(
                updateClientRepository.updateClientByEmail(
                        Mockito.anyString(),
                        Mockito.any(ClientModel.class))
        ).thenReturn(updateResult);
        ResponseModel responseModel = clientServiceImpl.updateClientByEmail(actualClientEmail,clientMapRequest);
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_EMAIL_ALREADY_EXIST_MESSAGE));
    }

    @Test
    public void updateClientByIdOk() {
        String actualClientId = "test";
        Map<String, Object> clientMapRequest = new HashMap<>();
        clientMapRequest.put("id","test");
        UpdateResult updateResult = generateAUpdateResult();
        Mockito.when(findClientRepository.clientExistById(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(
                updateClientRepository.updateClientById(
                        Mockito.anyString(),
                        Mockito.any(ClientModel.class))
        ).thenReturn(updateResult);
        ResponseModel responseModel = clientServiceImpl.updateClientById(actualClientId,clientMapRequest);
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void updateClientByIdNoOk() {
        String actualClientId = "test";
        Map<String, Object> clientMapRequest = new HashMap<>();
        UpdateResult updateResult = generateAUpdateResult();
        Mockito.when(findClientRepository.clientExistById(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(
                updateClientRepository.updateClientById(
                        Mockito.anyString(),
                        Mockito.any(ClientModel.class))
        ).thenReturn(updateResult);
        ResponseModel responseModel = clientServiceImpl.updateClientById(actualClientId,clientMapRequest);
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_ID_NOT_EXIST_MESSAGE));
    }

    @Test
    public void deleteClientByEmailOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(deleteClientRepository.deleteClientByEmail(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.deleteClientByEmail(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void deleteClientByEmailNoOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(deleteClientRepository.deleteClientByEmail(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.deleteClientByEmail(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_EMAIL_NOT_EXIST_MESSAGE));
    }

    @Test
    public void deleteClientByIdOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistById(Mockito.anyString())).thenReturn(true);
        Mockito.when(deleteClientRepository.deleteClientById(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.deleteClientById(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.SUCCESSFUL_PROCESS_CODE);
    }

    @Test
    public void deleteClientByIdNoOk() {
        ClientModel client = new ClientModel();
        Mockito.when(findClientRepository.clientExistById(Mockito.anyString())).thenReturn(false);
        Mockito.when(deleteClientRepository.deleteClientById(Mockito.anyString())).thenReturn(client);
        ResponseModel responseModel = clientServiceImpl.deleteClientById(Mockito.anyString());
        Assert.assertEquals(responseModel.getCode(), Constant.ERROR_CODE);
        Assert.assertTrue(responseModel.getException().contains(Constant.CLIENT_ID_NOT_EXIST_MESSAGE));
    }

    private UpdateResult generateAUpdateResult() {
        UpdateResult updateResult = new UpdateResult() {
            @Override
            public boolean wasAcknowledged() {
                return false;
            }

            @Override
            public long getMatchedCount() {
                return 0;
            }

            @Override
            public long getModifiedCount() {
                return 0;
            }

            @Override
            public BsonValue getUpsertedId() {
                return null;
            }
        };
        return updateResult;
    }

}
