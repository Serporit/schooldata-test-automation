package utils;

import bo.InstitutionsCount;

import java.util.HashMap;
import java.util.Map;

public class CountStorage {
    private Map<String, InstitutionsCount> map = new HashMap<>();

    private CountStorage() {
    }

    private static CountStorage instance;

    public static CountStorage getInstance() {
        if (instance == null) {
            instance = new CountStorage();
        }
        return instance;
    }

    public void put(String source, InstitutionsCount institutionsCount) {
        map.put(source, institutionsCount);
    }

    public InstitutionsCount get(String source) {
        return map.get(source);
    }

    public static void clear() {
        if (instance != null) {
            try {
                instance.map.clear();
            } finally {
                instance = null;
            }
        }
    }
}
