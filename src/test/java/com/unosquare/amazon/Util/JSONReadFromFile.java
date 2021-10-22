package com.unosquare.amazon.Util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Class helper to read a Json file
 */
public class JSONReadFromFile {

    /**
     * Method to get a key from the json file
     * @param file file name
     * @param key key to search
     * @return key value
     */
    public static String readFileJson (String file, String key) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/test/resources/" + file + ".json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            return jsonObject.get(key).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
