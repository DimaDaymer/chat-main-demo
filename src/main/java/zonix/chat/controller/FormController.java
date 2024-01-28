package zonix.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Kontroler obsługujący żądania związane z uwierzytelnianiem.
 */
@Controller
public class FormController {

    /**
     * Wyświetla formularz indeksu.
     *
     * @return Nazwa widoku do wyświetlenia formularza indeksu.
     */
    @GetMapping("/index")
    public String showIndexForm() {
        return "index";
    }
}
