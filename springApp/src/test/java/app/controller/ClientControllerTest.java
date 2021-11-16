package app.controller;

import app.model.ResponseModel;
import app.service.ClientService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ClientControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Test
    public void getListOfAllClients() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.getListOfAllClients()).thenReturn(responseModel);
        clientController.getListOfAllClients();
    }

    @Test
    public void getClientByEmail() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.getClientByEmail(Mockito.anyString())).thenReturn(responseModel);
        clientController.getClientByEmail(Mockito.anyString());
    }

    @Test
    public void getClientById() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.getClientById(Mockito.anyString())).thenReturn(responseModel);
        clientController.getClientById(Mockito.anyString());
    }

    @Test
    public void createAClient() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.createAClient(Mockito.anyMap())).thenReturn(responseModel);
        clientController.createAClient(Mockito.anyMap());
    }

    @Test
    public void updateClientByEmail() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.updateClientByEmail(Mockito.anyString(),Mockito.anyMap())).thenReturn(responseModel);
        clientController.updateClientByEmail(Mockito.anyMap(),Mockito.anyString());
    }

    @Test
    public void updateClientById() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.updateClientById(Mockito.anyString(),Mockito.anyMap())).thenReturn(responseModel);
        clientController.updateClientById(Mockito.anyMap(),Mockito.anyString());
    }

    @Test
    public void deleteClientByEmail() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.deleteClientByEmail(Mockito.anyString())).thenReturn(responseModel);
        clientController.deleteClientByEmail(Mockito.anyString());
    }

    @Test
    public void deleteClientById() {
        ResponseModel responseModel = new ResponseModel();
        Mockito.when(clientService.deleteClientById(Mockito.anyString())).thenReturn(responseModel);
        clientController.deleteClientById(Mockito.anyString());
    }
}