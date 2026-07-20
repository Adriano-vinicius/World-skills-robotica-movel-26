package frc.robot.subsystems;

// Biblioteca Studica
import com.studica.frc.Servo;
import com.studica.frc.Titan;
import com.studica.frc.Titan.Motor;

// Biblioteca WPILib
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Constantes do robô
import frc.robot.Constants;

/*
 * OMS (Object Manipulation System)
 *
 * Este subsystem controla:
 * - Elevador
 * - Garra (Servo)
 */
public class OMS extends SubsystemBase
{
    //==========================================================
    // CONTROLADOR TITAN
    //==========================================================

    /*
     * Representa toda a placa Titan.
     */
    private Titan titan;

    //==========================================================
    // MOTORES
    //==========================================================

    /*
     * Motor responsável pelo elevador.
     */
    private Motor elevator;

    /*
     * Servo responsável pela garra.
     */
    private Servo claw;

    //==========================================================
    // SHUFFLEBOARD
    //==========================================================

    /*
     * Aba onde serão exibidos os dados do OMS.
     */
    private ShuffleboardTab tab =
            Shuffleboard.getTab("Training Robot");

    //==========================================================
    // CONSTRUTOR
    //==========================================================

    public OMS()
    {
        //------------------------------------------------------
        // Inicializa a placa Titan
        //------------------------------------------------------

        titan = new Titan(Constants.TITAN_ID);

        //------------------------------------------------------
        // Obtém o motor do elevador
        //------------------------------------------------------

        elevator = titan.getMotor(Constants.M2);

        //------------------------------------------------------
        // Inicializa o servo da garra
        //------------------------------------------------------

        claw = new Servo(Constants.DIF_SERVO);
    }

    //==========================================================
    // ELEVADOR
    //==========================================================

    /*
     * Define a velocidade do elevador.
     *
     * -1 = sobe totalmente
     *  0 = parado
     *  1 = desce totalmente
     */
    public void setElevatorMotorSpeed(double speed)
    {
        elevator.set(speed);
    }

    //==========================================================
    // GARRA
    //==========================================================

    /*
     * Define o ângulo do servo.
     *
     * Exemplo:
     * 0°   = fechado
     * 150° = aberto
     */
    public void setServoPosition(double degrees)
    {
        claw.setAngle(degrees);
    }

    //==========================================================
    // EXECUTA A CADA 20ms
    //==========================================================

    @Override
    public void periodic()
    {
        /*
         * Futuramente você poderá atualizar
         * encoder, corrente do motor,
         * posição do servo etc.
         */
    }
}