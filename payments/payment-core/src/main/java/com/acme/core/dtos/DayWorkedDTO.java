package com.acme.core.dtos;

import com.acme.core.domains.types.Day;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@Builder
public class DayWorkedDTO implements Serializable, Comparable<DayWorkedDTO> {
    @NonNull
    private Day day;
    @NonNull
    private LocalTime start;
    @NonNull
    private LocalTime end;

    @Override
    public int compareTo(DayWorkedDTO dayWorked) {
        return this.day.compareTo(dayWorked.day);
    }
}
