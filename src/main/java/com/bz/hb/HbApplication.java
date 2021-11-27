package com.bz.hb;

/**
 * created by srana on 7/10/20 at 12.30 AM
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Log4j2
@SpringBootApplication
public class HbApplication implements CommandLineRunner {
    @Value("${app.data.upload.dir:${user.home}}")
    public String dataUploadDir;

    public static void main(String[] args) {
        SpringApplication.run(HbApplication.class, args);
    }


    @Override
    public void run(String... args) {
        File confFile = new File(dataUploadDir);
        try {
            Files.createDirectories(confFile.toPath());
        } catch (IOException e) {
            log.error("Failed to create upload directory");
        }
    }
}
