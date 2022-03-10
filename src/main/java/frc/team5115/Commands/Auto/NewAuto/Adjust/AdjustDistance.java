package frc.team5115.Commands.Auto.NewAuto.Adjust;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AdjustDistance extends CommandBase {
    Drivetrain drivetrain;
      

    public AdjustDistance(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

    }

    @Override
        public void execute() {
            drivetrain.AdjustDistance();
        }

    @Override
        public void end(boolean interupted){
            drivetrain.stop();
            //NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        }

    
    @Override
        public boolean isFinished() {
            if(Math.abs(drivetrain.getDistanceFromHub()-HUB_DISTANCE)<5){
                return true;
            }
            else{
                return false;
            }
        }
}