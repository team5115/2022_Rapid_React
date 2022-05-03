package frc.team5115.Commands.NewAuto.Adjust;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class AdjustAngle extends CommandBase {
    Drivetrain drivetrain;
    Timer timer;

      

    public AdjustAngle(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        timer = new Timer();
    }

    @Override
        public void initialize(){
            timer.reset();
            timer.start();
        }
        

    @Override
        public void execute() {
            drivetrain.AdjustAngle();
            System.out.print("adjust angle x: " + drivetrain.getX());
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        }

    @Override 
        public void end(boolean interputed){
            System.out.println("adjust angle is finished");
        }

    @Override
        public boolean isFinished() {
            if(timer.get()> 0.5){
            if(Math.abs(drivetrain.getX()) < 0.5){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        }
}