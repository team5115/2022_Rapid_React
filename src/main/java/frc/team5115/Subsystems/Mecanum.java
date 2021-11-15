package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.drive.Vector2d;
import static frc.team5115.Constants.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Mecanum extends SubsystemBase{
    private PWMTalonSRX frontLeft;
    private PWMTalonSRX frontRight;
    private PWMTalonSRX backLeft;
    private PWMTalonSRX backRight;

    public AHRS gyro;

    MecanumDrive cart;

    private double throttle = 0.5;
    private double frontLeftSpeed;
    private double frontRightSpeed;
    private double backLeftSpeed;
    private double backRightSpeed;



    public Mecanum() {
        frontLeft = new PWMTalonSRX(FRONT_LEFT_MOTOR_ID);
        frontRight = new PWMTalonSRX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new PWMTalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new PWMTalonSRX(BACK_RIGHT_MOTOR_ID);

        gyro = new AHRS(SPI.Port.kMXP);
        gyro.reset();

        cart = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    }

    public void drive(double y, double x, double z, double gyroangle) {
        y = deadband(y);
        x = deadband(x);

        //double[] processed = rotate(x,y,gyroangle);

        Vector2d input = new Vector2d(y,x);
        input.rotate(-gyroangle);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[FRONT_LEFT_MOTOR_ID] = input.x + input.y + z;
        wheelSpeeds[FRONT_RIGHT_MOTOR_ID] = -input.x + input.y - z;
        wheelSpeeds[BACK_LEFT_MOTOR_ID] = -input.x + input.y + z;
        wheelSpeeds[BACK_RIGHT_MOTOR_ID] = input.x + input.y - z;

        normalize(wheelSpeeds);

        frontLeft.set(wheelSpeeds[FRONT_LEFT_MOTOR_ID]);
        frontRight.set(wheelSpeeds[FRONT_RIGHT_MOTOR_ID] * -1);
        backLeft.set(wheelSpeeds[BACK_LEFT_MOTOR_ID]);
        backRight.set(wheelSpeeds[BACK_RIGHT_MOTOR_ID] * -1);
    }

    public void simpleDrive(double y, double x, double z) {
        frontLeftSpeed = x + y + z;
        frontRightSpeed = y - x - z;
        backLeftSpeed = x + y - z;
        backRightSpeed = y - x + z;
        
        frontLeft.set(frontLeftSpeed);
        frontRight.set(frontRightSpeed);
        backLeft.set(backLeftSpeed);
        backRight.set(backRightSpeed);
    }

    public double throttle(double increase, double decrease) {
        throttle += 0.03 *(increase - decrease);

        if (throttle > 1){
            throttle = 1;
        } else if(throttle < 0){
            throttle = 0;
        }
        return throttle;
    }

    private double[] rotate(double x, double y, double gyroangle) {
        double cosA = Math.cos(gyroangle * (Math.PI / 180));
        double sinA = Math.sin(gyroangle * (Math.PI / 180));
        double[] out = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }

    private double deadband(double value) {
        if (Math.abs(value) > DEAD_BAND) {
            if (value > 0.0) {
                return (value - DEAD_BAND) / (1.0 - DEAD_BAND);
            } else {
                return (value + DEAD_BAND) / (1.0 - DEAD_BAND);
            }
        } else {
            return 0.0;
        }
    }

    public void debug() {
//        System.out.println(frontLeft.getSelectedSensorPosition());
//        System.out.println(frontRight.getSelectedSensorPosition());
//        System.out.println(backLeft.getSelectedSensorPosition());
//        System.out.println(backRight.getSelectedSensorPosition());
    }

    private void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < wheelSpeeds.length; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1.0) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }
/*
    public void init() {
        update();
    }
    public void update(Joystick joy) {
        cart.drive(joy.getX(), -joy.getY(), joy.getZ(), (double) 0);
    } 
    */
}