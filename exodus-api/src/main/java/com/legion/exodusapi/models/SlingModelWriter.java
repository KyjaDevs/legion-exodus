package com.legion.exodusapi.models;

import com.legion.exodusapi.consants.CommonConsts;
import com.legion.exodusapi.consants.FileExts;
import com.legion.exodusapi.consants.FileTypes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlingModelWriter {

   private static final List<String> fileLines = new ArrayList<>();

   public static void write(SlingModel slingModel) throws IOException {
      setFileLines(slingModel);
      createFile(slingModel.getClassName());
   }

   private static void setFileLines(SlingModel slingModel) {
      setFileImports();
      setAuthorComments(slingModel.getComments());
      setClassAnnotations();
      setClassInit(slingModel.getClassName());
      setClassVariables(slingModel.getVariables());
      setClassEnd();
   }

   private static void createFile(String className) throws IOException {
      final String fileName = createFileName(className);
      FileWriter writer = new FileWriter(fileName);
      for (String line : fileLines) {
         writer.write(line);
         writer.write(System.lineSeparator());
      }
      writer.close();
   }

   private static void setFileImports() {
      fileLines.add(CommonConsts.LOMBOK_GETTER_IMPORT);
      fileLines.add(CommonConsts.IMPORT_SLING_MODEL);
      fileLines.add(CommonConsts.IMPORT_SLING_DEFAULT_INJECTION_STRATEGY);
      fileLines.add(CommonConsts.IMPORT_SLING_RESOURCE);
      fileLines.add(CommonConsts.IMPORT_JAVAX_INJECT);
   }

   private static void setAuthorComments(String comments) {
      fileLines.add("/**");
      fileLines.add(" * @author Calleja Víctor " + comments);
      fileLines.add("*/");
   }

   private static void setClassAnnotations() {
      final String modelAnnotation = "@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)";
      fileLines.add(CommonConsts.LOMBOK_GETTER_ANNOTATION);
      fileLines.add(modelAnnotation);
   }

   private static void setClassInit(String className) {
      fileLines.add("public class " + className + " {");
   }

   private static void setClassVariables(@NotEmpty List<Variable> variables) {
      for (Variable variable : variables) {
         fileLines.add(createVariableLine(variable));
      }
   }

   private static void setClassEnd() {
      fileLines.add("}");
   }

   private static String createVariableLine(@NotNull Variable variable) {
      return CommonConsts.JAVAX_INJECT_ANNOTATION + " private " + variable.getType() + " " + variable.getName();
   }

   private static String createFileName(String className) {
      return CommonConsts.FOLDER_PATH + className + FileTypes.MODEL + FileExts.JAVA;
   }

}
