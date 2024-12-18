package frc.lib.math.sim;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class SimulatedBot {
    public final int id;

    SimulatedBot(int id) {
        this.id = id;
    }

    private Pose2d pose = new Pose2d();
    private double intake = 0.0;
    private Rotation2d hatchAngle = new Rotation2d();
    private boolean hasHatch = true;
    private boolean intakeBeam = true;
    private boolean outtakeBeam = true;
    private double ballSpeed = 1.0;

    public void setBallSpeed(double speed) {
        this.ballSpeed = speed;
    }

    public Pose2d getpose() {
        return pose;
    }

    public void setPose(Pose2d pose) {
        this.pose = pose;
    }

}
