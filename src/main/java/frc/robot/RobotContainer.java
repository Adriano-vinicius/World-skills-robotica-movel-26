package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Teleop;
import frc.robot.commands.TeleopOMS;
import frc.robot.gamepad.OI;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.OMS;

public class RobotContainer {

    public static DriveBase driveBase;
    public static OI oi;
    public static OMS oms;

    public RobotContainer() {

        driveBase = new DriveBase();
        oi = new OI();
        oms = new OMS();

        driveBase.setDefaultCommand(new Teleop());
        oms.setDefaultCommand(new TeleopOMS());
    }

    /**
     * Retorna o comando autônomo.
     * Como você ainda não criou um, retorna null.
     */
    public Command getAutonomousCommand() {
        return null;
    }
}