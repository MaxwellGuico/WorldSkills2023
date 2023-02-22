package frc.robot.commands.auto;
import com.studica.frc.Servo;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

//Subsystem imports


/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveCamera extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal;
    private TrapezoidProfile.State m_setpoint;
    private TrapezoidProfile m_profile;


    private final double tgt_pos;
    private final Arm m_arm = RobotContainer.m_arm;
    private final double maxSpeed = 75;
    /**
     * This command moves the camera
     * <p>
     * 
     * @param pos - target angle
     * 
     */
    
    public MoveCamera(double pos)
    {
        m_constraints = new TrapezoidProfile.Constraints(maxSpeed, maxSpeed);
        tgt_pos = pos;
       

    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        double start_pos = m_arm.getCameraAngle();
        
        m_goal = new TrapezoidProfile.State(tgt_pos, 0);
        m_setpoint = new TrapezoidProfile.State(start_pos, 0);
        m_endFlag = false;
    }
    /**
     * Condition to end speed profile
     */
    public boolean endCondition()
    {
        return false;
    }
    /**
     * Called continously until command is ended
     */
    @Override
    public void execute()
    {
        //Create a new profile to calculate the next setpoint(speed) for the profile

        m_profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = m_profile.calculate(dT);

        m_arm.setCameraAngle( m_setpoint.position);

        if ((m_profile.isFinished(dT)) ) {
            //distance reached End the command
            m_arm.setCameraAngle( m_goal.position);
            m_endFlag = true;
        }
    }

    /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    public void end(boolean interrupted)
    {

    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        return m_endFlag;
    }

}