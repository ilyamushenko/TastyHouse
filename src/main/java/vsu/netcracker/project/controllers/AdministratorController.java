package vsu.netcracker.project.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    private String type;

    @GetMapping
    public Map<String, String> please() {
        System.out.println("priiint");
        HashMap<String, String> json = new HashMap<>();
        json.put("1", "first");
        json.put("2", "second");
        json.put("3", "third");

        return json;
    }

    @PostMapping
    public Map<String, String> post(@RequestBody Map<String, String> selectedType){
        type = (String) selectedType.values().toArray()[0];
        System.out.println(type);
        return selectedType;
    }

}
