package com.rocketbuilder.schoolbase;

import com.google.gson.*;
import com.rocketbuilder.schoolbase.models.Parent;
import com.rocketbuilder.schoolbase.models.Student;

import java.lang.reflect.Type;

public class StudentDetailsSerializer implements JsonSerializer<Student> {

    @Override
    public JsonElement serialize(Student student, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("avatarPath", student.getAvatarPath());
        result.addProperty("age", student.getAge());
        result.addProperty("firstname", student.getFirstname());
        result.addProperty("surname", student.getSurname());
        result.addProperty("middlename", student.getMiddlename());

        result.addProperty("group_title", student.getGroups().getTitle());
        result.addProperty("hobby", student.getHobby());
        result.addProperty("familyChildCount", student.getFamilyChildCount());
        result.addProperty("diseases", student.getDiseases());
        result.addProperty("healthgroup", student.getHealthGroup().toString());
        result.addProperty("gymgroup", student.getGymGroup().toString());
        result.addProperty("birthday", student.getStringDate());

        JsonArray parents = new JsonArray();

        for(Parent parent : student.getParents()) {
            parents.add(
                    parent != null ?
                            context.serialize(parent) : null
            );
        }
        result.add("parents", parents);

        return result;
    }
}
