package app.repository.impl;

import app.repository.DeleteClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import app.model.ClientModel;

@Component
public class DeleteClientRepositoryImpl implements DeleteClientRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public ClientModel deleteClientById(String clientId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(clientId));
        return mongoTemplate.findAndRemove(query, ClientModel.class);
    }

    @Override
    public ClientModel deleteClientByEmail(String clientEmail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(clientEmail));
        return mongoTemplate.findAndRemove(query, ClientModel.class);
    }
}
