package frc.robot.subsystems.Ball;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class BallReal implements BallIO {
    private final CANSparkMax ballMotor = new CANSparkMax(12, MotorType.kBrushless);

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
