package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;

public class Intake extends SubsystemBase{
    private TalonSRX intake;
    private double intakeSpeed = 0.5;
    public ColorSensorV3 colorSensor;
   // public ColorSensorV3 colorSensor2;
   // private final I2C.Port i2cPort = I2C.Port.kOnboard;
   // private final I2C.Port i2cPort2 = I2C.Port.kMXP;
    private int ballProximity = 60;

    public Intake(){
        intake = new TalonSRX(INTAKE_MOTOR_ID);
     //   colorSensor = new ColorSensorV3(i2cPort);
      //  colorSensor2 = new ColorSensorV3(i2cPort2);
        
    }

    public void forwardIntake(){
        intake.set(ControlMode.PercentOutput, intakeSpeed);


      //  System.out.println("color sensor:  "+colorSensor.getProximity());
    }

    public void reverseIntake(){
        intake.set(ControlMode.PercentOutput, -intakeSpeed);
    }

    public void stop(){
        intake.set(ControlMode.PercentOutput, 0);
      //  System.out.println("color sensor off");
    }
    /*
    public boolean ballDetection(){
        if(colorSensor.getProximity()>ballProximity||colorSensor2.getProximity()>ballProximity){
            return true;
        }
        else{
            return false;
        }
    }
    */
    
}
