/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/* everything executes in this order:
 * robotInit
 * autonomousInit
 * autonomousPeriodic
 * teleopInit
 * teleopPeriodic
 */

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;



/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {	
  //private final Timer m_timer = new Timer();
  public DifferentialDrive m_drive;
  public SpeedControllerGroup m_left;
  public SpeedControllerGroup m_Right;
  public Joystick m_leftStick;
  public Joystick m_rightStick;
  public Joystick xboxController;
  public Talon ballShooter;
  public Talon m_FrontLeft;
  public Talon m_rearLeft;
  public Talon m_frontRight;
  public Talon m_rearRight;
  public Talon spinnerMotor;
  public Talon intake;
  public Talon feeder;
  public Talon climb;

  @Override
  public void robotInit() { //TODO what are the "m_"?
    m_leftStick    = new Joystick(0);
    m_rightStick   = new Joystick(1);
    xboxController = new Joystick(2);

    m_FrontLeft    = new Talon(2);
    m_rearLeft     = new Talon(3);
    m_left         = new SpeedControllerGroup(m_FrontLeft, m_rearLeft);

    m_frontRight   = new Talon(1);
    m_rearRight    = new Talon(0);
    m_Right        = new SpeedControllerGroup(m_frontRight, m_rearRight);

    m_drive        = new DifferentialDrive(m_left, m_Right);

    intake         = new Talon(6); 
    climb          = new Talon(4);
    feeder         = new Talon(7);
    spinnerMotor   = new Talon(8);
    shooter        = new Talon(5);
  }

  //will pause the program for mili miliseconds
  public void wait(int mili) { //TODO wait not used, timer is used instead
    try {
      Thread.sleep(mili);
    } catch ( InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  //will only run once before teleop
  @Override
  public void teleopInit( ) {
    
  }

  //will run over and over agaid during teleop
  @Override
  public void teleopPeriodic() {
    boolean intakeButtonIsPressed = xboxController.getRawButton(4);
    double rightTrigger = xboxController.getRawAxis(3);
    boolean AButton = xboxController.getRawButton(1);
    boolean BButton = xboxController.getRawButton(3);
    boolean XButton = xboxController.getRawButton(2);
    boolean RT = m_rightStick.getRawButton(1);
    boolean XLT = xboxController.getRawButton(5);
    boolean XRT = xboxController.getRawButton(6);
    boolean select  = xboxController.getRawButton(8);

    m_drive.tankDrive(-m_leftStick.getRawAxis(1), -m_rightStick.getRawAxis(1));
    if (intakeButtonIsPressed) {
      intake.setSpeed(1);
    } else {
      intake.setSpeed(0);
    }
    
    ballShooter.set(rightTrigger);
    
    if (AButton) { 
      spinnerMotor.setSpeed(1);
    } else {
      spinnerMotor.setSpeed(0);
    }
    
    if (BButton) {
      feeder.setSpeed(1);
      //spinnerMotor.setSpeed(1);
    }
    
    else {
      feeder.setSpeed(0);
      //spinnerMotor.setSpeed(0);
    }
    
   
    if (XButton) {
      feeder.setSpeed(1);
      intake.setSpeed(1);
    }
    
    if (XLT){
      climb.setSpeed(0.1);
    }else if (XRT) {
      climb.setSpeed(0.45);
    }else {
      climb.setSpeed(0);
    }
    
    if (select){
      spinnerMotor.setSpeed(1);
    }else {
      spinnerMotor.setSpeed(0);
    }
}

  @Override
  public void autonomousInit() {
    m_drive.tankDrive(.5, .5);
    Timer.delay(4);
    m_drive.tankDrive(0, 0);

  }

  @Override
  public void autonomousPeriodic() {}
    
}
