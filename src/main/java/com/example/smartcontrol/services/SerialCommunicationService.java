package com.example.smartcontrol.services;

import org.springframework.stereotype.Service;
import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SerialCommunicationService {
    public void sendMessageToEsp(String dataToSend) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://10.0.96.127/receiveData"))
                .POST(HttpRequest.BodyPublishers.ofString(dataToSend))
                .build();
//192.168.0.11
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    public void sendMessageToSerialPort(String message) {
        try {
            SerialPort port = SerialPort.getCommPort("COM5"); // Adjust the port name as needed
            port.setBaudRate(9600); // Set the appropriate baud rate
            if (port.openPort()) {
                Thread.sleep(2000);
                byte[] bytes = message.getBytes();
                port.writeBytes(bytes, bytes.length);
                port.getOutputStream().flush();
                Thread.sleep(2000);
                port.closePort(); // Close the port when done
            } else {
                System.out.println("Port could not be opened.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void testOnOff(String args) {
        SerialPort comPort = SerialPort.getCommPort("COM5"); // Substitua COM3 pela porta do seu Arduino
        comPort.setBaudRate(9600);

        if (comPort.openPort()) {
            try {
                Thread.sleep(2000); // Espera 2 segundos
                comPort.getOutputStream().write(args.getBytes()); // Envia '1' para acender o LED
                comPort.getOutputStream().flush();
                Thread.sleep(2000);
                comPort.closePort();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                comPort.closePort();
            }
        } else {
            System.out.println("Não foi possível abrir a porta.");
        }
    }
}
