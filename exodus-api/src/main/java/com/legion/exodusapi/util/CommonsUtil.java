package com.legion.exodusapi.util;

import com.legion.exodusapi.models.Input;
import com.legion.exodusapi.models.InputSection;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommonsUtil {

   public static List<Input> fetchInputsFromSections(List<InputSection> sections) {
      return sections.stream().flatMap(s -> s.getInputs().stream()).collect(Collectors.toList());
   }

}
