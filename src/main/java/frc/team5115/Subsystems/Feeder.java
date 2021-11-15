package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.team5115.Constants.FEEDER_MOTOR_ID;

public class Feeder extends SubsystemBase {
    VictorSPX feeder_m;
    double feedspeed = -0.8;

    public Feeder() {
        feeder_m = new VictorSPX(FEEDER_MOTOR_ID);
    }

    public void moveCells() {
        feeder_m.set(ControlMode.PercentOutput, feedspeed);
    }

    public void stopCells() {
        feeder_m.set(ControlMode.PercentOutput, 0);
    }

    public void spit() {
        feeder_m.set(ControlMode.PercentOutput, -feedspeed);
    }

}