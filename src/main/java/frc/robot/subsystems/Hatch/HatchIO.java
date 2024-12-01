package frc.robot.subsystems.Hatch;

import org.littletonrobotics.junction.AutoLog;

public interface HatchIO {
    @AutoLog
    public class HatchIOInputs {
    }

    public default void updateInputs(HatchIOInputs inputs) {}

    public default void setPower(double power) {}
}
