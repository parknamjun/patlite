package com.hyosung.jpos.lights;

/**
 * PATLITE NE-USB LED control sample
 * @Author:
 * @Date: 2023.10.04
 */
public class Main {
    public static void main(String[] args) {
        Control control = new Control();

        //LED color (off: 0, red: 1, green: 2, yellow: 3, blue: 4, purple: 5, sky blue: 6, white: 7, maintain the current settings: 0x08 to 0x0F)
        byte lightColor[] = new byte[7];
        for(int i = 0; i < 7;)
            lightColor[i] = (byte)++i;

        //LED pattern (off: 0x00, on: 0x01, LED pattern 1: 0x02, LED pattern 2: 0x03, LED pattern 3: 0x04, LED pattern 4: 0x05, LED pattern 5: 0x06, LED pattern 6: 0x07, current settings Maintain: 0x08-0x0F)
        byte lightPattern[] = new byte[6];
        for(int i = 0; i < 6;)
            lightPattern[i] = (byte)++i;

        try {
            int ret = control.usb_open();
            if(ret < 0 ) {
                System.out.println("device not found");
                return;
            }
            for(int j = 0; j < lightPattern.length; j++) {
                for (int i = 0; i < lightColor.length; i++) {
                    System.out.printf("lightColor %d, lightPattern %d\n", i, j);
                    control.set_light(lightColor[i], lightPattern[j]);
                    Thread.sleep(2000);
                }
            }
            //set_buzzer(0x01, 0x01); //buzzer on
            control.reset();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            control.usb_close();
        }
    }

}

