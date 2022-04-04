package frc.team5115.Commands.NewAuto.BallFinder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team5115.Subsystems.Limelight.*;
import frc.team5115.Subsystems.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import frc.team5115.Constants;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class AdjustAngleToSecondBall extends CommandBase {
    Drivetrain drivetrain;
    Timer timer;

      

    public AdjustAngleToSecondBall(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        timer = new Timer();
    }

    @Override
        public void initialize(){
            timer.reset();
            timer.start();
        }
        

    @Override
        public void execute() {
            drivetrain.AdjustAngle();
            System.out.print("adjust angle x: " + drivetrain.getX());
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        }

    @Override 
        public void end(boolean interputed){
            System.out.println("adjust angle is finished");
        }

    @Override
        public boolean isFinished() {
            if(timer.get()> 0.5){
            if(Math.abs(drivetrain.getX()) < TARGET_ANGLE_BALL_2){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        }
}