package me.tianle.login.resp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 配合 RespEntity 的第二个参数生成最终响应的 json 数据格式
 * Map: json 格式
 * List<Map> : json 数组格式
 */
public class EntityJsonValue {
    private static HashMap<String, Object> hashMap = new HashMap<>();

    private static class Single {
        public static EntityJsonValue INSTANCE = new EntityJsonValue();
    }

    public static EntityJsonValue with() {
        return Single.INSTANCE;
    }

    public boolean hasValues() {
        if (hashMap.size() > 0) {
            return true;
        }

        return false;
    }

    public EntityJsonValue put(String key, Object value) {
        hashMap.put(key, value);
        return this;
    }

    /**
     * results 结果中存储的是 json 数组格式数据
     *
     * @return
     */
    public ArrayList<HashMap<String, Object>> toJsonArrayValue() {
        ArrayList<HashMap<String, Object>> resultList = new ArrayList<>();
        resultList.add((HashMap<String, Object>) hashMap.clone());
        hashMap.clear();
        return resultList;
    }

    /**
     * results 结果中存储的是 json 格式数据
     *
     * @return
     */
    public HashMap<String, Object> toJsonValue() {
        HashMap<String, Object> map = (HashMap<String, Object>) hashMap.clone();
        hashMap.clear();
        return map;
    }
}
