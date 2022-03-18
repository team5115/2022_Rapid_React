package frc.team5115.Commands.Subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class LeftReverseClimb extends CommandBase{
    Climber climber;
    Timer timer;

    public LeftReverseClimb(Climber left){
        climber = left;
    }

    public void execute(){
        if(climber.getLeftLimit() == false){
            climber.nogodirectionleft = "null";
            
        }
      
        if(climber.nogodirectionleft == "bckl"){
            climber.leftStop();
                
        }
        else{
            climber.leftReverseClimb();
        }
        System.out.println("left no go direction" + climber.nogodirectionleft);
    }        
    
    public boolean isFinished(){
        if(climber.getLeftLimit()&climber.nogodirectionleft=="null"){
            climber.nogodirectionleft = "bckl";
            return true;
        }
        else{
            return false;
        }
    }

    
}
    