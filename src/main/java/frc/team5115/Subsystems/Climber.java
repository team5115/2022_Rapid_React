package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;

//import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Servo;
import static frc.team5115.Constants.*;

public class Climber {
  //private PWMSparkMax climberA;
  //private PWMSparkMax climberB;
  private double climbSpeed = .3;
  private Servo servo;
  private CANSparkMax LeftClimber;
  private CANSparkMax RightClimber;

    public Climber(){
      LeftClimber = new CANSparkMax(LEFT_CLIMBER_MOTOR_ID, MotorType.kBrushless);
      RightClimber = new CANSparkMax(RIGHT_CLIMBER_MOTOR_ID, MotorType.kBrushless);
    }

    public void leftForwardClimb(){
     LeftClimber.set(climbSpeed);
    }

    public void rightForwardClimb(){
      RightClimber.set(climbSpeed);
    }

    public void leftReverseClimb(){
      LeftClimber.set(-climbSpeed);
    }

    public void rightReverseClimb(){
      RightClimber.set(-climbSpeed);
    }

    public void leftStop(){
       LeftClimber.set(0);
    }

    public void rightStop(){
      RightClimber.set(0);
   }
}
