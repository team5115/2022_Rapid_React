package frc.team5115.Commands.Subsystems.Limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.team5115.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team5115.Subsystems.*;

public class Update extends CommandBase {
    Limelight limelight;
    NetworkTableEntry pipeline;
    public NetworkTableEntry tx;
    public NetworkTableEntry ty;
    public NetworkTableEntry tv;
    public NetworkTableEntry ta;
      

    public Update(Limelight limelight) {
        this.limelight = limelight;

    }

    @Override
        public void execute() {
            NetworkTable networkTableInstance = NetworkTableInstance.getDefault().getTable("limelight");
            NetworkTableEntry tx = networkTableInstance.getEntry("tx");
            NetworkTableEntry ty = networkTableInstance.getEntry("ty");
            NetworkTableEntry tv = networkTableInstance.getEntry("tv");
            NetworkTableEntry ta = networkTableInstance.getEntry("ta");

        }
    @Override
        public boolean isFinished() {
        return false;
        }

}