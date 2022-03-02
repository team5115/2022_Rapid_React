package frc.team5115.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
    Servo servo; 
    double shoot = 45;
    double climb = 90;
    double neutral = 0; 
    
    
    public Camera(){
      //  servo = new Servo(6);
      //  servo.setAngle(neutral);
    }

    public void setClimbAngle(){
       // servo.setAngle(climb);
    }

    public void setShootAngle(){
       // servo.setAngle(shoot);
    }

    public void setNeutralAngle(){
       // servo.setAngle(neutral);
    }

    public void getAngle(){
       // System.out.println("servo angle"+servo.getAngle());}
}
}