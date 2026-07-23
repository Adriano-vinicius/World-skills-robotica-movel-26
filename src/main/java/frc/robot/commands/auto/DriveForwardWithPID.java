package frc.robot.commands.auto;

import frc.robot.commands.driveCommands.DriveWithPID;

public class DriveForwardWithPID extends AutoCommand
{
    public DriveForwardWithPID () 
    {
        super(new DriveWithPID(1000, 10 , 0.0, 1.0).withTimeout(5));
    }
}