package com.bivashy.learn.arduino;

import com.bivashy.learn.arduino.model.ComPort;
import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class LEDController {

    public static final int ENABLE_COMMAND = 49; // 1 in ASCII
    public static final int DISABLE_COMMAND = 48; // 0 in ASCII
    private final int baudrate;
    private SerialPort comPort;

    public LEDController(int baudrate) {
        this.baudrate = baudrate;
    }

    public boolean start(SerialPort port) {
        requireNonNull(port, "port");
        comPort = port;
        comPort.setComPortParameters(baudrate, 8, 1, 0);
        return comPort.openPort();
    }

    public void enable() {
        writeIntQuiet(ENABLE_COMMAND);
    }

    public void disable() {
        writeIntQuiet(DISABLE_COMMAND);
    }

    public void flush() throws IOException {
        comPort.getOutputStream().flush();
    }

    public void writeIntQuiet(int data) {
        try {
            comPort.getOutputStream().write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        comPort.closePort();
        comPort = null;
    }

    public boolean isStarted() {
        return comPort != null && comPort.isOpen();
    }

    public List<ComPort> availablePorts() {
        return Arrays.stream(SerialPort.getCommPorts())
                .map(port -> new ComPort(port))
                .toList();
    }

}
