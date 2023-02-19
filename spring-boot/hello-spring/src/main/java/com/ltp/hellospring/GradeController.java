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
  public String gradeForm(Model model, @RequestParam(required = false) String name) {
    Grade grade;
    if (getGradeIndex(name) == -1000) {
      grade = new Grade();
    } else {
      grade = studentsGrades.get(getGradeIndex(name));
    }

    model.addAttribute("grade", grade);
    return "form";
  }

  @PostMapping("/new-grade")
  public String addGrade(Grade grade) {
    int index = getGradeIndex(grade.getName());
    if (index == -1000) {
      studentsGrades.add(grade);
    } else {
      studentsGrades.set(index, grade);
    }

    return "redirect:/grades";
  }

  public Integer getGradeIndex(String name) {
    for (int i = 0; i < studentsGrades.size(); i++) {
      if (studentsGrades.get(i).getName().equals(name))
        return i;
    }
    return -1000;
  }

}
