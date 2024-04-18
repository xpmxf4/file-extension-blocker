package com.flow.fileextensionblocker.controller;


import com.flow.fileextensionblocker.dto.request.*;
import com.flow.fileextensionblocker.dto.response.*;
import com.flow.fileextensionblocker.service.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequiredArgsConstructor
public class FileExtensionController {

    private final FileExtensionService extensionService;

    @PostMapping("/api/extension")
    public ResponseEntity<HttpStatus> addCustomExtension(@Valid @RequestBody CustomExtensionRequestDto extensionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        extensionService.addCustomExtension(extensionDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/extension/{name}")
    public ResponseEntity<HttpStatus> removeCustomExtension(@PathVariable(name = "name") String name) {
        extensionService.removeCustomExtension(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/extension")
    public ResponseEntity<List<CustomExtensionResponseDto>> findAllCustomExtension() {
        return ResponseEntity.ok(extensionService.findAllCustomExtension());
    }

    @GetMapping("/api/extension/default")
    public ResponseEntity<List<DefaultExtensionResponseDto>> findAllDefaultExtension() {
        return ResponseEntity.ok(extensionService.findAllDefaultExtension());
    }

    @PatchMapping("/api/extension/{name}")
    public ResponseEntity<HttpStatus> modifyDefaultExtension(@PathVariable(name = "name") String name) {
        extensionService.modifyDefaultExtension(name);
        return ResponseEntity.ok().build();
    }
}
