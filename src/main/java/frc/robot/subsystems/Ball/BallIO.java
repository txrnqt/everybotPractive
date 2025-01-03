package frc.robot.subsystems.Ball;

import org.littletonrobotics.junction.AutoLog;

public interface BallIO {

    @AutoLog
    public class BallIOInputs {
        /**
         * data from the beam breaks
         */
        public boolean intake;
        public boolean outtake;
    }

    public default void updateInputs(BallIOInputs inputs) {}

    public default void setPower(double Power) {}
}
