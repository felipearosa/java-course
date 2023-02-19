package com.ltp.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeSubmissionController {

  @GetMapping("/grades")
  public String getGrade(Model model) {
    Grade grade = new Grade("Harry", "Potions", "B");
    model.addAttribute("grade", grade);
    return "grades";
  }
}
