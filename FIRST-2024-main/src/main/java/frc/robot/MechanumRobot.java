package frc.robot;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
  Joystick leftStick = new Joystick(0);
  Joystick rightStick = new Joystick(1);
  Joystick xboxController = new Joystick(2);
  
  VictorSP FLDrive = new VictorSP(0);
  VictorSP BLDrive = new VictorSP(1);
  VictorSP FRDrive = new VictorSP(2);
  VictorSP BRDrive = new VictorSP(3);
  VictorSP ArmLift = new VictorSP(4);

  ADIS16448_IMU gyro = new ADIS16448_IMU();
  
  @Override
  public void robotInit() {
    FLDrive.set(0);
    BLDrive.set(0);
    FRDrive.set(0);
    BRDrive.set(0);
  }

  public void forward(double speed) {
    FLDrive.set(speed);
    BLDrive.set(-speed);
    FRDrive.set(speed);
    BRDrive.set(-speed);
  }

  public void sideways(double speed) {
    FLDrive.set(-speed);
    BLDrive.set(speed);
    FRDrive.set(speed);
    BRDrive.set(-speed);
  }
  
  @Override
  public void teleopPeriodic() {
    boolean leftTrigger = leftStick.getRawButton(1);
    boolean rightTrigger = rightStick.getRawButton(1);

    double y = rightStick.getRawAxis(1);
    double x = leftStick.getRawAxis(0);
    double rx = rightStick.getRawAxis(0);
    
    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    double FL = (y - x - rx) / denominator;
    double BL = (-y + x - rx) / denominator;
    double FR = (y + x + rx) / denominator;
    double BR = (-y - x + rx) / denominator;
    
    FLDrive.set(FL);
    BLDrive.set(BL);
    FRDrive.set(FR);
    BRDrive.set(BR);

    if (leftTrigger) {
      ArmLift.set(1);
    } else if (rightTrigger) {
      ArmLift.set(-1);
    } else {
      ArmLift.set(0);
    }
  }
  @Override
  public void autonomousInit() {
    gyro.calibrate();
    forward(.5);
    Timer.delay(2);
  }
  @Override
  public void autonomousPeriodic() {
    double gyroAngle = gyro.getGyroAngleX();

    if (gyroAngle > 5)
    {
      forward(.25);
    } else if (gyroAngle < -5)
    {
      forward(-.25);
    } else 
    {
      forward(0);
    } 
  }
}
