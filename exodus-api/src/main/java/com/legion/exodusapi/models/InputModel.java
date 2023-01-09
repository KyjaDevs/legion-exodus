package com.legion.exodusapi.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InputModel {

   @NotNull
   private String name;

   @NotNull
   private String dataTypeName;

   private List<InputModel> variables;

   @NotEmpty
   private List<DialogProperty> dialogProperties;

}
