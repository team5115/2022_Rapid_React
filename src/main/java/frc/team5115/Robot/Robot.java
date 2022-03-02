package frc.team5115.Robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team5115.Commands.Auto.NewAuto.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        robotContainer.setUltraDefault();
        robotContainer.startAuto();
    }


    public void autonomousPeriodic() {
        robotContainer.autoPeriod();
    }

    public void teleopInit () {
        robotContainer.startTeleop();
        robotContainer.setDriveDefault();
    }
    
    public void teleopPeriodic () {}

    public void testInit () {
        CommandScheduler.getInstance().cancelAll();
    }

    public void testPeriodic () {}

    public void practiceInit(){}

    public void practicePeriodic(){}
}
