package app.controller;

import app.model.ClientModel;
import app.model.ResponseModel;
import app.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/get-list-of-all-clients", method = RequestMethod.GET)
    public ResponseModel getListOfAllClients() {
        LOGGER.info("Executing ClientController.getListOfAllClients");
        return clientService.getListOfAllClients();
    }

    @RequestMapping(value = "/get-client-by-email/{email}", method = RequestMethod.GET)
    public ResponseModel getClientByEmail(@RequestParam(value ="email",required=true) String clientEmail) {
        LOGGER.info("Executing ClientController.getClientByEmail");
        return clientService.getClientByEmail(clientEmail);
    }

    @RequestMapping(value = "/get-client-by-id/{id}", method = RequestMethod.GET)
    public ResponseModel getClientById(@RequestParam(value ="id",required=true) String id) {
        LOGGER.info("Executing ClientController.getClientById");
        return clientService.getClientById(id);
    }

    @RequestMapping(value = "/create-client", method = RequestMethod.POST)
    public ResponseModel createAClient(@RequestBody(required=true) Map<String, Object> clientModelMap) {
        LOGGER.info("Executing ClientController.createAClient");
        return clientService.createAClient(clientModelMap);
    }

    @RequestMapping(value = "/update-client-by-email/{email}", method = RequestMethod.PUT)
    public ResponseModel updateClientByEmail(@RequestBody(required=true) Map<String, Object> clientModelMap,
                                   @RequestParam(value = "email",required=true) String email) {
        LOGGER.info("Executing ClientController.updateClientByEmail");
        return clientService.updateClientByEmail(email, clientModelMap);
    }

    @RequestMapping(value = "/update-client-by-id/{id}", method = RequestMethod.PUT)
    public ResponseModel updateClientById(@RequestBody(required=true) Map<String, Object> clientModelMap,
                                       @RequestParam(value = "id",required=true) String id) {
        LOGGER.info("Executing ClientController.updateClientById");
        return clientService.updateClientById(id, clientModelMap);
    }

    @RequestMapping(value = "/delete-client-by-email/{email}", method = RequestMethod.DELETE)
    public ResponseModel deleteClientByEmail(@RequestParam(value = "email",required=true) String email) {
        LOGGER.info("Executing ClientController.deleteClientByEmail");
        return clientService.deleteClientByEmail(email);
    }

    @RequestMapping(value = "/delete-client-by-id/{id}", method = RequestMethod.DELETE)
    public ResponseModel deleteClientById(@RequestParam(value = "id",required=true) String id) {
        LOGGER.info("Executing ClientController.deleteClientById");
        return clientService.deleteClientById(id);
    }

}