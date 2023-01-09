package com.legion.exodusapi.models;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SlingModel {

   @NotNull
   private String className;

   @NotNull
   private String comments;

   @NotNull
   private String directoryPath;

   @NotEmpty
   private List<@Valid Variable> variables;

}
