package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;

//import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Climber {
  //  private CANSparkMax climberA;
  //  private CANSparkMax climberB;
    private double climbSpeed = 0.5;

    public Climber(){
    //    climberA = new CANSparkMax(CLIMBER_A_MOTOR_ID, MotorType.kBrushless);
    //    climberB = new CANSparkMax(CLIMBER_B_MOTOR_ID, MotorType.kBrushless);
    }

    public void forwardClimb(){
      //  climberA.set(ControlMode.PercentOutput, climbSpeed);
      //  climberB.set(ControlMode.PercentOutput, climbSpeed);
    }

    public void reverseClimb(){
      //  climberA.set(ControlMode.PercentOutput, -climbSpeed);
      //  climberB.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void stop(){
       // climberA.set(ControlMode.PercentOutput, 0);
    //    climberB.set(ControlMode.PercentOutput, 0);
    }
}
