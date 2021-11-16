package app.repository.impl;

import app.model.ClientModel;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class DeleteClientRepositoryImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    DeleteClientRepositoryImpl deleteClientRepositoryImpl;

    @Mock
    MongoTemplate mongoTemplate;

    @Test
    public void deleteClientById() {
        Mockito.when(mongoTemplate.findAndRemove(
                Mockito.any(Query.class),
                Mockito.any(Class.class))
        ).thenReturn(Mockito.any(ClientModel.class));

        deleteClientRepositoryImpl.deleteClientById(Mockito.anyString());
    }

    @Test
    public void deleteClientByEmail() {
        Mockito.when(mongoTemplate.findAndRemove(
                Mockito.any(Query.class),
                Mockito.any(Class.class))
        ).thenReturn(Mockito.any(ClientModel.class));

        deleteClientRepositoryImpl.deleteClientByEmail(Mockito.anyString());
    }

}
