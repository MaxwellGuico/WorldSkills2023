package frc.robot.commands.auto;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProxyScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.Robot;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveTest;
//import frc.robot.commands.auto.MoveIRSensor;
import frc.robot.commands.auto.RotateTest;
import frc.robot.commands.auto.MoveRobotSense.end_func;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup {

    public static int testPos0[] = { 210, 1210, 0 };
    public static int testPos1[] = { 900, 900, 0 };
    public static int depoPos1[] = { 600, 3400, 0 };
    public static int homeBase[] = { 300, 300, 0 };
    public static int depoPos2[] = { 1500, 3300, 0 };
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    private  final static Points m_points = RobotContainer.m_points;

    public AutoMainCmd() {
        /*
         * 0 - Jagabee
         * 1 - Dettol
         * 2 - Coke
         */

        super(
                       
            // new ViewItem(), 
            // new LoopCommands(new ProcessSeq())
            // new MovetoB(new Pose2d(1.00, 1.33, new Rotation2d(0)))
            
            
            // new InstantCommand(()-> m_vision.setFlag(-1))
            // new MoveArm(new Translation2d(0.3,0.4), 2),
            // new InstantCommand(()-> m_arm.setCameraAngle(280)),
            // new InstantCommand(() -> RobotContainer.m_vision.setFlag(-1)),
    
            // // Move out of the way
            // new MoveRobot(0, -0.05, 0, 0, 5),
            // new MoveRobot(1, 0.25, 0, 0, 5),
    
    
            // new loopMoveRobotWaypoint(),
            // new LoopCmd(new loopMoveRobotWaypoint(), () -> Globals.endConditionCP5()),
            // new InstantCommand(() -> RobotContainer.m_vision.setFlag(-2))
            
            new CP5()
            // new MoveArm(new Translation2d(0.3,0.4), 2),
            // new MoveRobot(2,Math.PI/2,0,0, Math.PI/2),
            // new WaitCommand(3),
            // new InstantCommand(()-> m_arm.setCameraAngle(285)),
            // new InstantCommand(()-> m_vision.updatePoint("GreenTarget")),
            // new WaitCommand(2),
            // new MovetoPoint("GreenTarget")
            


        );


    }

    @Override
    public void initialize() {
        // Initialize done before base initialization

        super.initialize();
        RobotContainer.m_arm.initialize();
        RobotContainer.m_omnidrive.initialise();
        Globals.useTF = false;
        Globals.loopCount=0;
    }

}
