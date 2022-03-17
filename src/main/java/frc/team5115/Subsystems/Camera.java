package frc.team5115.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
    Servo servo; 
    double shoot = 45;
    double climb = 90;
    double neutral = 0; 
    
    
    public Camera(){
        servo = new Servo(0);
      //  servo.setAngle(neutral);
    }

    public void setClimbAngle(){
        servo.setAngle(180);
    }

    public void setShootAngle(){
        servo.setAngle(90);
    }

    public void setNeutralAngle(){
        servo.setAngle(0);
    }

    public void getAngle(){
       // System.out.println("servo angle"+servo.getAngle());}
}
}