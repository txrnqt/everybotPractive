package frc.robot.subsystems.Tank;

import org.littletonrobotics.junction.AutoLog;

public interface TankIO {

    @AutoLog
    public class TankIOInputs {
        /**
         * right motor data
         */
        public double tankRightLeadPositionRad;
        public double tankRightLeadVelocityRadPerSec;
        public double tankRightLeadAppliedVolts;

        /**
         * left motor data
         */
        public double tankLeftLeadPositionRad;
        public double tankLeftLeadVelocityRadPerSec;
        public double tankLeftLeadAppliedVolts;

        /**
         * gyro data
         */
        public static float yaw;
        public static float pitch;
        public static float roll;
    }

    public default void updateInputs(TankIOInputs inputs) {}

    public default void setPower(double powerLeft, double powerRight) {}
}
