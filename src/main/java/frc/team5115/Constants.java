package frc.team5115;

public class Constants{

    public static final boolean MECANUM = true; 
  

    //motor ids
    public static final byte FRONT_LEFT_MOTOR_ID = 1;
    public static final byte FRONT_RIGHT_MOTOR_ID = 2;
    public static final byte BACK_LEFT_MOTOR_ID = 3;
    public static final byte BACK_RIGHT_MOTOR_ID = 4;

    public static final int INTAKE_MOTOR_ID = 5;
    public static final int FEEDER_MOTOR_ID = 6; 
    public static final int LEFT_SHOOTER_MOTOR_ID = 7;
    public static final int RIGHT_SHOOTER_MOTOR_ID = 8; 
    public static final int LEFT_CLIMBER_MOTOR_ID = 9;
    public static final int RIGHT_CLIMBER_MOTOR_ID = 10;

    public static final byte LIMELIGHT_BUTTON_ID = 3;
    public static final double ENCODER_CALIBRATION = (63.837/4104.5);

    //X-Box
    public static int JOY_Y_AXIS_ID = 1;
    public static int JOY_X_AXIS_ID = 0;
    public static int JOY_Z_AXIS_ID = 4; 
    public static int INTAKE_BUTTON_ID = 5;
    public static int SHOOTER_BUTTON_ID = 6;
    public static int LEFT_CLIMBER_UP_BUTTON_ID = 1;
    public static int RIGHT_CLIMBER_UP_BUTTON_ID = 2;
    public static int LEFT_CLIMBER_DOWN_BUTTON_ID = 3;
    public static int RIGHT_CLIMBER_DOWN_BUTTON_ID = 4;

    public static int GYRO_RESET_BUTTON_ID = 7;

    public static byte PILOT_X_AXIS_ID= 0;
    public static byte PILOT_Y_AXIS_ID = 1;

    //auto
    public static final double kD = 0.25;
    public static final double hD = 0.044;
    public static final double bA = 10;
    public static final double MaxArea = 0.1;
    public static final double kA = 0.1; 
    public static final double TARGET_ANGLE = 1.1;
    public static final double ULTRASONIC_UNIT_CONVERSION = (10000/45.927);
    public static final double D1 = 1;
   // public static final StartingConfiguration startingConfiguration = StartingConfiguration.Middle;
    public static final double startY = 120;
    public static final int startingAngle = -45; //90 is looking away from the driver stations.

    public static final double AUTO_MAX_THROTTLE = 0.3;
    public static final double AUTO_CAMERA_HEIGHT = 40.5; //units: centimeters.
    public static final double AUTO_CAMERA_ANGLE = 50; //units: degrees.
    public static final double AUTO_SHOOTIN_DISTANCE = 120; //units: inches. todome update
    public static final double AUTO_HIGH_GOAL_HEIGHT = 40.5; //units: centimeters (temporary).
    public static final double HUB_DISTANCE = 231; //units: centimeters.

    public enum Pipeline {
        DriveCamera, Balls, GreenLedMode;
        public int getPipelineNumber() {
            switch(this) {
                case DriveCamera:
                    return 0;
                case Balls:
                    return 3;
                case GreenLedMode:
                    return 2;
            }
            return -1;
        }
    }
}


