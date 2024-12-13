package frc.robot.subsystems.Tank;

import org.littletonrobotics.junction.AutoLog;

public interface TankIO {

    @AutoLog
    public class TankIOInputs {
        /**
         * right motor data
         */
        public double tankRightLeadVelocity;
        public double tankRightPosition;

        /**
         * left motor data
         */
        public double tankLeftLeadVelocity;
        public double tankleftPosition;

        /**
         * gyro data
         */
        public float yaw;
        public float pitch;
        public float roll;
    }

    public default void updateInputs(TankIOInputs inputs) {}

    public default void setVolatge(double leftV, double rightV) {}
}
