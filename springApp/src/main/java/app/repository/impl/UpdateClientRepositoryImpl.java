package app.repository.impl;

import app.model.ClientModel;
import app.repository.UpdateClientRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class UpdateClientRepositoryImpl implements UpdateClientRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UpdateResult updateClientById(String clientId, ClientModel clientModel) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(clientId));
        Update update = new Update();
        getUpdatedClientModel(clientModel, update);
        return mongoTemplate.updateFirst(query, update, ClientModel.class);
    }

    @Override
    public UpdateResult updateClientByEmail(String clientEmail, ClientModel clientModel) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(clientEmail));
        Update update = new Update();
        getUpdatedClientModel(clientModel, update);
        return mongoTemplate.updateFirst(query, update, ClientModel.class);
    }

    private Update getUpdatedClientModel(ClientModel clientModel, Update update) {
        update.set("email", clientModel.getEmail());
        update.set("names", clientModel.getNames());
        update.set("lastNames", clientModel.getLastNames());
        update.set("type", clientModel.getType());
        update.set("phone", clientModel.getPhone());
        update.set("country", clientModel.getCountry());

        return update;
    }
}
