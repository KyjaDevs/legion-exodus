package com.ogame.reaper;

import com.ogame.reaper.models.MetalMine;

public class HelloApplication {

   public static void main(String[] args) {

      MetalMine metalMine = new MetalMine(5);
      System.out.println("Production: " + metalMine.getProduction());
      System.out.println("Level Up Metal cost: " + metalMine.getMetalCost());
      System.out.println("Level Up Crystal cost: " + metalMine.getCrystalCost());
      System.out.println("Energy needed: " + metalMine.getEnergyConsumption());
      System.out.println("Production duration: " + metalMine.getConstructionTime());

   }
}