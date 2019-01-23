package com.acme.core.commons.utils;

import com.acme.core.commons.exceptions.ParseException;
import com.acme.core.domains.types.Day;
import com.acme.core.dtos.DayWorkedDTO;
import com.acme.core.dtos.InputDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParse implements Parser<String, InputDTO> {

    public InputParse() {

    }

    public InputDTO parse(String input) throws ParseException {
        String[] inputs = input.trim().split("=");

        if (inputs.length != 2) {
            throw new ParseException("Invalid input: " + input);
        }
        return InputDTO.builder()
                .name(inputs[0])
                .workDays(new ParseListDayWorked().parse(inputs[1]))
                .build();
    }

    public static class ParseListDayWorked implements Parser<String, List<DayWorkedDTO>> {

        @Override
        public List<DayWorkedDTO> parse(String input) throws ParseException {

            final String[] workedDays = input.trim().split(",");

            if (workedDays.length == 0) {
                throw new ParseException("Invalid input: " + input);
            }

            return Stream.of(workedDays)
                    .map(dayWorked -> {
                        try {
                            return new DayWorkedParse().parse(dayWorked);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    public static class DayWorkedParse implements Parser<String, DayWorkedDTO> {

        private final static String PATTERN_DAY = "(?<=MO|TU|WE|TH|FR|SA|SU)";

        @Override
        public DayWorkedDTO parse(String input) throws ParseException {
            String[] dayWithHours = input.trim().split(PATTERN_DAY);

            if (dayWithHours.length != 2) {
                throw new ParseException("Invalid input: " + input);
            }

            String[] hours = dayWithHours[1].split("-");

            if (hours.length != 2) {
                throw new ParseException("Invalid input: " + input);
            }

            return DayWorkedDTO.builder()
                    .day(Day.dayOf(dayWithHours[0]).orElseThrow(() -> new ParseException("Invalid input: " + dayWithHours[0])))
                    .start(TimeUtil.parse(hours[0]))
                    .end(TimeUtil.parse(hours[1]))
                    .build();
        }
    }

}
