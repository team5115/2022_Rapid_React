package frc.team5115.Commands.Subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class RightReverseClimb extends CommandBase{
    Climber climber;
    Timer timer;

    public RightReverseClimb(Climber right){
        climber = right;
    }

    public void execute(){
        if(climber.getRightLimit() == false){
            climber.nogodirectionright = "null";
            
        }
      
        if(climber.nogodirectionright == "bckr"){
            climber.rightStop();
                
        }
        else{
            climber.rightReverseClimb();
        }
        System.out.println(climber.nogodirectionright);
    }        
    
    public boolean isFinished(){
        if(climber.getRightLimit()&climber.nogodirectionright=="null"){
            climber.nogodirectionright = "bckr";
            return true;
        }
        else{
            return false;
        }
    }

    
}
    