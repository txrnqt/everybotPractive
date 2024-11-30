package frc.robot.subsystems.Ball;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class BallReal implements BallIO {
    private final CANSparkMax ballMotor =
        new CANSparkMax(Constants.Ball.BALLMOTOR, MotorType.kBrushless);
    private final DigitalInput beamBreak1 = new DigitalInput(Constants.Ball.BEAMBREAK1);
    private final DigitalInput beamBreak2 = new DigitalInput(Constants.Ball.BEAMBREAK2);

    public BallReal() {}

    /**
     * sets power for motors
     */
    public void setPower(double power) {
        ballMotor.set(power);
    }

    public void periodic() {}

    public void updateInputs() {}
}
