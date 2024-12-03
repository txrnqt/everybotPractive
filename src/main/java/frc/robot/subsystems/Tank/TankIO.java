package frc.robot.subsystems.Tank;

import org.littletonrobotics.junction.AutoLog;

public interface TankIO {

    @AutoLog
    public class TankIOInputs {
        /**
         * right motor data
         */
        public double tankRightLeadVelocityRadPerSec;

        /**
         * left motor data
         */
        public double tankLeftLeadVelocityRadPerSec;

        /**
         * gyro data
         */
        public static float yaw;
        public static float pitch;
        public static float roll;
    }

    public default void updateInputs(TankIOInputs inputs) {}

    public default void setVolatge(double leftV, double rightV) {}
}
