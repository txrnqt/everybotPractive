package frc.robot.subsystems.Tank;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.Constants;

public class TankSim implements TankIO {
    private FlywheelSim leftLead = new FlywheelSim(
        DCMotor.getKrakenX60(Constants.Tank.sim.LEFTLEAD), Constants.Tank.sim.LEFTLEADGEAR, 0.001);
    private FlywheelSim left = new FlywheelSim(DCMotor.getKrakenX60(Constants.Tank.sim.LEFT),
        Constants.Tank.sim.LEFTGEAR, 0.001);
    private FlywheelSim rightLead =
        new FlywheelSim(DCMotor.getKrakenX60(Constants.Tank.sim.RIGHTLEAD),
            Constants.Tank.sim.RIGHTLEADGEAR, 0.001);
    private FlywheelSim right = new FlywheelSim(DCMotor.getKrakenX60(Constants.Tank.sim.RIGHT),
        Constants.Tank.sim.RIGHTGEAR, 0.001);

    private double leftAppliedVolts;
    private double rightAppliedVolts;

    // private final SimulatedBot sim;

}
