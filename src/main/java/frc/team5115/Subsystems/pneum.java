package frc.team5115.Subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class pneum{
    DoubleSolenoid pneum;
    public pneum(){
        pneum = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1); 
    }

    public void forward(){
        pneum.set(Value.kForward);
    }

    public void reverse(){
        pneum.set(Value.kReverse);
    }

    public void stop(){
        pneum.set(Value.kOff);
    }
}