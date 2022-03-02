package frc.team5115.Commands.Auto.NewAuto.Adjust;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;

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
        public boolean isFinished() {
            if(Math.abs(drivetrain.getDistanceFromHub()-HUB_DISTANCE)<5){
                return true;
            }
            else{
                return false;
            }
        }
}