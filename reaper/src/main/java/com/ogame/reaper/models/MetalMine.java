package com.ogame.reaper.models;

import com.ogame.reaper.constants.CommonConstants;

public class MetalMine extends ResourceMine {

   private static final int BASE_METAL_COST = 60;
   private static final int BASE_CRYSTAL_COST = 15;
   private static final int BASE_ENERGY_CONSUMPTION = 10;
   private static final int BASE_PRODUCTION = 30;
   private static final double COST_RATIO = 1.5;
   private static final double PRODUCTION_FACTOR = 1.1;
   private static final double ENERGY_FACTOR = 1.1;

   public MetalMine(int level) {
      super(level, BASE_METAL_COST, BASE_CRYSTAL_COST, COST_RATIO);
   }

   @Override
   public int getProduction() {
      return (int) (BASE_PRODUCTION * CommonConstants.ACCELERATION_FACTOR * this.level * Math.pow(PRODUCTION_FACTOR, this.level));
   }

   @Override
   public int getEnergyConsumption() {
      return (int) (BASE_ENERGY_CONSUMPTION * this.level * Math.pow(ENERGY_FACTOR, this.level));
   }
}
