package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.UdpConfig;
import org.eclipse.californium.elements.exception.ConnectorException;
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

        // Emulate attributes coming from embedded device
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("completeName", "jon doe");
        jsonBody.put("age", "22");
        jsonBody.put("city", "chicago");

        // Send PUT request and receive response (format 0 -> text/plain)
        CoapResponse response = client.put(jsonBody.toString(), 0);

        if (response != null) {
            System.out.println(Utils.prettyPrint(response));
        } else {
            System.out.println("No response received.");
        }

        client.shutdown();
    }
}
