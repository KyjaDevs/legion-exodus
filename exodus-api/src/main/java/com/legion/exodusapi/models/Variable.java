package com.legion.exodusapi.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Variable {

   @NotNull
   private String type;

   @NotNull
   private String name;
}
