package com.aphabankTest.Task;

import com.aphabankTest.Task.giphy.GifUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * main controller class
 */
@Controller
public class MainController {
    /**
     * route to localhost
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * route that allows you to see gif by currency request
     * @return html template
     */
    @GetMapping("/get/{currency}")
    public String test(Model model, @PathVariable String currency) {
        System.out.println(ApiRequester.getRandomGifUrl(currency));
        GifUrl gifUrl = ApiRequester.getRandomGifUrl(currency);
        if (gifUrl != null) {
            model.addAttribute("url", gifUrl.url);
            model.addAttribute("embed", gifUrl.embedUrl);
        }
        return "index";
    }

}