package com.legion.exodusapi.models;

import com.legion.exodusapi.consants.CommonConsts;
import com.legion.exodusapi.consants.FileExts;
import com.legion.exodusapi.consants.FileTypes;
import com.legion.exodusapi.util.CommonsUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlingModelTestWriter extends ComponentWriter {

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




   private final String FILE_EXT = ".java";

   public SlingModelTestWriter(String fileName) throws IOException {
      super(fileName);
   }

   public void write(ComponentRequest component) throws IOException {
      final String filePath = CommonConsts.FOLDER_PATH + component.getName() + "ModelTest" + FILE_EXT;

      writeClass(component.getName());
      writeVariables(component.getName());
      writeVariableTestings(component);
      writeEnd();
   }

   private void writeClass(String className) throws IOException {
      writeClassModelAnnotation();
      writeLine("public class " + className + " {");
      writeLine(System.lineSeparator());
   }

   private void writeClassModelAnnotation() throws IOException {
      writeLine("@ExtendWith(AemContextExtension.class)");
   }

   private void writeVariables(String className) throws IOException {
      String classVariableName = Character.toLowerCase(className.charAt(0)) + className.substring(1);
      writeLine("private final AemContext context = AppAemContext.newAemContext();");
      writeLine(System.lineSeparator());
      writeLine("private " + className + "Model " + classVariableName + "Model;");
      writeLine(System.lineSeparator());
   }

   private void writeBeforeEach(String className, TestProperties properties) throws IOException {
      String classVariableName = Character.toLowerCase(className.charAt(0)) + className.substring(1);
      writeLine("@BeforeEach");
      writeLine("public void setup() {");
      writeLine("context.addModelsForClasses(" + className + "Model.class);");
      writeLine("context.load().json(\"" + properties.getJsonModelPath() + "\", \"/content\");");
      writeLine("context.currentResource(\"/content/" + classVariableName + "\");");
      writeLine(classVariableName + " = context.currentResource().adaptTo(" + className + "Model.class);");
      writeLine("}");
   }

   private void writeVariableTestings(ComponentRequest component) throws IOException {
      List<Input> inputs = CommonsUtil.fetchInputsFromSections(component.getSections());
      for (Input input : inputs) {
         String methodNamePart = Character.toUpperCase(input.getName().charAt(0)) + input.getName().substring(1);
         String modelMethodName = Character.toLowerCase(input.getName().charAt(0)) + input.getName().substring(1);
         writeLine("@Test");
         writeLine(System.lineSeparator());
         writeLine("public void testGet" + methodNamePart + "() throws NullPointerException {");
         writeLine(System.lineSeparator());
         writeLine("final String expected = \"" + input.getExpectedValue() + "\";");
         writeLine(System.lineSeparator());
         writeLine(input.getType() + " " + input.getName() + " = " + component.getName() + "Model.get" + modelMethodName);
         writeLine(System.lineSeparator());
      }
   }

   private void writeEnd() throws IOException {
      writeLine("}");
      this.close();
   }

}
