package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Constants file.
 */
public final class Constants {
    /**
     * Stick Deadband
     */
    public static final double stickDeadband = 0.1;
    /**
     * Driver ID
     */
    public static final int driverID = 0;
    /**
     * Operator ID
     */
    public static final int operatorID = 1;

    /**
     * Motor CAN id's.
     */
    public static final class Motors {
    }

    /**
     * Pneumatics CAN id constants.
     */
    public static final class Pneumatics {
    }

    public static final class Tank {
        public static final edu.wpi.first.wpilibj.SPI.Port navXID =
            edu.wpi.first.wpilibj.SPI.Port.kMXP;
        public static final boolean isFeildRelative = true;
    }

    public static final class Ball {
        public static final int BEAMBREAK1 = 0;
        public static final int BEAMBREAK2 = 1;
        public static final int BALLMOTOR = 12;
    }

    public static final class Hatch {
        /**
         * hardware ports
         */
        public static final int HATCHMOTOR = 10;
        public static final int TOUCHSENSOR = 3;
        public static final int CANCODER = 15;

        /**
         * PID values (theres none :O)
         */
        public static final double HATCH_LARGE_KP = 1.0;
        public static final double HATCH_KP = 1.0;
        public static final double HATCH_KI = 1.0;
        public static final double HATCH_KD = 1.0;
        public static final double MAX_VELOCITY = 1.0;
        public static final double MAX_ACCELERATION = 1.0;

        public static final Rotation2d HATCH_REF_1_ANGLE_MEASURED = Rotation2d.fromRotations(0.0);
        public static final Rotation2d HATCH_REF_2_ANGLE_MEASURED = Rotation2d.fromRotations(0.0);
        public static final Rotation2d HATCH_REF_1_ANGLE_ACTUAL = Rotation2d.fromDegrees(0.0);
        public static final Rotation2d HATCH_REF_2_ANGLE_ACTUAL = Rotation2d.fromDegrees(0.0);

        public static final double HATCH_M;
        public static final double HATCH_B;

        static {
            HATCH_M =
                (HATCH_REF_2_ANGLE_ACTUAL.getRotations() - HATCH_REF_1_ANGLE_ACTUAL.getRotations())
                    / (HATCH_REF_2_ANGLE_MEASURED.getRotations()
                        - HATCH_REF_1_ANGLE_MEASURED.getRotations());
            // meas_1 * m + b = act_1
            // b = act_1 - meas_1 * m
            HATCH_B = HATCH_REF_1_ANGLE_ACTUAL.getRotations()
                - HATCH_REF_1_ANGLE_MEASURED.getRotations() * HATCH_M;
        }
    }
}
