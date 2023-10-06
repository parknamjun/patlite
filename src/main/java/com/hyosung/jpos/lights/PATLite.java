package com.hyosung.jpos.lights;


public class PATLite {

    public static void main(String[] args) {
        Control ctr = new Control();

        // Connect to NE-USB
        int ret = ctr.usb_open();
        if(ret < 0 ) {
            System.out.println("device not found");
            return;
        }

        try {
            // Argument check
            String commandId = " ";
            if (args.length > 0) {
                commandId = args[0];
            }

            // Command judgment
            switch (commandId)
            {
                case "1":
                {
                    // Specify the LED color and LED pattern to turn on and turn on the pattern
                    if (args.length >= 3)
                        ctr.set_light((byte)Integer.parseInt(args[1]), (byte)Integer.parseInt(args[2]));
                    break;
                }

                case "2":
                {
                    // Specify the buzzer pattern and make the buzzer sound
                    if (args.length >= 3)
                        ctr.set_buz((byte)Integer.parseInt(args[1]), (byte)Integer.parseInt(args[2]));
                    break;
                }

                case "3":
                {
                    // Change the buzzer volume by specifying the volume
                    if (args.length >= 2)
                        ctr.set_vol((byte)Integer.parseInt(args[1]));
                    break;
                }

                case "4":
                {
                    // Sound the buzzer by specifying the buzzer pattern, number of times, and volume.
                    if (args.length >= 4)
                        ctr.set_buz_ex((byte)Integer.parseInt(args[1]), (byte)Integer.parseInt(args[2]), (byte)Integer.parseInt(args[3]));
                    break;
                }

                case "5":
                {
                    // Change the connection display settings
                    if (args.length >= 2)
                        ctr.set_setting((byte)Integer.parseInt(args[1]));
                    break;
                }

                case "6":
                {
                    // Get touch sensor input status
                    int state = ctr.getTouchSensorState();
                    if(state == 1){
                        System.err.println("touch sensor input = ON");
                    }
                    else if(state == 0){
                        System.err.println("touch sensor input = OFF");
                    }
                    else{
                        System.err.println("USB communication failed");
                    }
                    break;
                }

                case "7":
                {
                    // Turn off the LED and stop the buzzer
                    ctr.reset();
                    break;
                }

            }

        } finally {
            // End processing
            ctr.usb_close();
        }
    }

}

