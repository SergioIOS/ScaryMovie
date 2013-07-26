/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import org.lwjgl.Sys;

/*
Copyright (c) 2005, Corey Goldberg

StopWatch.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.
*/

// Converted from System to LWJGL Sys time - dimecoin
// See: http://slick.javaunlimited.net/viewtopic.php?p=10177#10177
// Added reset method

public class Timer {
   private long startTime = 0;
   private long stopTime = 0;
   private boolean running = false;

   public void start() {
      this.startTime = Sys.getTime();
      this.running = true;
   }

   public void stop() {
      this.stopTime = Sys.getTime();
      this.running = false;
   }

   // elaspsed time in milliseconds
   public long getElapsedTime() {
      long elapsed;
      if (running) {
         elapsed = (Sys.getTime() - startTime);
      } else {
         elapsed = (stopTime - startTime);
      }
      return elapsed;
   }

   // elaspsed time in seconds
   public long getElapsedTimeSecs() {
      long elapsed;
      if (running) {
         elapsed = ((Sys.getTime() - startTime) / 1000);
      } else {
         elapsed = ((stopTime - startTime) / 1000);
      }
      return elapsed;
   }

   public void reset() {
      stop();
      running = false;
      startTime = 0;
      stopTime = 0;
   }

   public boolean isRunning() {
      return running;
   }
   // sample usage
   /*
    * public static void main(String[] args) { StopWatch s = new StopWatch();
    * s.start(); //code you want to time goes here s.stop();
    * System.out.println("elapsed time in milliseconds: " +
    * s.getElapsedTime()); }
    */
}