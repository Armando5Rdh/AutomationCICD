package Data;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.testng.annotations.Test;

public class DataReader {

    @Test
    public void getJsonDataToMap() throws IOException, ParseException {
        File file = new File("src/test/java//Data/PurchaseOrder.json");
//        Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});

        Object o = new JSONParser().parse(new FileReader(file));

        List<HashMap<String,String>> data = (List<HashMap<String, String>>) o;

        System.out.println(data.get(0).get("product"));


//        https://reflectoring.io/jackson/
//        FileUtils.readFileToString(new File("PurchaseOrder.json"));
    }
}
