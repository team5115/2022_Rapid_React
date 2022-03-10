package frc.team5115.Commands;

import java.lang.Math;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;
import static frc.team5115.Constants.*;

public class Ultrasonic extends CommandBase{

    Drivetrain drivetrain;
    public Ultrasonic(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

    }

    @Override
        public void execute() {
            //drivetrain.DistanceDetectionAverage();
            drivetrain.printEncoderDistance();
        }
    @Override
        public boolean isFinished() {
            return false;
        }

    
}
