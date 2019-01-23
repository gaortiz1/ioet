package com.acme.core.commons.utils;

import com.acme.core.commons.exceptions.ParseException;
import com.acme.core.domains.types.Day;
import com.acme.core.dtos.DayWorkedDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestInputParse {

    @Test
    void shouldParseWhenInputIsCorrect() throws ParseException {
        String input = "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";
        Assertions.assertNotNull(new InputParse().parse(input));
    }

    @Test
    void shouldNotParseWhenInputIsIncorrect() throws ParseException {
        Assertions.assertAll(
                () -> Assertions.assertThrows(ParseException.class, () -> new InputParse().parse("ASTRIDMO10:00-12:00,TH12:00-14:00,SU20:00-21:00")),
                () -> Assertions.assertThrows(ParseException.class, () -> new InputParse().parse(""))
        );
    }

    @Test
    void shouldParseDayWorkedDTOWhenInputIsCorrect() throws ParseException {
        final DayWorkedDTO dayWorkedDTO = new InputParse.DayWorkedParse().parse("MO10:00-12:00");
        Assertions.assertAll(
                () -> Assertions.assertEquals(Day.MONDAY, dayWorkedDTO.getDay()),
                () -> Assertions.assertEquals(TimeUtil.getHour(10, 0), dayWorkedDTO.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(12, 0), dayWorkedDTO.getEnd())
        );
    }

    @Test
    void shouldNotParseDayWorkedDTOWhenInputIsIncorrect() {
        Assertions.assertAll(
                () -> Assertions.assertThrows(ParseException.class, () -> new InputParse.DayWorkedParse().parse("MO10:0012:00")),
                () -> Assertions.assertThrows(ParseException.class, () -> new InputParse.DayWorkedParse().parse("MO10:00")),
                () -> Assertions.assertThrows(ParseException.class, () -> new InputParse.DayWorkedParse().parse("10:00-12:00")),
                () -> Assertions.assertThrows(ParseException.class, () -> new InputParse.DayWorkedParse().parse(""))
        );
    }

    @Test
    void shouldParseParseListDayWorkedWhenInputIsCorrect() throws ParseException {
        List<DayWorkedDTO> workedDays = new InputParse.ParseListDayWorked().parse("MO10:00-12:00, MO10:00-12:00");
        Assertions.assertEquals(2, workedDays.size());
    }

    @Test
    void shouldNotParseParseListDayWorkedWhenInputIsIncorrect() {
        Assertions.assertThrows(RuntimeException.class, () -> new InputParse.ParseListDayWorked().parse("MO10:0012:00"));
        Assertions.assertThrows(RuntimeException.class, () -> new InputParse.ParseListDayWorked().parse(""));
    }

}
