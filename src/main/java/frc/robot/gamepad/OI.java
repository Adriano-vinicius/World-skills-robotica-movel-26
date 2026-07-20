package frc.robot.gamepad;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    //Criando o joystick
    Joystick drivePad;

    public OI ()
    {
        drivePad = new Joystick(GamepadConstants.DRIVE_USB_PORT);
    }


    public double getRightDriveX()
    {
        double joy = drivePad.getRawAxis(GamepadConstants.RIGHT_ANALOG_X);
        if(Math.abs(joy) < 0.85)
          return 0.0;
        else
          return joy;
    }

    public double getLeftDriveX()
    {
        double joy = drivePad.getRawAxis(GamepadConstants.LEFT_ANALOG_X);
        if(Math.abs(joy) < 0.85)
          return 0.0;
        else
          return joy;
    }

    public double getLeftDriveY()
    {
        double joy = drivePad.getRawAxis(GamepadConstants.LEFT_ANALOG_Y);
        if(Math.abs(joy) < 0.85)
          return 0.0;
        else
          return joy;
    }

    //botãos
    public boolean getDriveRightTrigger()
    {
        return drivePad.getRawButton(GamepadConstants.RIGHT_TRIGGER);
    }

    public boolean getDriveRightBumper()
    {
        return drivePad.getRawButton(GamepadConstants.RIGHT_BUMPER);
    }

    public boolean getDriveLeftBumper()
    {
        return drivePad.getRawButton(GamepadConstants.LEFT_BUMPER);
    }
}