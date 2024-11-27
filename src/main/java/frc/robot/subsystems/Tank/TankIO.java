package frc.robot.subsystems.Tank;

import org.littletonrobotics.junction.AutoLog;

public interface TankIO {

    @AutoLog
    public class TankIOInputs {
        public double tankPositionRad = 0.0;
        public double tankVelocityRadPerSec = 0.0;
        public double tankAppliedVolts = 0.0;
    }

    public default void updateInputs(TankIOInputs inputs) {}

    public default void setPower(double powerLeft, double powerRight) {}
}
