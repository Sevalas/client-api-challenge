package app.repository;

import app.model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FindClientRepository extends MongoRepository<ClientModel, String> {

    List<ClientModel> findAll();

    @Query("{email:'?0'}")
    ClientModel findClientByEmail(String clientEmail);

    @Query("{id:'?0'}")
    ClientModel findClientById(String clientId);

    @Query(value = "{id : ?0}", exists = true)
    boolean clientExistById(String clientEmail);

    @Query(value = "{email : ?0}", exists = true)
    boolean clientExistByEmail(String clientEmail);

}
