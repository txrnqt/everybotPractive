package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot.RobotRunType;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Hatch.Hatch;
import frc.robot.subsystems.Hatch.HatchIO;
import frc.robot.subsystems.Hatch.HatchReal;
import frc.robot.subsystems.Tank.Tank;
import frc.robot.subsystems.Tank.TankIO;
import frc.robot.subsystems.Tank.TankReal;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driver = new CommandXboxController(Constants.driverID);
    private final CommandXboxController operator = new CommandXboxController(Constants.operatorID);

    // Initialize AutoChooser Sendable
    private final SendableChooser<String> autoChooser = new SendableChooser<>();

    /* Subsystems */
    private Tank tank;
    private Hatch hatch;
    private LEDs leds = new LEDs(9, 100);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer(RobotRunType runtimeType) {

        switch (runtimeType) {
            case kReal:
                tank = new Tank(new TankReal());
                hatch = new Hatch(new HatchReal(), operator);
                break;
            case kSimulation:
                tank = new Tank(new TankIO() {});
                hatch = new Hatch(new HatchIO() {}, operator);
                break;
            default:

        }
        leds.setDefaultCommand(leds.setAllianceColor().ignoringDisable(true));
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        tank.setDefaultCommand(tank.tankCMD(driver));


        /** operator controlls */
        operator.povDown().onTrue(leds.call().andThen(Commands.startEnd(() -> {
            operator.getHID().setRumble(RumbleType.kBothRumble, 1);
        }, () -> {
            operator.getHID().setRumble(RumbleType.kBothRumble, 0);
        })).withTimeout(5));
        operator.a().onTrue(hatch.homePosition());
        operator.x().onTrue(hatch.intake());
    }

    /**
     * Gets the user's selected autonomous command.
     *
     * @return Returns autonomous command selected.
     */
    public Command getAutonomousCommand() {
        Command autocommand;
        String stuff = autoChooser.getSelected();
        switch (stuff) {
            case "wait":
                autocommand = new WaitCommand(1.0);
                break;
            default:
                autocommand = new InstantCommand();
        }
        return autocommand;
    }
}
