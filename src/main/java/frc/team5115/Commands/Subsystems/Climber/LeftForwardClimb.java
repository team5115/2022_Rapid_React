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
        System.out.println("left no go direction" + climber.nogodirectionleft);

    }        
    
    public boolean isFinished(){
        if(climber.getLeftLimit()&climber.nogodirectionleft=="null"){
            climber.nogodirectionleft = "fwdl";
            return true;
        }
        else{
            return false;
        }
    }

    
}
    