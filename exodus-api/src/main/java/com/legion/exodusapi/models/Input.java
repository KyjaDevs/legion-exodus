package com.legion.exodusapi.models;

import lombok.Data;

import java.util.List;

@Data
public class Input {

   private String name;

   private String type;

   private String expectedValue;

   private List<DialogProperty> properties;

}
