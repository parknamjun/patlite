package com.hyosung.serial.scanner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import gnu.io.NRSerialPort;

/**
 * Created by Administrator on 2016-09-21.
 */
public class NRSerialPortReadTest {
    public static void main(String[] args) {
    /*
        for(String s:NRSerialPort.getAvailableSerialPorts()){
            System.out.println("Availible port: "+s);
            port = s;
    }*/

        String port = "COM9";
        int baudRate = 9600;
        NRSerialPort serial = new NRSerialPort(port, baudRate);
        serial.connect();

        DataInputStream ins = new DataInputStream(serial.getInputStream());
        //DataOutputStream outs = new DataOutputStream(serial.getOutputStream());
        byte[] buffer = new byte[128];
        byte[] received = new byte[128];

        try{
            //while(ins.available()==0 && !Thread.interrupted());// wait for a byte
            while(!Thread.interrupted()) {// read all bytes
                if(ins.available()>0) {
                    int length = ins.read(buffer);
                    if(length > 0) {
                        received = Arrays.copyOf(buffer, length - 1); //\r removed
                    }
                    System.out.println("length: " + length);
                    System.out.println("received: " + new String(received, StandardCharsets.UTF_8));

                }
                Thread.sleep(10);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try{
                ins.close();
                //outs.close();
            } catch(Exception ex){
                ex.printStackTrace();
            }
            serial.disconnect();
        }
    }
}
