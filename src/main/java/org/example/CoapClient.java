package org.example;

import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.UdpConfig;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class CoapClient {

    static {
        CoapConfig.register();
        UdpConfig.register();
    }

    public static void main(String[] args) throws ConnectorException, IOException {

        String uri = "coap://localhost:5683/accessControl"; // URI parameter of the request

        org.eclipse.californium.core.CoapClient client = new org.eclipse.californium.core.CoapClient(uri);

        // Main JSON Object
        JSONObject rootObject = new JSONObject();

        // userAttributes Object
        JSONObject userAttributes = new JSONObject();
        userAttributes.put("id", "10");
        userAttributes.put("role", "Manager");
        userAttributes.put("department", "HR");
        userAttributes.put("yearsOfExperience", 6);
        userAttributes.put("clearanceLevel", "Level 2");
        userAttributes.put("employmentStatus", "Full-time");

        // timeSchedule Object
        JSONObject timeSchedule = new JSONObject();
        timeSchedule.put("startTime", "00:00:00");
        timeSchedule.put("endTime", "17:00:00");

        // daysOfWeek Array
        JSONArray daysOfWeek = new JSONArray();
        daysOfWeek.put("Monday");
        daysOfWeek.put("Thursday");
        daysOfWeek.put("Wednesday");
        daysOfWeek.put("Sunday");

        timeSchedule.put("daysOfWeek", daysOfWeek);
        userAttributes.put("timeSchedule", timeSchedule);

        rootObject.put("userAttributes", userAttributes);

        // accessPointAttributes Object
        JSONObject accessPointAttributes = new JSONObject();
        accessPointAttributes.put("id", "1");
        accessPointAttributes.put("location", "C213");
        accessPointAttributes.put("isTampered", false);
        accessPointAttributes.put("occupancyLevel", 10);

        rootObject.put("accessPointAttributes", accessPointAttributes);

        // Print the resulting JSON
        System.out.println(rootObject.toString(4)); // Pretty-print with 4 spaces indent

        // Send PUT request and receive response (format 0 -> text/plain)
        CoapResponse response = client.put(rootObject.toString(), 0);

        if (response != null) {
            System.out.println(Utils.prettyPrint(response));
        } else {
            System.out.println("No response received.");
        }

        client.shutdown();
    }
}
