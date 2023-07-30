import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException, ParseException {
        Task3 task3 = new Task3();
        List<Test> tests = task3.getTestsFromJson(new File(args[0]));
        Map<Long, String> valuesMap = task3.getValuesMap(new File(args[1]));
        task3.setValue(tests, valuesMap);
        task3.buildReportJSON(tests);
    }

    public void setValue(List<Test> tests, Map<Long, String> valuesMap) {
        String value;
        for (Test test : tests) {
            value = valuesMap.get(test.getId());
            if (value != null) {
                test.setValue(value);
            } else {
                test.setValue("");
            }
            if (test.getValues() != null) {
                setValue(test.getValues(), valuesMap);
            }
        }
    }

    public Map<Long, String> getValuesMap(File file) throws IOException, ParseException {
        Map<Long, String> valuesMap = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            JSONObject jsonObject = (JSONObject) (new JSONParser().parse(bufferedReader));
            JSONArray jsonArray = (JSONArray) jsonObject.get("values");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonValue = (JSONObject) iterator.next();
                valuesMap.put((Long) jsonValue.get("id"), (String) jsonValue.get("value"));
            }
        }
        return valuesMap;
    }

    public List<Test> getTestsFromJson(File file) throws IOException, ParseException {
        List<Test> tests = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            JSONObject jsonObject = (JSONObject) (new JSONParser().parse(bufferedReader));
            JSONArray jsonArray = (JSONArray) jsonObject.get("tests");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonTest = (JSONObject) iterator.next();
                tests.add(getTestFromJsonObject(jsonTest));
            }
        }
        return tests;
    }

    public Test getTestFromJsonObject(JSONObject jsonObject) {
        long id = (Long) jsonObject.get("id");
        String title = (String) jsonObject.get("title");
        String value = (String) jsonObject.get("value");
        List<Test> values;
        JSONArray jsonArray = (JSONArray) jsonObject.get("values");
        if (jsonArray == null) {
            values = null;
        } else {
            values = new ArrayList<>();
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject1 = (JSONObject) iterator.next();
                values.add(getTestFromJsonObject(jsonObject1));
            }
        }
        return new Test(id, title, value, values);
    }

    public void buildReportJSON(List<Test> tests) throws IOException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Test test : tests) {
            jsonArray.add(objectToJsonObject(test));
        }
        jsonObject.put("report", jsonArray);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("report.json")))) {
            jsonObject.writeJSONString(bufferedWriter);
        }
    }

    private JSONObject objectToJsonObject(Test test) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", test.getId());
        jsonObject.put("title", test.getTitle());
        if (test.getValue() != "") {
            jsonObject.put("value", test.getValue());
        }
        if (test.getValues() != null) {
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("values", jsonArray);
            for (Test test1: test.getValues()) {
                jsonArray.add(objectToJsonObject(test1));
            }
        }
        return jsonObject;
    }

}