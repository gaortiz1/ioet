package com.acme.core.dtos;

import com.acme.core.commons.exceptions.ParseException;
import com.acme.core.commons.utils.InputParse;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class InputDTO implements Serializable {
    @NonNull
    private String name;
    @NonNull
    private List<DayWorkedDTO> workDays;

    public static InputDTO parse(String input) throws ParseException {
        return new InputParse().parse(input);
    }
}
