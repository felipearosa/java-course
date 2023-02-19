package com.ltp.hellospring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

  List<Grade> studentsGrades = new ArrayList<>();

  @GetMapping("/grades")
  public String getGrades(Model model) {
    model.addAttribute("grades", studentsGrades);
    return "grades";
  }

  @GetMapping("/")
  public String gradeForm(Model model, @RequestParam(required = false) String id) {
    int index = getGradeIndex(id);

    model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : studentsGrades.get(index));
    return "form";
  }

  @PostMapping("/new-grade")
  public String addGrade(Grade grade) {
    int index = getGradeIndex(grade.getId());
    if (index == Constants.NOT_FOUND) {
      studentsGrades.add(grade);
    } else {
      studentsGrades.set(index, grade);
    }

    return "redirect:/grades";
  }

  public Integer getGradeIndex(String id) {
    for (int i = 0; i < studentsGrades.size(); i++) {
      if (studentsGrades.get(i).getId().equals(id))
        return i;
    }
    return Constants.NOT_FOUND;
  }

}
