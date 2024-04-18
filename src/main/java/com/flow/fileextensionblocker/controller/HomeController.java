package com.flow.fileextensionblocker.controller;

import com.flow.fileextensionblocker.repository.*;
import com.flow.fileextensionblocker.service.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FileExtensionService service;
    private final CustomExtensionRepository customExtensionRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("defaultExtensionList", service.findAllDefaultExtension());
        model.addAttribute("customExtensionList", service.findAllCustomExtension());
        model.addAttribute("count", customExtensionRepository.count());

        return "home";
    }
}
