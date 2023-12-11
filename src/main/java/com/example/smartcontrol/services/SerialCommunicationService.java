package com.example.smartcontrol.services;

import org.springframework.stereotype.Service;
import com.fazecast.jSerialComm.*;

@Service
public class SerialCommunicationService {
    public void sendMessageToSerialPort(String portName, String message) {
        SerialPort port = SerialPort.getCommPort("COM3"); // Adjust the port name as needed
        port.openPort();
        port.setBaudRate(9600); // Set the appropriate baud rate

        byte[] bytes = message.getBytes();
        port.writeBytes(bytes, bytes.length);

        port.closePort(); // Close the port when done
    }

}
