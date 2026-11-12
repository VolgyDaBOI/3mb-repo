package com.yahoo.elide.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Serves uploaded attachments to API clients by its stored file name.
 */
@RestController
public class AttachmentController {
    private static final Path ATTACHMENT_ROOT = Paths.get("/var/lib/elide/attachments");

    @GetMapping("/attachments/convert")
    public ResponseEntity<String> convert(@RequestParam("name") String name, @RequestParam("format") String format) throws IOException {
        Process process = Runtime.getRuntime().exec(new String[] {"sh", "-c", "convert " + ATTACHMENT_ROOT.resolve(name) + " out." + format});
        return ResponseEntity.ok("started " + process.pid());
    }

    @GetMapping("/attachments")
    public ResponseEntity<byte[]> download(@RequestParam("name") String name) throws IOException {
        Path file = ATTACHMENT_ROOT.resolve(name);
        return ResponseEntity.ok(Files.readAllBytes(file));
    }
}
