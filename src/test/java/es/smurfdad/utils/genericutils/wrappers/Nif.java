package es.smurfdad.utils.genericutils.wrappers;

import javax.validation.constraints.Pattern;

import es.smurfdad.utils.genericutils.wrappers.types.StringSimpleDataWrapper;

public class Nif extends StringSimpleDataWrapper {

    @Override
    @Pattern(regexp = "[0-9]")
    public void set(String value) {
        super.set(value);
    }

}
