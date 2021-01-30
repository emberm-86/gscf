package com.interview.gscf;

import com.interview.gscf.service.WallPaperCalculatorService;
import com.interview.gscf.util.WallPaperUtil;

import java.net.URI;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * WallPaper calculator console app.
 */
public class Application {

    private static final String INPUT_FILE = "input1.txt";

    public static void main(String[] args) {
        URI uri = WallPaperUtil.loadFile(INPUT_FILE);

        if (uri == null) {
            return;
        }

        WallPaperCalculatorService wallPaperCalculatorService =
                new WallPaperCalculatorService();

        printResult("All wallpapers needed: ", uri,
                wallPaperCalculatorService::getSumForAllRooms);

        printResult("Cubic rooms in reversed order by " +
                        "dimension: ", uri,
                wallPaperCalculatorService::getCubicOrderByAmountDesc);

        printResult("Rooms are repeated in the input file: ",
                uri, wallPaperCalculatorService::getRoomsWithRepetition);
    }

    private static <R> void printResult(String title, URI uri,
                                        Function<Stream<String>, R> function) {
        System.out.println(title + WallPaperUtil.test(uri, function));
    }
}
