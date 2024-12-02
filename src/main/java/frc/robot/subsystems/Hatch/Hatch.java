package frc.robot.subsystems.Hatch;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hatch extends SubsystemBase {
    public HatchIO io;
    public HatchIOInputsAutoLogged inputs = new HatchIOInputsAutoLogged();

    private boolean pidEnabled = false;

    PIDController hatchcController = new PIDController(Constants.Hatch.HATCH_KP,
        Constants.Hatch.HATCH_KI, Constants.Hatch.HATCH_KD);

    PIDController hatchProfiledPIDController = new PIDController(Constants.Hatch.HATCH_LARGE_KP,
        Constants.Hatch.HATCH_KI, frc.robot.Constants.Hatch.HATCH_KD);

    private GenericEntry wristAngle = 
}
