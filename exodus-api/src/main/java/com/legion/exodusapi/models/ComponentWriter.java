package com.legion.exodusapi.models;

import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.FileWriter;
import java.io.IOException;

public class ComponentWriter extends FileWriter {

   public ComponentWriter(String fileName) throws IOException {
      super(fileName);
   }

   public void writeLine(String text) throws IOException {
      this.write(text);
      this.write(System.lineSeparator());
   }

}
