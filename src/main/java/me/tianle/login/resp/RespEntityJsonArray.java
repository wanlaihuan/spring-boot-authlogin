package me.tianle.login.resp;

import java.util.ArrayList;
import java.util.HashMap;

public class RespEntityJsonArray {
    static ArrayList<HashMap<String, Object>> resultList = new ArrayList();

    /**
     * 清除结果
     */
    public static void clearResult() {
        resultList.clear();
    }

    /**
     * 获取结果
     * @return
     */
    public static ArrayList<HashMap<String, Object>> setResult(HashMap map) {
        resultList.clear();
        resultList.add(map);
        return resultList;
    }

    /**
     * 获取结果
     * @param key
     * @param value
     * @return
     */
    public static void setResult(String key, Object value) {
        resultList.clear();
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        resultList.add(map);
    }

    /**
     * 获取结果
     * @return
     */
    public static ArrayList<HashMap<String, Object>> optResult() {
        return resultList;
    }

}
