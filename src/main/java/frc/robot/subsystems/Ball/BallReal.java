package frc.robot.subsystems.Ball;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class BallReal implements BallIO {
    /**
     * initilize motors and senors
     */
    private final CANSparkMax ballMotor =
        new CANSparkMax(Constants.Ball.BALLMOTOR, MotorType.kBrushless);
    private final DigitalInput intakeBeamBreak = new DigitalInput(Constants.Ball.BEAMBREAK1);
    private final DigitalInput outtakeBeamBreak = new DigitalInput(Constants.Ball.BEAMBREAK2);

    public BallReal() {}

    @Override

    /**
     * sets power for motors
     */
    public void setPower(double power) {
        ballMotor.set(power);
    }


    public void periodic() {}

    public void updateInputs(BallIOInputs inputs) {
        inputs.intake = intakeBeamBreak.get();
        inputs.outtake = outtakeBeamBreak.get();
    }
}
