package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Robot extends TimedRobot {
  Joystick xboxController = new Joystick(0);
  
  private final PWMSparkMax front_left = new PWMSparkMax(0);
  private final PWMSparkMax back_left = new PWMSparkMax(1);
  private final PWMSparkMax front_right = new PWMSparkMax(2);
  private final PWMSparkMax back_right = new PWMSparkMax(3);
  MotorControllerGroup left = new MotorControllerGroup(front_left, back_left);
  MotorControllerGroup right = new MotorControllerGroup(front_right, back_right);
  private final DifferentialDrive drive = new DifferentialDrive(left, right);

  private final XboxController controller = new XboxController(0);
  private final Timer timer = new Timer();

  ADIS16448_IMU gyro = new ADIS16448_IMU();
  
  @Override
  public void robotInit() {
    drive.stopMotor();
    drive.setInverted(true);
  }
  
  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(-m_controller.getLeftY(), -m_controller.getRightX());
  }
  
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }
  
  @Override
  public void autonomousPeriodic() {
    
  }
}

