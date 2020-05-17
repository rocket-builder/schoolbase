package com.rocketbuilder.schoolbase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rocketbuilder.schoolbase.models.Student;
import com.rocketbuilder.schoolbase.models.Teacher;

import java.lang.reflect.Type;

public class TeacherSerializer  implements JsonSerializer<Teacher> {
    @Override
    public JsonElement serialize(Teacher teacher, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();

        result.addProperty("firstname", teacher.getFirstname());
        result.addProperty("surname", teacher.getSurname());
        result.addProperty("middlename", teacher.getMiddlename());
        result.addProperty("number", teacher.getNumber());
        result.addProperty("grouptitle", teacher.getGroups().getTitle());

        return result;
    }
}
