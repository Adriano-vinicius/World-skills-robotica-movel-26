package frc.robot.subsystems;

// Biblioteca do giroscópio NavX
import com.kauailabs.navx.frc.AHRS;

// Biblioteca do controlador Titan
import com.studica.frc.Titan;
import com.studica.frc.Titan.Motor;

// Biblioteca WPILib
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Classe onde ficam todas as constantes do robô
import frc.robot.Constants;

/*
 * Classe responsável por controlar toda a base do robô.
 * Aqui ficam os motores, sensores e métodos de movimentação.
 */
public class DriveBase extends SubsystemBase
{
    //==========================================================
    // CONTROLADOR TITAN
    //==========================================================

    /*
     * O Titan representa a placa controladora inteira.
     * É através dele que acessamos cada motor e encoder.
     */
    private Titan titan;

    //==========================================================
    // MOTORES
    //==========================================================

    /*
     * Cada motor é obtido através do Titan.
     * M0, M1 e M2 são definidos no Constants.java.
     */
    private Motor leftMotor;
    private Motor rightMotor;
    private Motor backMotor;

    //==========================================================
    // SENSOR NAVX
    //==========================================================

    /*
     * Giroscópio utilizado para saber a orientação do robô.
     */
    private AHRS navx;

    //==========================================================
    // SHUFFLEBOARD
    //==========================================================

    /*
     * Cria uma aba chamada "Training Robot"
     */
    private ShuffleboardTab tab =
            Shuffleboard.getTab("Training Robot");

    /*
     * Campo que exibirá o valor do giroscópio.
     */
    private NetworkTableEntry gyroValue =
            tab.add("NavX Yaw", 0).getEntry();

    //==========================================================
    // CONSTRUTOR
    //==========================================================

    public DriveBase()
    {
        //------------------------------------------------------
        // Inicializa o controlador Titan
        //------------------------------------------------------

        titan = new Titan(Constants.TITAN_ID);

        //------------------------------------------------------
        // Obtém cada motor do Titan
        //------------------------------------------------------

        leftMotor = titan.getMotor(Constants.M2);

        rightMotor = titan.getMotor(Constants.M0);

        backMotor = titan.getMotor(Constants.M1);

        //------------------------------------------------------
        // Inicializa o NavX utilizando a porta SPI MXP
        //------------------------------------------------------

        navx = new AHRS(SPI.Port.kMXP);
    }

    //==========================================================
    // CONTROLE INDIVIDUAL DOS MOTORES
    //==========================================================

    /*
     * Controla apenas o motor esquerdo.
     */
    public void setLeftMotorSpeed(double speed)
    {
        leftMotor.set(speed);
    }

    /*
     * Controla apenas o motor direito.
     */
    public void setRightMotorSpeed(double speed)
    {
        rightMotor.set(speed);
    }

    /*
     * Controla apenas o motor traseiro.
     */
    public void setBackMotorSpeed(double speed)
    {
        backMotor.set(speed);
    }

    //==========================================================
    // CONTROLE DOS 3 MOTORES AO MESMO TEMPO
    //==========================================================

    /*
     * Define uma velocidade diferente para cada motor.
     */
    public void setDriveMotorSpeeds(double leftSpeed,
                                    double rightSpeed,
                                    double backSpeed)
    {
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
        backMotor.set(backSpeed);
    }

    //==========================================================
    // CINEMÁTICA HOLONÔMICA
    //==========================================================

    /*
     * Converte os movimentos do joystick em velocidades
     * para cada um dos três motores.
     *
     * x = direita/esquerda
     * y = frente/trás
     * z = rotação
     */
    public void holonomicDrive(double x,
                               double y,
                               double z)
    {
        // Calcula velocidade do motor direito
        double rightSpeed =
                ((x / 3) - (y / Math.sqrt(3)) + z)
                        * Math.sqrt(3);

        // Calcula velocidade do motor esquerdo
        double leftSpeed =
                ((x / 3) + (y / Math.sqrt(3)) + z)
                        * Math.sqrt(3);

        // Calcula velocidade do motor traseiro
        double backSpeed =
                (-2 * x / 3) + z;

        //------------------------------------------------------
        // Normalização
        // Evita que algum motor passe de 100%
        //------------------------------------------------------

        double max = Math.abs(rightSpeed);

        if (Math.abs(leftSpeed) > max)
            max = Math.abs(leftSpeed);

        if (Math.abs(backSpeed) > max)
            max = Math.abs(backSpeed);

        if (max > 1)
        {
            rightSpeed /= max;
            leftSpeed /= max;
            backSpeed /= max;
        }

        //------------------------------------------------------
        // Envia as velocidades aos motores
        //------------------------------------------------------

        leftMotor.set(leftSpeed);

        rightMotor.set(rightSpeed);

        backMotor.set(backSpeed);
    }

    //==========================================================
    // GIROSCÓPIO
    //==========================================================

    /*
     * Retorna o ângulo atual do robô.
     */
    public double getYaw()
    {
        return navx.getYaw();
    }

    /*
     * Zera o giroscópio.
     */
    public void resetYaw()
    {
        navx.zeroYaw();
    }

    //==========================================================
    // EXECUTA A CADA CICLO DO ROBÔ (20 ms)
    //==========================================================

    @Override
    public void periodic()
    {
        /*
         * Atualiza o valor do NavX no Shuffleboard.
         */
        gyroValue.setDouble(navx.getYaw());
    }
}