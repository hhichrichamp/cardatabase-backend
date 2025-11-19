package com.champsoft.cardatabase;



public class test{

    public test() {
        // Given values
        double v0 = 0;  // initial velocity in km/h
        double a = 20;  // acceleration in km/h^2
        System.out.printf("%30s %30s" , "Time(hours)" , "Distance(klm)");
        for (int t=0; t<=4; t++){
            // Kinematic equation for distance traveled
            double s = v0 * t + 0.5 * a * Math.pow(t, 2);
            System.out.printf("%30d %30.2f" , t , s);
        }
    }

    public class RocketSimulation {
        public static void main(String[] args) {
            double h = 0;       // altitude (m)
            double v = 0;       // velocity (m/s)
            double a = 30;      // thrust acceleration (m/s^2)
            double k = 0.0002;  // drag coefficient
            double dt = 0.1;    // time step (s)
            double t = 0;       // control variable: time (seconds)
            double targetHeight = 10000; // meters

            while (h < targetHeight) {
                v = v + (a - k * v * v) * dt;
                h = h + v * dt;
                t = t + dt; // control variable update

                if (t > 5000) { // safety to avoid infinite loop
                    System.out.println("Simulation aborted — too long!");
                    break;
                }
            }

            System.out.printf("Rocket reached %.2f m after %.1f seconds.%n", h, t);
        }
    }



}