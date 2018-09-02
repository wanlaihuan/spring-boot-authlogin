package me.tianle.login.resp;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityJsonValue {
    private static HashMap<String, Object> hashMap = new HashMap<>();

    private static class Single {
        public static EntityJsonValue INSTANCE = new EntityJsonValue();
    }

    public static EntityJsonValue with() {
        return Single.INSTANCE;
    }

    public boolean hasValues() {
        if(hashMap.size() > 0) {
            return true;
        }

        return false;
    }
    public EntityJsonValue put(String key, Object value) {
        hashMap.put(key, value);
        return this;
    }

    public ArrayList<HashMap<String, Object>> toJsonArrayValue() {
        ArrayList<HashMap<String, Object>> resultList = new ArrayList<>();
        resultList.add((HashMap<String, Object>) hashMap.clone());
        hashMap.clear();
        return resultList;
    }

    public HashMap<String, Object> toJsonValue() {
        HashMap<String, Object> map = (HashMap<String, Object>) hashMap.clone();
        hashMap.clear();
        return map;
    }
}
