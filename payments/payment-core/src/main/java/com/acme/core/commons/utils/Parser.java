package com.acme.core.commons.utils;

import com.acme.core.commons.exceptions.ParseException;

public interface Parser<Input, Output> {

    Output parse(Input input) throws ParseException;

}
