package app.repository.impl;

import app.model.ClientModel;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonValue;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class UpdateClientRepositoryImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    UpdateClientRepositoryImpl updateClientRepositoryImpl;

    @Mock
    MongoTemplate mongoTemplate;

    @Test
    public void updateClientById() {
        ClientModel clientModel = new ClientModel();
        Mockito.when(mongoTemplate.updateFirst(
                new Query(),
                new Update(),
                ClientModel.class)
        ).thenReturn(generateAUpdateResult());

        updateClientRepositoryImpl.updateClientById("test",clientModel);
    }

    @Test
    public void updateClientByEmail() {
        ClientModel clientModel = new ClientModel();
        Mockito.when(mongoTemplate.updateFirst(
                new Query(),
                new Update(),
                ClientModel.class)
        ).thenReturn(generateAUpdateResult());

        updateClientRepositoryImpl.updateClientByEmail("test",clientModel);
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
