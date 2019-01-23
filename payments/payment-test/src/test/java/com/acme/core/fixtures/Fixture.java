package com.acme.core.fixtures;

import com.acme.core.commons.utils.TimeUtil;
import com.acme.core.domains.types.Day;
import com.acme.core.dtos.DayWorkedDTO;

import java.util.ArrayList;
import java.util.List;

public class Fixture {

    public static final List<DayWorkedDTO> CASE_1;
    public static final List<DayWorkedDTO> CASE_2;

    static {
        CASE_1 = new ArrayList<>();

        CASE_1.add(DayWorkedDTO.builder()
                .day(Day.MONDAY)
                .start(TimeUtil.getHour(10, 0))
                .end(TimeUtil.getHour(12, 0))
                .build());

        CASE_1.add(DayWorkedDTO.builder()
                .day(Day.TUESDAY)
                .start(TimeUtil.getHour(10, 0))
                .end(TimeUtil.getHour(12, 0))
                .build());

        CASE_1.add(DayWorkedDTO.builder()
                .day(Day.THURSDAY)
                .start(TimeUtil.getHour(1, 0))
                .end(TimeUtil.getHour(3, 0))
                .build());

        CASE_1.add(DayWorkedDTO.builder()
                .day(Day.SATURDAY)
                .start(TimeUtil.getHour(14, 0))
                .end(TimeUtil.getHour(18, 0))
                .build());

        CASE_1.add(DayWorkedDTO.builder()
                .day(Day.SUNDAY)
                .start(TimeUtil.getHour(20, 0))
                .end(TimeUtil.getHour(21, 0))
                .build());
    }

    static {
        CASE_2 = new ArrayList<>();

        CASE_2.add(DayWorkedDTO.builder()
                .day(Day.MONDAY)
                .start(TimeUtil.getHour(10, 0))
                .end(TimeUtil.getHour(12, 0))
                .build());

        CASE_2.add(DayWorkedDTO.builder()
                .day(Day.THURSDAY)
                .start(TimeUtil.getHour(12, 0))
                .end(TimeUtil.getHour(14, 0))
                .build());

        CASE_2.add(DayWorkedDTO.builder()
                .day(Day.SATURDAY)
                .start(TimeUtil.getHour(20, 0))
                .end(TimeUtil.getHour(21, 0))
                .build());
    }

}
