package com.rocketbuilder.schoolbase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rocketbuilder.schoolbase.models.Student;

import java.lang.reflect.Type;

public class StudentSerializer implements JsonSerializer<Student> {

    @Override
    public JsonElement serialize(Student student, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();
        result.addProperty("firstname", student.getFirstname());
        result.addProperty("surname", student.getSurname());
        result.addProperty("middlename", student.getMiddlename());

        result.addProperty("group_title", student.getGroups().getTitle());
        result.addProperty("hobby", student.getHobby());
        result.addProperty("familyChildCount", student.getFamilyChildCount());
        result.addProperty("diseases", student.getDiseases());
        result.addProperty("healthgroup", student.getHealthGroup().toString());
        result.addProperty("gymgroup", student.getGymGroup().toString());
        result.addProperty("birthday", student.getInputDate());

        return result;
    }
}
