package frc.robot;

import java.beans.IntrospectionException;
import javax.swing.plaf.basic.BasicComboBoxUI.FocusHandler;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.UnitBuilder;

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
        public static final int FRONTRIGHT = 4;
        public static final int FRONTLEFT = 2;
        public static final int BACKRIGHT = 5;
        public static final int BACKLEFT = 3;

        /** pid value */
        public static final double TANK_KP = 0.0;
        public static final double TANK_KI = 0.0;
        public static final double TANK_KD = 0.0;
        public static final double MAX_SPEED = 0.0;

        public class sim {
            public static final int LEFTLEAD = 1;
            public static final int LEFT = 1;
            public static final int RIGHTLEAD = 1;
            public static final int RIGHT = 1;

            /** gear ratios */
            public static final double LEFTLEADGEAR = 3;
            public static final double LEFTGEAR = 3;
            public static final double RIGHTLEADGEAR = 3;
            public static final double RIGHTGEAR = 3;
        }
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

        public static final Rotation2d HATCH_HOME = Rotation2d.fromDegrees(10);
        public static final Rotation2d INTAKE_POSITON = Rotation2d.fromDegrees(100);
    }
}


