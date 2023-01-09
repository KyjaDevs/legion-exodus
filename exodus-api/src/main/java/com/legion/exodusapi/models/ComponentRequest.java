package com.legion.exodusapi.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ComponentRequest {

   @NotBlank
   private String name;

   private String coreModelsPath;

   private String coreModelsTestPath;

   private String coreJsonModelsPath;

   private String appComponentPath;

   private List<InputSection> sections;

}
