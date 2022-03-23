package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;

//import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.Servo;
import static frc.team5115.Constants.*;
import edu.wpi.first.wpilibj.DigitalInput;

public class Climber {
  //private PWMSparkMax climberA;
  //private PWMSparkMax climberB;
  private double climbSpeed = .7;
  private Servo servo;
  private CANSparkMax LeftClimber;
  private CANSparkMax RightClimber;
  private TalonSRX leftClimberTest;
  private TalonSRX rightClimberTest;
  
  public DigitalInput leftLimit;
  public DigitalInput rightLimit;
  public boolean disableLeftDirectionForward;
  public boolean disableRightDirectionForward;
  public String nogodirectionright;
  public String nogodirectionleft;

  
    public Climber(){
      LeftClimber = new CANSparkMax(LEFT_CLIMBER_MOTOR_ID, MotorType.kBrushless);
     // leftClimberTest = new TalonSRX(1);
     // rightClimberTest = new TalonSRX(2);
      RightClimber = new CANSparkMax(RIGHT_CLIMBER_MOTOR_ID, MotorType.kBrushless);
      leftLimit = new DigitalInput(1);
      rightLimit = new DigitalInput(2);

      nogodirectionleft = "null";
      nogodirectionright = "null";
  
      //dio2 = new DigitalInput(1);
    }




    public void leftForwardClimb(){
     LeftClimber.set(climbSpeed);
     //leftClimberTest.set(ControlMode.PercentOutput, climbSpeed);
    }

    public void rightForwardClimb(){
     RightClimber.set(climbSpeed);
     // rightClimberTest.set(ControlMode.PercentOutput, climbSpeed);
    }

    public boolean getLeftLimit(){
      return !leftLimit.get();
    }

    public boolean getRightLimit(){
      return !rightLimit.get();
    }
    
    

    public void leftReverseClimb(){
    LeftClimber.set(-climbSpeed);
    //leftClimberTest.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void rightReverseClimb(){
    RightClimber.set(-climbSpeed);
    //  rightClimberTest.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void leftStop(){
    LeftClimber.set(0);
    //   leftClimberTest.set(ControlMode.PercentOutput, 0);
    }

    public void rightStop(){
    RightClimber.set(0);
    //  leftClimberTest.set(ControlMode.PercentOutput, 0);
   }
  
}
