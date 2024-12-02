package frc.robot.subsystems.Hatch;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Hatch extends SubsystemBase {
    public HatchIO io;
    public HatchIOInputsAutoLogged inputs = new HatchIOInputsAutoLogged;
    private InterpolatingDoubleTreeMap radToAngle = new InterpolatingDoubleTreeMap();
    private CommandXboxController operator;
    private double estmAngle = 0.0;
    private boolean pidEnabled = false;

    /**
     * pid stuff \_|'_'|_/
     */
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
        return Rotation2d.fromRotations(estmAngle);
    }

    public Hatch(HatchIO io, CommandXboxController operator) {
        this.operator = operator;
        this.io = io;
        io.updateInputs(inputs);
    }

    /**
     * gets wrist set point
     */
    public void setHatchAngle(Rotation2d angle) {
        hatchPIDController.setSetpoint(angle.getRotations());
        pidEnabled = true;
    }

    public Command homePosition() {
        Command checkHome = (goToPosition(Rotation2d.fromDegrees(1))
            .until(() -> getHatchAngle().getDegrees() > 1).withTimeout(1));
        Command goHome = goToPosition(frc.robot.Constants.Hatch.HATCH_HOME).withTimeout(1);
        return checkHome.andThen(goHome);
    }

    /**
     * sets the hatch wrist at a certant angle using set points
     * 
     * @param angle that is desired
     * @return moves the wrist
     */
    public Command goToPosition(Rotation2d angle) {
        return Commands.runOnce(() -> {
            hatchPIDController.reset();
            hatchPIDController.setSetpoint(angle.getRotations());
            pidEnabled = true;
        }).andThen(Commands.waitUntil(() -> atGoal()))
            .andThen(Commands.runOnce(() -> pidEnabled = false));
    }

    public Command intake() {
        Command checkIntake = (goToPosition(Rotation2d.fromDegrees(1))
            .until(() -> getHatchAngle().getDegrees() > 1).withTimeout(1));
        Command goToIntake = (goToPosition(Constants.Hatch.INTAKE_POSITON).withTimeout(1));
        return checkIntake.andThen(goToIntake);
    }

    public Boolean atGoal() {
        return hatchPIDController.atSetpoint();
    }
}
