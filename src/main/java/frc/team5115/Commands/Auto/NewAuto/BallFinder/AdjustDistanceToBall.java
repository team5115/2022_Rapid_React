package frc.team5115.Commands.Auto.NewAuto.BallFinder;

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


public class AdjustDistanceToBall extends CommandBase{

    Drivetrain drivetrain;
    Intake intake;
    public AdjustDistanceToBall(Drivetrain drivetrain, Intake intake){
        this.drivetrain = drivetrain;
        this.intake = intake;
    }


    @Override
        public void initialize(){
        }

    @Override
        public void execute() {
            intake.forwardIntake();
            drivetrain.autodrive();
            
        }

        @Override 
        public void end(boolean interupted){
            drivetrain.stop();
            intake.stop();
        }
    @Override
        public boolean isFinished() {
                if(drivetrain.backLeftEncoder > 200){
                    return true;
                }
                return false;
            }
    
}