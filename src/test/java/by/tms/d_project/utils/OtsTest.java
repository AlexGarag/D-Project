package by.tms.d_project.utils;

import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.entity.ShaftOts;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class OtsTest {

    @Test
    void makeOts() {
        Ots ots = new Ots();
        String jsonIcOts =
                "{\n" +
                "    \"titlePrinting\": \"34971t\",\n" +
                "    \"typeShaft\": 500,\n" +
                "    \"formsIcOts\": [\n" +
                "        { \n" +
                "            \"titleForm\": \"probe2\",\n" +
                "            \"quantityImprint\": 2,\n" +
                "            \"width\": 500,\n" +
                "            \"rightMargin\": 44,\n" +
                "            \"intervalLabels\": 363\n" +
                "        },\n" +
                "        {\n" +
                "            \"titleForm\": \"probe3\",\n" +
                "            \"quantityImprint\": 2,\n" +
                "            \"width\": 500,\n" +
                "            \"rightMargin\": 44,\n" +
                "            \"intervalLabels\": 363\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ShaftIcOts testIcOts = (ShaftIcOts) new Gson().fromJson(jsonIcOts, Data.class);
        String jsonOts =
                "{\n" +
                        "    \"id\": 0,\n" +
                        "    \"titlePrinting\": \"34971t\",\n" +
                        "    \"typeShaft\": 500,\n" +
                        "    \"formsOts\": [\n" +
                        "        {\n" +
                        "            \"id\": null,\n" +
                        "            \"numberOnShaft\": 2,\n" +
                        "            \"indentationOnShaft\": 179,\n" +
                        "            \"intervalLabels\": 363,\n" +
                        "            \"toothOnShaft\": 0,\n" +
                        "            \"owner\": null\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": null,\n" +
                        "            \"numberOnShaft\": 1,\n" +
                        "            \"indentationOnShaft\": 679,\n" +
                        "            \"intervalLabels\": 363,\n" +
                        "            \"toothOnShaft\": 0,\n" +
                        "            \"owner\": null\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"createdAt\": \"null\"\n" +
                        "}";
        ShaftOts testOts = (ShaftOts) new Gson().fromJson(jsonOts, Data.class);

        ShaftOts result = ots.makeOts(testIcOts);
        assertEquals(testOts, result);
    }
}