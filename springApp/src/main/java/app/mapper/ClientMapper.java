package app.mapper;

import app.model.ClientModel;
import app.util.Util;

import java.util.Map;

public class ClientMapper {

    public static ClientModel returnClientModelFromMapWithoutId(Map<String, Object> clientModelMap) {
        ClientModel clientModel = new ClientModel();

        clientModel.setEmail(Util.returnMapValueObjectAsString(clientModelMap.get("email")));
        clientModel.setNames(Util.returnMapValueObjectAsString(clientModelMap.get("names")));
        clientModel.setLastNames(Util.returnMapValueObjectAsString(clientModelMap.get("lastNames")));
        clientModel.setType(Util.returnMapValueObjectAsString(clientModelMap.get("type")));
        clientModel.setPhone(Util.returnMapValueObjectAsString(clientModelMap.get("phone")));
        clientModel.setCountry(Util.returnMapValueObjectAsString(clientModelMap.get("country")));

        return clientModel;
    }
}
