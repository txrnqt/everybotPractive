package frc.robot.subsystems.Ball;

import org.littletonrobotics.junction.AutoLog;

public interface BallIO {

    @AutoLog
    public class BallIOInputs {
    }

    public default void updateInputs(BallIOInputs inputs) {}

    public default void setPower(double Power) {}

    public default boolean hasBall() {
        return true;
    }
}
