package app.repository;

import app.model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreateClientRepository extends MongoRepository<ClientModel, String> {

    ClientModel insert(ClientModel clientModel);
}
