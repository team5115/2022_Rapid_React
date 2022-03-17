package frc.team5115.Commands.Subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class LeftForwardClimb extends CommandBase{
    Climber climber;
    Timer timer;

    public LeftForwardClimb(Climber left){
        climber = left;
    }

    public void execute(){
        if(climber.getLeftLimit() == false){
            climber.nogodirectionleft = "null";
            
        }
      
        if(climber.nogodirectionleft == "fwdl"){
            climber.leftStop();
                
        }
        else{
            climber.leftForwardClimb();
        }
        System.out.println(climber.nogodirectionright);
    }        
    
    public boolean isFinished(){
        if(climber.getLeftLimit()&climber.nogodirectionright=="null"){
            climber.nogodirectionright = "fwdl";
            return true;
        }
        else{
            return false;
        }
    }

    
}
    