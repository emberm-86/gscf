package com.interview.gscf;

import com.interview.gscf.service.WallPaperCalculatorService;
import com.interview.gscf.util.WallPaperUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class WallPaperCalculatorTest {

    private static final String INPUT_FILE = "input1.txt";

    private WallPaperCalculatorService wallPaperCalculatorService;
    private URI uri;

    @Before
    public void init() {
        uri = WallPaperUtil.loadFile(INPUT_FILE);
        wallPaperCalculatorService = new WallPaperCalculatorService();
    }

    @Test
    public void testSum() {
        Integer sum = WallPaperUtil.test(uri,
                wallPaperCalculatorService::getSumForAllRooms);

        Assert.assertNotNull(sum);
        Assert.assertEquals(1452150, sum.intValue());
    }

    @Test
    public void testCubicListInReverseOrder() {
        List<String> cubicReversedList = WallPaperUtil.test(uri,
                wallPaperCalculatorService::getCubicOrderByAmountDesc);

        Assert.assertNotNull(cubicReversedList);
        Assert.assertEquals(cubicReversedList, Arrays.asList("28x28x28",
                "15x15x15", "12x12x12", "9x9x9", "7x7x7"));
    }

    @Test
    public void testAppearsMoreThanOne() {
        List<String> repetitionList = WallPaperUtil.test(uri,
                wallPaperCalculatorService::getRoomsWithRepetition);

        Assert.assertNotNull(repetitionList);
        Assert.assertEquals(repetitionList, Arrays.asList("22x3x1", "6x8x12",
                "17x25x1", "8x8x16", "4x3x23",
                "7x3x4", "8x28x29", "15x26x22", "15x10x7", "17x15x2",
                "22x27x12", "6x18x15", "2x25x8"));
    }

}
