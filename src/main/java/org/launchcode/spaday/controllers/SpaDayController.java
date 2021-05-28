package org.launchcode.spaday.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class SpaDayController {

    public boolean checkSkinType(String skinType, String facialType) {
        if (skinType.equals("oily")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating");
        }
        else if (skinType.equals("combination")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel");
        }
        else if (skinType.equals("dry")) {
            return facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial");
        }
        else {
            return true;
        }
    }

    @GetMapping(value="")
//    @ResponseBody
    public String customerForm () {
//        String html = "<form method = 'post'>" +
//                "Name: <br>" +
//                "<input type = 'text' name = 'name'>" +
//                "<br>Skin type: <br>" +
//                "<select name = 'skintype'>" +
//                "<option value = 'oily'>Oily</option>" +
//                "<option value = 'Combination'>Combination</option>" +
//                "<option value = 'Normal'>Normal</option>" +
//                "<option value = 'Dry'>Dry</option>" +
//                "</select><br>" +
//                "Manicure or Pedicure? <br>" +
//                "<select name = 'manipedi'>" +
//                "<option value = 'Manicure'>Manicure</option>" +
//                "<option value = 'Pedicure'>Pedicure</option>" +
//                "</select><br>" +
//                "<input type = 'submit' value = 'Submit'>" +
//                "</form>";
        return "spaMenu";
    }

    @PostMapping(value="")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String manipedi, Model model) {

        ArrayList<String> facials = new ArrayList<>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        ArrayList<String> appropriateFacials = new ArrayList<>();
        for (int i = 0; i < facials.size(); i ++) {
            if (checkSkinType(skintype,facials.get(i))) {
                appropriateFacials.add(facials.get(i));
            }
        }
        model.addAttribute("name", name);
        model.addAttribute("skintype", skintype);
        model.addAttribute("manipedi", manipedi);
        model.addAttribute("appropriateFacials", appropriateFacials);

        return "menu";
    }
}
