package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.*;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;

public class Climber {
  //private PWMSparkMax climberA;
  //private PWMSparkMax climberB;
  private double climbSpeed = .7;
  private CANSparkMax LeftClimber;
  private CANSparkMax RightClimber;

  public DigitalInput leftLimit;
  public DigitalInput rightLimit;
  public boolean disableLeftDirectionForward;
  public boolean disableRightDirectionForward;
  public String nogodirectionright;
  public String nogodirectionleft;
  private RelativeEncoder leftClimberEncoder;
  private RelativeEncoder rightClimberEncoder;
  public boolean FailSaveCheck;
  public double UpEncoderSetpoint;
  public double DownEncoderSetpoint;
  public PIDController pid;

    public Climber(){
      LeftClimber = new CANSparkMax(LEFT_CLIMBER_MOTOR_ID, MotorType.kBrushless);
      RightClimber = new CANSparkMax(RIGHT_CLIMBER_MOTOR_ID, MotorType.kBrushless);
      leftLimit = new DigitalInput(3);
      rightLimit = new DigitalInput(2);
      leftClimberEncoder = LeftClimber.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
      rightClimberEncoder = RightClimber.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);

      pid = new PIDController(kp, ki, kd);
      nogodirectionleft = "null";
      nogodirectionright = "null";
      FailSaveCheck = false;
  
    }


    public void EncoderPrint(){
      System.out.print(leftClimberEncoder.getPosition() + "\n");

    }

    public void EncoderReset(){
      leftClimberEncoder.setPosition(0);
    }

    public void leftForwardClimb(){
      if(FailSaveCheck == false){
     //LeftClimber.set(climbSpeed);
     LeftClimber.set(pid.calculate(leftClimberEncoder.getPosition(), -330));
      }
      else{
        leftStop();
      }
     //leftClimberTest.set(ControlMode.PercentOutput, climbSpeed);
    }

    public void rightForwardClimb(){
      if(FailSaveCheck == false){
     //RightClimber.set(climbSpeed);
     RightClimber.set(pid.calculate(rightClimberEncoder.getPosition(), -330));
      }
      else{
        rightStop();
      }
      //rightClimberTest.set(ControlMode.PercentOutput, climbSpeed);
    }

    public void leftReverseClimb(){
      if(FailSaveCheck == false){
        //LeftClimber.set(-climbSpeed);
        LeftClimber.set(pid.calculate(leftClimberEncoder.getPosition(), 0));
         }
         else{
           leftStop();
         }
    //leftClimberTest.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void rightReverseClimb(){
      if(FailSaveCheck == false){
        //RightClimber.set(-climbSpeed);
        RightClimber.set(pid.calculate(rightClimberEncoder.getPosition(), 0));
         }
         else{
           rightStop();
         }
    //rightClimberTest.set(ControlMode.PercentOutput, -climbSpeed);
    }

    public void leftStop(){
    LeftClimber.set(0);
    //   leftClimberTest.set(ControlMode.PercentOutput, 0);
    }

    public void rightStop(){
    RightClimber.set(0);
    //  leftClimberTest.set(ControlMode.PercentOutput, 0);
   }

   public void failSave(){
     FailSaveCheck = true;
   }
   public void failSaveStop(){
    FailSaveCheck = false;
   }
  
}
