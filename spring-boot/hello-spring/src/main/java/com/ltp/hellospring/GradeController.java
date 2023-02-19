package com.ltp.hellospring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {

  List<Grade> studentsGrades = Arrays.asList(
      new Grade("Harry", "Potions", "B"),
      new Grade("Hermine", "Arithmancy", "A"),
      new Grade("Ron", "Charms", "B-"));

  @GetMapping("/grades")
  public String getGrades(Model model) {
    model.addAttribute("grades", studentsGrades);
    return "grades";
  }

  @GetMapping("/")
  public String gradeForm(Model model) {
    model.addAttribute("grade", new Grade());
    return "form";
  }
}
