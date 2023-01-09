package com.legion.exodusapi.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class InputSection {

   private String name;

   @NotEmpty
   private List<InputModel> inputs;

}
