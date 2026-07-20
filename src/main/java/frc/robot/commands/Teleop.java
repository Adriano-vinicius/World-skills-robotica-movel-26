package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.gamepad.OI;
import frc.robot.subsystems.DriveBase;

public class Teleop extends CommandBase
{

    //Bring in DriveBase and OI
    private static final DriveBase driveBase = RobotContainer.driveBase;
    private static final OI oi = RobotContainer.oi;

    // imputs do joystick
    double inputLeftY = 0;
    double inputLeftX = 0;
    double inputRightX = 0;

    // ramp varriaveis
    double deltaLeftY = 0;
    double deltaLeftX = 0;
    double deltaRightX = 0;
    double prevLeftY = 0;
    double prevLeftX = 0;
    double prevRightX = 0;

    //variaveis motor
    double leftMotor = 0;
    double rightMotor = 0;
    double backMotor = 0;
    double max = 0;

    // ramp up constant
    private static final double RAMP_UP = 0.05;

    // ramp down Constant
    private static final double RAMP_DOWN = 0.05;

    // delta limit
    private static final double DELTA_LIMIT = 0.075;

    public Teleop()
    {
     addRequirements(driveBase);
    }

   @Override
   public void initialize()
   {

   }

   @Override
   public void execute()
   {
     //get joystick data
     inputLeftX = oi.getLeftDriveX();
     inputLeftY = oi.getLeftDriveY();
     inputRightX = oi.getRightDriveX();

     //Ramp
     deltaLeftX = inputLeftX - prevLeftX;
     deltaLeftY = inputLeftY - prevLeftY;
     deltaRightX = inputRightX - prevRightX;
     if(deltaLeftX >= DELTA_LIMIT)
         inputLeftX += RAMP_UP;
     else if (deltaLeftX <= -DELTA_LIMIT)
         inputLeftX -= RAMP_DOWN;
     if(deltaLeftY >= DELTA_LIMIT)
        inputLeftY += RAMP_UP;
     else if (deltaLeftY <= -DELTA_LIMIT)
        inputLeftY -= RAMP_DOWN;
     if(deltaRightX >= DELTA_LIMIT)
        inputRightX += RAMP_UP;
     else if (deltaRightX <= -DELTA_LIMIT)
        inputRightX -= RAMP_DOWN;
     prevLeftY = inputLeftY;
     prevLeftX = inputLeftX;
     prevRightX = inputRightX;

     //Holonomic Conversion

     driveBase.holonomicDrive(inputLeftX, inputLeftY, inputRightX);
   }
   @Override
   public void end (boolean interrupted)
   {
      driveBase.setDriveMotorSpeeds(0.0, 0.0, 0.0);
   }

   @Override
   public boolean isFinished()
   {
       return false;
   }
}