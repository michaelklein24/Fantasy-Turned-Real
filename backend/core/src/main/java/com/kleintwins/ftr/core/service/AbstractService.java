package com.kleintwins.ftr.core.service;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {

    @Autowired
    protected I18nService i18nService;
    @Autowired
    protected ConfigService configService;

}
