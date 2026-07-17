/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //definir ID do CAN do titan e vmx
    public static final int TITAN_ID = 42;

    //constantes da base de condução

      /**
       * Motores
       */
      public static final int M0 = 0; //Direita
      public static final int M1 = 1; //Traseira
      public static final int M2 = 2; //Esquerda

      /**
       * Encoders (sensor acoplado ao motor que mede quanto e quão rápido o eixo do motor gira.)
       */

       // Raio da roda Omini Wheel com diametro = 102mm, 
       // serve para saber quanto a roda anda em uma volta
       public static final double wheelRadius = 51; //mm

       //Encoder Pulsos por revolução
       //O encoder gera 1440 pulsos (tick) quando o eixo dá uma volta completa
       public static final double pulsePerRevolution = 1440;

       //Relação de engrenagem entre o codificador e a roda
       public static final double gearRatio = 1/1;

       //Pulsos por volta da roda wheel
       public static final double wheelPulseRatio = pulsePerRevolution * gearRatio;

       //Distâcia por tick
       public static final double WHELL_DIST_PER_TICK = (Math.PI * 2 * wheelRadius) / wheelPulseRatio;

    /**
     * Constantes Elevador
     */

        /**
         * Motores Elevador
         */
        public static int M3 = 3; // Elevator motor
        public static int DIF_SERVO = 0; // Differential servo

        /**
         * Encoder
         */

         //Raio da polia da correia
         public static final double pulleyRadius = 7.85; //mm

         //Pulsos do encoder por revolution
         public static final double pulsePerRevElevator = 1440;

         //Relação de engrenagem entre o encoder e a polia
         public static final double elevatorGeanRatio = 1.0/2.0;

         //Pulsos por revolução da polia
         public static final double pulleyPulseRatio = pulsePerRevElevator * elevatorGeanRatio;

         //Distância por tick
         public static final double ELEVATOR_DIST_TICK = (Math.PI * 2 * pulleyRadius) / pulleyPulseRatio;

}
