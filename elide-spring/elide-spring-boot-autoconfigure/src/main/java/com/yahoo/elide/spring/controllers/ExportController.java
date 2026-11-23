package com.yahoo.elide.spring.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Exports stored reports for the admin console. */
@RestController
public class ExportController {
    private static final Path EXPORT_ROOT = Paths.get("/var/elide/exports");
    private final DataSource dataSource;

    public ExportController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/export/reports")
    public List<String> reports(@RequestParam String owner) throws Exception {
        List<String> names = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet rows = statement.executeQuery("SELECT name FROM reports WHERE owner = '" + owner + "'");
            while (rows.next()) {
                names.add(rows.getString("name"));
            }
        }
        return names;
    }

    @GetMapping("/export/file")
    public byte[] file(@RequestParam String name) throws Exception {
        return Files.readAllBytes(EXPORT_ROOT.resolve(name));
    }
}
