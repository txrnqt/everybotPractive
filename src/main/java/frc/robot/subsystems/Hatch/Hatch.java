package frc.robot.subsystems.Hatch;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Hatch extends SubsystemBase {
    public HatchIO io;
    public HatchIOInputsAutoLogged inputs = new HatchIOInputsAutoLogged();
    private InterpolatingDoubleTreeMap radToAngle = new InterpolatingDoubleTreeMap();
    private CommandXboxController operator;
    private double estmAngle = 0.0;
    private boolean pidEnabled = false;

    /**
     * pid stuff \_|'_'|_/
     */
    PIDController hatchPIDController = new PIDController(Constants.Hatch.HATCH_KP,
        Constants.Hatch.HATCH_KI, Constants.Hatch.HATCH_KD);

    PIDController hatchProfiledPIDController = new PIDController(Constants.Hatch.HATCH_LARGE_KP,
        Constants.Hatch.HATCH_KI, frc.robot.Constants.Hatch.HATCH_KD);


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
        hatchProfiledPIDController.setSetpoint(angle.getRotations());
        pidEnabled = true;
    }

    public Command homePosition() {
        Command checkHome = Command.either(
            goTO
        )
    }

    public Command goToPositon(Rotation2d angle) {
        return Command.runOnce(() -> {
            hatchPIDController.reset();
            hatchPIDController.setSetpoint(angle.getRotations);
            hatchProfiledPIDController.setSetpoint(angle.getRotations);
            pidEnabled = true;
        }).andThen(Commands.waitUntil(() -> atGoal))
    }

    public Boolean atGoal() {
        return hatchPIDController.atSetpoint();
    }

}
