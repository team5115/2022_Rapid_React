package frc.team5115.Commands.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;

public class RightBackwardClimb extends CommandBase {
    Drivetrain drivetrain;

      
 
    public RightBackwardClimb(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
        public void initialize(){
        }

    @Override
        public void execute() {
            
        }

    @Override 
        public void end(boolean interputed){
            
        }

    @Override
        public boolean isFinished() {
            return false;
        }
}