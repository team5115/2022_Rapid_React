package frc.team5115.Commands.NewAuto.Adjust;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;


public class AdjustDistance extends CommandBase {
    Drivetrain drivetrain;
    Camera camera;
    Timer timer;

    public AdjustDistance(Drivetrain drivetrain, Camera camera) {
        this.drivetrain = drivetrain;
        this.camera = camera;
        timer = new Timer();

    }

    @Override
        public void initialize(){
            timer.reset();
            timer.start();
        }

    @Override
        public void execute() {
            drivetrain.AdjustDistance();
            System.out.print("adjusting distance");
            System.out.println(drivetrain.getY());

        }

    @Override
        public void end(boolean interupted){
            drivetrain.stop();
            //camera.setClimbAngle();
            //NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        }

    
    @Override
        public boolean isFinished() {
            if(timer.get()>0.5){
            if(Math.abs(drivetrain.getY()-TARGET_ANGLE)<1){
                return true;
            }
                return false;
        }
        else{
            return false;
        }
        }
}