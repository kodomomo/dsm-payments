package com.github.kodomo.dsmpayments.domain.booth.exception;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;

public class MenuNotFoundException extends GlobalException {
    public MenuNotFoundException() { super(404, "Menu not found."); }
}
