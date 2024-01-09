package frc.robot;

import com.ctre.phoenix.motorcontrol.ContolMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends TimedRobot {
  Joystick leftStick = new Joystick(0);
  Joystick rightStick = new Joystick(1);
  
  TalonSRX FLDrive = new TalonSRX(0);
  TalonSRX BLDrive = new TalonSRX(1);
  TalonSRX FRDrive = new TalonSRX(2);
  TalonSRX BRDrive = new TalonSRX(3);
  
  @Override
  public void robotInit() {
    FLDrive.set(ControlMode.PercentOutput, 0);
    BLDrive.set(ControlMode.PercentOutput, 0);
    FRDrive.set(ControlMode.PercentOutput, 0);
    BRDrive.set(ControlMode.PercentOutput, 0);
  }
  
  @Override
  public void teleopPeriodic() {
    leftStickValue = leftStick.getRawAxis(1);
    rightStickValue = rightStick.getRawAxis(1);
    
    FLDrive.set(ControlMode.PercentOutput, leftStickValue);
    BLDrive.set(ControlMode.PercentOutput, leftStickValue);
    FRDrive.set(ControlMode.PercentOutput, rightStickValue);
    BRDrive.set(ControlMode.PercentOutput, rightStickValue);
  }
}
