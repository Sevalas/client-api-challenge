package app.service;

import app.model.ResponseModel;

import java.util.Map;

public interface ClientService {

    ResponseModel getListOfAllClients();

    ResponseModel getClientByEmail(String clientEmail);

    ResponseModel getClientById(String clientId);

    ResponseModel createAClient(Map<String, Object> clientModelMap);

    ResponseModel updateClientByEmail(String clientEmail, Map<String, Object> clientModelMap);

    ResponseModel updateClientById(String clientId, Map<String, Object> clientModelMap);

    ResponseModel deleteClientByEmail(String clientEmail);

    ResponseModel deleteClientById(String clientId);
}
