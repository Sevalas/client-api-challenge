package app.repository;

import app.model.ClientModel;
import com.mongodb.client.result.UpdateResult;

public interface UpdateClientRepository {

    UpdateResult updateClientById(String clientId, ClientModel clientModel);

    UpdateResult updateClientByEmail(String clientEmail, ClientModel clientModel);

}
