package frc.robot.subsystems.Hatch;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Hatch extends SubsystemBase {
    public HatchIO io;
    public HatchIOInputsAutoLogged inputs = new HatchIOInputsAutoLogged();

    public Hatch(HatchIO io, CommandXboxController operator) {
        this.io = io;
        io.updateInputs(inputs);
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("hatch", inputs);

        Rotation2d currentHatchAngle = Rotation2d.fromRotations(inputs.hatchAbsoluteENCRawValue);
        double pid = hatchPIDController.calculate(currentHatchAngle.getDegrees());
        io.setVolatge(pid);
    }

    public boolean getTouch() {
        return inputs.touchSensor;
    }

    PIDController hatchPIDController = new PIDController(Constants.Hatch.HATCH_KP,
        Constants.Hatch.HATCH_KI, Constants.Hatch.HATCH_KD);

    /**
     * calculates wrist angle from raw value
     */
    public Rotation2d getHatchAngleMesuremet() {
        return Rotation2d.fromRotations(
            Constants.Hatch.HATCH_M * inputs.hatchAbsoluteENCRawValue + Constants.Hatch.HATCH_B);
    }

    public Rotation2d getHatchAngle() {
        return Rotation2d.fromRotations(inputs.hatchAbsoluteENCRawValue);
    }

    /**
     * gets wrist set point
     */
    public Command setHatchAngle(Rotation2d angle) {
        return Commands.runOnce(() -> {
            hatchPIDController.reset();
            hatchPIDController.setSetpoint(angle.getDegrees());
        }).andThen(Commands.waitUntil(() -> atGoal()));
    }

    /**
     * move the wrist back to the "home" positon
     */
    public Command homePosition() {
        Command checkHome = (goToPosition(Rotation2d.fromDegrees(1))
            .until(() -> getHatchAngle().getDegrees() > 1).withTimeout(1));
        Command goHome = goToPosition(frc.robot.Constants.Hatch.HATCH_HOME).withTimeout(1);
        return checkHome.andThen(goHome);
    }

    /**
     * sets the hatch wrist at a certant angle using set points
     */
    public Command goToPosition(Rotation2d angle) {
        return Commands.runOnce(() -> {
            hatchPIDController.reset();
            hatchPIDController.setSetpoint(angle.getRotations());
        }).andThen(Commands.waitUntil(() -> atGoal()));
    }

    /**
     * move the wrist up in the "intake" position
     */
    public Command intake() {
        Command checkIntake = (goToPosition(Rotation2d.fromDegrees(1))
            .until(() -> getHatchAngle().getDegrees() > 1).withTimeout(1));
        Command goToIntake = (goToPosition(Constants.Hatch.INTAKE_POSITON).withTimeout(1));
        return checkIntake.andThen(goToIntake);
    }

    /**
     * checks if the wrist is at the desired set point
     */
    public Boolean atGoal() {
        return hatchPIDController.atSetpoint();
    }

    public boolean touchSensorStatus() {
        return inputs.touchSensor;
    }
}
