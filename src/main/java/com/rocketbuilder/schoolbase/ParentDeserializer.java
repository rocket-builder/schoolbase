package com.rocketbuilder.schoolbase;

import com.google.gson.*;
import com.rocketbuilder.schoolbase.models.Parent;

import java.lang.reflect.Type;

public class ParentDeserializer implements JsonDeserializer<Parent> {

    @Override
    public Parent deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Parent parent = new Parent();
        parent.setFirstname(jsonObject.get("firstname").getAsString());
        parent.setSurname(jsonObject.get("surname").getAsString());
        parent.setMiddlename(jsonObject.get("middlename").getAsString());
        parent.setJob(jsonObject.get("job").getAsString());
        parent.setNumber(jsonObject.get("number").getAsString());

        return parent;
    }
}
