package com.interview.gscf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class WallPaperCalculatorTest {

    private static final String INPUT_FILE = "input1.txt";

    private static WallPaperCalculatorService wallPaperCalculatorService;
    private static URI uri;

    @BeforeAll
    public static void init() {
        uri = WallPaperUtil.loadFile(INPUT_FILE);
        wallPaperCalculatorService = new WallPaperCalculatorService();
    }

    @Test
    public void testSum() {
        Integer sum = WallPaperUtil.test(uri,
                wallPaperCalculatorService::getSumForAllRooms);

        Assertions.assertNotNull(sum);
        Assertions.assertEquals(1452150, sum.intValue());
    }

    @Test
    public void testCubicListInReverseOrder() {
        List<String> cubicReversedList = WallPaperUtil.test(uri,
                wallPaperCalculatorService::getCubicOrderByAmountDesc);

        Assertions.assertNotNull(cubicReversedList);
        Assertions.assertEquals(cubicReversedList, Arrays.asList("28x28x28",
                "15x15x15", "12x12x12", "9x9x9", "7x7x7"));
    }

    @Test
    public void testAppearsMoreThanOne() {
        List<String> repetitionList = WallPaperUtil.test(uri,
                wallPaperCalculatorService::getRoomsWithRepetition);

        Assertions.assertNotNull(repetitionList);
        Assertions.assertEquals(repetitionList, Arrays.asList("22x3x1", "6x8x12",
                "17x25x1", "8x8x16", "4x3x23",
                "7x3x4", "8x28x29", "15x26x22", "15x10x7", "17x15x2",
                "22x27x12", "6x18x15", "2x25x8"));
    }
}
