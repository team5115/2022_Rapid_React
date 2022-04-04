package frc.team5115.Commands.Subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class LeftForwardClimb extends CommandBase{
    Climber climber;
    Timer timer;

    public LeftForwardClimb(Climber right){
        climber = right;
    }

    public void execute(){
        if(climber.getLeftLimit() == false){
            climber.nogodirectionright = "null";
            
        }
      
        if(climber.nogodirectionright == "fwdl"){
            climber.leftStop();
                
        }
        else{
            climber.rightForwardClimb();
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
    