package com.interview.gscf.service;

import com.interview.gscf.pojo.Room;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WallPaperCalculatorService {

    private static final String DELIMITER_IN_ROWS = "[x]";

    public Integer getSumForAllRooms(Stream<String> stream) {
        return stream.map(this::convertRowToRoom)
                .filter(Objects::nonNull)
                .map(this::calcWallPaperAmount)
                .reduce(0, Integer::sum);
    }

    public List<String> getCubicOrderByAmountDesc(Stream<String> stream) {
        return stream.map(this::convertRowToRoom)
                .filter(Objects::nonNull)
                .filter(this::isCubic)
                .sorted(Comparator.comparingInt(this::calcWallPaperAmount)
                        .reversed()).map(r -> r.l + "x" + r.w + "x" + r.h)
                .collect(Collectors.toList());
    }

    public List<String> getRoomsWithRepetition(Stream<String> stream) {
        return stream.collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(c -> c.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private boolean isCubic(Room room) {
        return room.l == room.w && room.w == room.h;
    }

    private int calcOverHead(Room room) {
        return !isCubic(room) ? Math.min(Math.min(room.l, room.h), room.w) : 0;
    }

    private int calcWallPaperAmount(Room room) {
        return 2 * (room.l * room.w + room.w * room.h + room.h * room.l)
                + calcOverHead(room);
    }

    private boolean isPositiveNumber(String str) {
        return str.charAt(0) != 0 && str.chars().allMatch(Character::isDigit);
    }

    private Room convertRowToRoom(String str) {
        String[] arr = str.split(DELIMITER_IN_ROWS);

        if (arr.length != 3 || !Arrays.stream(arr)
                .allMatch(this::isPositiveNumber)) {
            return null;
        }

        int[] dimension =
                Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();

        return new Room(dimension[0], dimension[1], dimension[2]);
    }
}
