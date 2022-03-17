package frc.team5115.Commands.NewAuto.AdjustSubsitute;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team5115.Subsystems.Limelight.*;
import frc.team5115.Subsystems.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import frc.team5115.Robot.*;
import edu.wpi.first.wpilibj.Timer;


public class DriveToPoint extends CommandBase{

    Drivetrain drivetrain;
    public DriveToPoint(Drivetrain drivetrain){
        this.drivetrain = drivetrain;
    }


    @Override
        public void initialize(){
        }

    @Override
        public void execute() {
            drivetrain.plugAndChugDrive(-0.25, 0.25, -0.25, 0.25);
            
        }

        @Override 
        public void end(boolean interupted){
            drivetrain.stop();
        }
    @Override
        public boolean isFinished() {
                if(drivetrain.backLeftEncoder < 80){
                    return true;
                }
                return false;
            }
    
}