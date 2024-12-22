package frc.robot.subsystems.Tank;


import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Tank extends SubsystemBase {
    private TankIO io;
    private TankIOInputsAutoLogged inputs = new TankIOInputsAutoLogged();
    private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
        new Rotation2d(inputs.yaw), inputs.tankleftPosition, inputs.tankRightPosition);


    PIDController tankPidControllerL =
        new PIDController(Constants.Tank.TANK_KP, Constants.Tank.TANK_KI, Constants.Tank.TANK_KD);
    PIDController tankPidControllerR =
        new PIDController(Constants.Tank.TANK_KP, Constants.Tank.TANK_KI, Constants.Tank.TANK_KD);


    public Tank(TankIO tankIO) {
        this.io = tankIO;
        io.updateInputs(inputs);
    }

    @Override
    public void periodic() {
        Logger.processInputs("Tank", inputs);
        io.updateInputs(inputs);
        odometry.update(new Rotation2d(inputs.yaw), inputs.tankleftPosition,
            inputs.tankRightPosition);
    }

    public void setVolatge(double leftV, double rightV) {
        Logger.recordOutput("Tank/leftPower", leftV);
        Logger.recordOutput("Tank/rightPower", rightV);
        io.setVolatge(leftV, rightV);
    }

    public void setPIDVoltage(double leftSetPoint, double rightSetPoint) {
        double pidL = tankPidControllerL.calculate(leftSetPoint * Constants.Tank.MAX_SPEED);
        double pidR = tankPidControllerR.calculate(rightSetPoint * Constants.Tank.MAX_SPEED);
        setVolatge(pidL, pidR);
        Logger.recordOutput("Tank/pidL", pidL);
        Logger.recordOutput("Tank/rightPower", pidR);
    }

    public void setProfiledVoltage(double left, double right) {}

    public Command tankCMD(CommandXboxController driver) {
        return run(() -> {
            setPIDVoltage(driver.getLeftY(), driver.getRightY());
        });
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    // public void choreoController(Pose2d curPose, DifferentialSample sample) {
    // ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(new
    // ChassisSpeeds(tankPidControllerL.calculate(curPose.getX(), sample.x)+ sample.vx,
    // tankPidControllerR.calculate(curPose.getY(), sample.y) + sample.vy,
    // thetaController, null)
    // }
}

