package com.mhchoudh.appeal.repositories;

import java.util.HashMap;
import java.util.Map.Entry;

import com.mhchoudh.appeal.model.Identifier;
import com.mhchoudh.appeal.model.AppealFormat;

public class AppealFormatterRepository {

    private static final AppealFormatterRepository theRepository = new AppealFormatterRepository();
    private HashMap<String, AppealFormat> backingStore = new HashMap<String, AppealFormat>(); // Default implementation, not suitable for production!

    public static AppealFormatterRepository current() {
        return theRepository;
    }
    
    private AppealFormatterRepository(){}
    
    public AppealFormat get(Identifier identifier) {
        return backingStore.get(identifier.toString());
    }
    
    public AppealFormat take(Identifier identifier) {
        AppealFormat send = backingStore.get(identifier.toString());
        remove(identifier);
        return send;
    }

    public Identifier store(AppealFormat send) {
        Identifier id = new Identifier();
        backingStore.put(id.toString(), send);
        return id;
    }
    
    public void store(Identifier formatIdentifier, AppealFormat send) {
        backingStore.put(formatIdentifier.toString(), send);
    }

    public boolean has(Identifier identifier) {
        boolean result =  backingStore.containsKey(identifier.toString());
        return result;
    }

    public void remove(Identifier identifier) {
        backingStore.remove(identifier.toString());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, AppealFormat> entry : backingStore.entrySet()) {
            sb.append(entry.getKey());
            sb.append("\t:\t");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    public synchronized void clear() {
        backingStore = new HashMap<String, AppealFormat>();
    }
}
