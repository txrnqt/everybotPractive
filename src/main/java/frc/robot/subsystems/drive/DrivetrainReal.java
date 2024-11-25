package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * DrivetrainReal
 */
public class DrivetrainReal implements DrivetrainIO {


    /**
     * Drivetrain Real
     */
    public DrivetrainReal() {}

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.gyroYaw = Rotation2d.fromDegrees(0);
    }

    /**
     * Drive Voltage
     */
    public void setDriveVoltage(double lvolts, double rvolts) {}

}
