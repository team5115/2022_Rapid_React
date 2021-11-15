package frc.team5115.Commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.Drivetrain;

public class DriveForward extends CommandBase {
    Drivetrain drivetrain;

    public DriveForward(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    public void execute() {
        drivetrain.autodrive();
        System.out.println("STARTING AUTO CODE");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
