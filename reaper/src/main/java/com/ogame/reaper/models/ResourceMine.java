package com.ogame.reaper.models;

import com.ogame.reaper.constants.CommonConstants;
import com.ogame.reaper.constants.CommonUtils;
import com.ogame.reaper.constants.ResearchLab;

abstract class ResourceMine {
   protected final int level;
   protected final int metalBaseCost;
   protected final int crystalBaseCost;
   protected final double costRatio;

   public ResourceMine(int level, int metalBaseCost, int crystalBaseCost, double costRatio) {
      this.level = level;
      this.metalBaseCost = metalBaseCost;
      this.crystalBaseCost = crystalBaseCost;
      this.costRatio = costRatio;
   }

   public int getMetalCost() {
      return (int) (metalBaseCost * Math.pow(costRatio, level));
   }

   public int getCrystalCost() {
      return (int) (crystalBaseCost * Math.pow(costRatio, level));
   }

   public int getConstructionTime() {
      int totalResources = getMetalCost() + getCrystalCost();
      int roboticsFactoryFactor = 1 + ResearchLab.ROBOTICS_FACTORY_LEVEL;
      double naniteFactoryFactor = Math.pow(2, ResearchLab.NANITE_FACTORY_LEVEL);
      double hours = (totalResources / (2500 * roboticsFactoryFactor * naniteFactoryFactor * CommonConstants.ACCELERATION_FACTOR));
      int seconds = CommonUtils.convertHoursToSeconds(hours);
      if (level < 5) seconds = seconds * 2 / (7 - level);
      return seconds;
   }

   public int getLevel() {
      return level;
   }

   abstract int getProduction();

   abstract int getEnergyConsumption();

}
