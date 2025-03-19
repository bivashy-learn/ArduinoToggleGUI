/*
 * Created by JFormDesigner on Wed Mar 19 11:13:09 QYZT 2025
 */
package com.bivashy.learn.arduino;

import com.bivashy.learn.arduino.model.ComPort;
import javax.swing.*;
import net.miginfocom.swing.*;

import java.io.IOException;

/**
 * @author kreker
 */
public class LEDFrame extends JFrame {

    private final LEDController controller;

    public LEDFrame(LEDController controller) {
        this.controller = controller;
        initComponents();
        controller.availablePorts().forEach(comboBox1::addItem);
        comboBox1.setSelectedItem(null);
        comboBox1.addActionListener(actionEvent -> {
            if (controller.isStarted())
                controller.close();
            if (!enableSelectedPort()) {
                JOptionPane.showMessageDialog(this, "Не удалось открыть порт, выберите порт или перезагрузите компьютер!");
            }
        });
        toggleButton1.addActionListener(actionEvent -> {
            if (!controller.isStarted())
                if (!enableSelectedPort()) {
                    JOptionPane.showMessageDialog(this, "Не удалось открыть порт или перезагрузите компьютер!");
                }
            if (toggleButton1.isSelected())
                controller.enable();
            else
                controller.disable();
            try {
                controller.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean enableSelectedPort() {
        ComPort selectedPort = (ComPort) comboBox1.getSelectedItem();
        if (selectedPort != null)
            return controller.start(selectedPort.getPort());
        return false;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        comboBox1 = new JComboBox();
        toggleButton1 = new JToggleButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,align center center",
            // columns
            "[fill]",
            // rows
            "[]" +
            "[]"));
        contentPane.add(comboBox1, "cell 0 0");

        //---- toggleButton1 ----
        toggleButton1.setText("LED");
        contentPane.add(toggleButton1, "cell 0 1,align center center,grow 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox comboBox1;
    private JToggleButton toggleButton1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
