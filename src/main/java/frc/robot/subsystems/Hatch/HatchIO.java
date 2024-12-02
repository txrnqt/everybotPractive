package frc.robot.subsystems.Hatch;

import org.littletonrobotics.junction.AutoLog;

public interface HatchIO {
    @AutoLog
    public static class HatchIOInputs {
        public double positon;
    }



    public default void setVolatge(double v) {}

    public default void updateInputs(HatchIOInputs inputs) {}
}
