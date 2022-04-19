package frc.team5115.Subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Pneumatics {
    DoubleSolenoid exampleDoublePCM;

    public Pneumatics(){
        exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public void forward(){
        exampleDoublePCM.set(DoubleSolenoid.Value.kForward);
    }

    public void backward(){
        exampleDoublePCM.set(DoubleSolenoid.Value.kReverse);
    }

    public void stop(){
        exampleDoublePCM.set(DoubleSolenoid.Value.kOff);
    }
    
}
