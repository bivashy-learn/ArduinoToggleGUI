package com.bivashy.learn.arduino.model;

import com.fazecast.jSerialComm.SerialPort;

public class ComPort {

    private final SerialPort port;

    public ComPort(SerialPort port) {
        this.port = port;
    }

    public SerialPort getPort() {
        return port;
    }

    @Override
    public String toString() {
        return port.getDescriptivePortName() + " (" + port.getSystemPortName() + ")";
    }

}
