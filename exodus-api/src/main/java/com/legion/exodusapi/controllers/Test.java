package com.legion.exodusapi.controllers;

import com.google.gson.Gson;
import com.legion.exodusapi.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class Test {

   private <T> T parseJson(String jsonPath, Class<T> objClass) throws IOException {
      Reader reader = Files.newBufferedReader(Paths.get(jsonPath));
      return new Gson().fromJson(reader, objClass);
   }

   @GetMapping("/test")
   public void test() throws IOException {
      String filePath = "src/main/resources/static/request.json";
      String modelPath = new File(filePath).getAbsolutePath();
      ComponentRequest component = parseJson(modelPath, ComponentRequest.class);

      SlingModelWriter.write(component.getSlingModel());
      // ComponentDialogWriter
      // ComponentHTMLWriter
      // ComponentFiles (.content.xml, folder structure...)

   }

}
