package app.repository;

import app.model.ClientModel;

public interface DeleteClientRepository {

    ClientModel deleteClientById(String clientId);
    ClientModel deleteClientByEmail(String clientEmail);
}
