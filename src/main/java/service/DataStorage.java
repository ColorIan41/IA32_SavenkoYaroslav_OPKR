package service;

import jakarta.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;

public class DataStorage {
    private static final String DATA_FILE = "/WEB-INF/data.ser";
    
    private static Map<String, List<?>> dataMap;

    static {
        dataMap = new HashMap<>();
        dataMap.put("languages", new ArrayList<Language>());
        dataMap.put("words", new ArrayList<Word>());
        dataMap.put("translations", new ArrayList<Translation>());
    }

    public static void loadData(ServletContext context) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(context.getRealPath(DATA_FILE)))) {
            dataMap = (Map<String, List<?>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file not found or error in reading, keep default empty lists
        }
    }

    public static void saveData(ServletContext context) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(context.getRealPath(DATA_FILE)))) {
            oos.writeObject(dataMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Language> getLanguages(ServletContext context) {
        return (List<Language>) dataMap.get("languages");
    }

    public static List<Word> getWords(ServletContext context) {
        return (List<Word>) dataMap.get("words");
    }

    public static List<Translation> getTranslations(ServletContext context) {
        return (List<Translation>) dataMap.get("translations");
    }
}