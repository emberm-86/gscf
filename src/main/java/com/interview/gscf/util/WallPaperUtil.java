package com.interview.gscf.util;

import io.vavr.control.Try;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class WallPaperUtil {

    public static final Logger LOGGER = Logger.getLogger(WallPaperUtil.class);

    public static URI loadFile(String inputFile) {
        URL systemResource = ClassLoader.getSystemResource(inputFile);

        if (systemResource == null) {
            LOGGER.error(inputFile + " is not on the classpath!");
            return null;
        }

        return Try.of(systemResource::toURI).recover(URISyntaxException.class,
                e -> {
            System.out.println(e.getMessage());
            return null;
        }).get();
    }

    public static <R> R test(URI uri, Function<Stream<String>, R> function) {
        try (Stream<String> stream = Files.lines(Paths.get(uri))) {
            return function.apply(stream);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return null;
    }
}
