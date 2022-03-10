package frc.team5115.Commands.Auto.NewAuto.BallFinder;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team5115.Subsystems.*;
import frc.team5115.Commands.Auto.NewAuto.BallFinder.AdjustRobotToBallCommandGroup;

public class AdjustRobotToBallCommandGroup extends SequentialCommandGroup {
        Drivetrain drivetrain;
        Intake intake;
    public AdjustRobotToBallCommandGroup(Drivetrain drivetrain, Intake intake){
        this.drivetrain = drivetrain;
        this.intake = intake;
        addCommands(
            //new AdjustAngleToBall(drivetrain),
            new AdjustDistanceToBall(drivetrain, intake) 
            //,new FinalMovement(intake)
            );

    }
}
