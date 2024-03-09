package org.example;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.UdpConfig;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class CoapGetClient {

    static {
        CoapConfig.register();
        UdpConfig.register();
    }

    public static void main(String[] args) throws ConnectorException, IOException {

        String uri = "coap://localhost:5683/accessControl"; // URI parameter of the request

        CoapClient client = new CoapClient(uri);

        CoapResponse response = client.put("JSON Containing encrypted attributes", 1);

        if (response != null) {
            System.out.println(response.isSuccess());
            System.out.println(response.getCode());
            System.out.println(response.getOptions());
            System.out.println(response.getResponseText());

        } else {
            System.out.println("No response received.");
        }

        client.shutdown();
    }
}
