package ua.study.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.service.AddMaterialsService;

import java.util.List;
import java.util.Map;

@RequestMapping("/additional-materials")
@Controller
public class AdditionalMaterialsController {
    @Autowired
    private AddMaterialsService addMaterialsService;

    @GetMapping
    public String materials(Map<String, Object> model) {
        List<List<Object>> materials = addMaterialsService.getLecturesAndAdditionalMaterials();

        model.put("materials", materials);

        return "additional-materials";
    }
}