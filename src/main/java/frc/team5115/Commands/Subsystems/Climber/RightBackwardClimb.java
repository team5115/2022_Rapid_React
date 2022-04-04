package frc.team5115.Commands.Subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class RightBackwardClimb extends CommandBase{
    Climber climber;
    Timer timer;

    public RightBackwardClimb(Climber right){
        climber = right;
    }

    public void execute(){
        if(climber.getRightLimit() == false){
            climber.nogodirectionright = "null";
            
        }
      
        if(climber.nogodirectionright == "fwdr"){
            climber.rightStop();
                
        }
        else{
            climber.rightForwardClimb();
        }
        System.out.println(climber.nogodirectionright);
    }        
    
    public boolean isFinished(){
        if(climber.getRightLimit()&climber.nogodirectionright=="null"){
            climber.nogodirectionright = "fwdr";
            return true;
        }
        else{
            return false;
        }
    }

    
}
    