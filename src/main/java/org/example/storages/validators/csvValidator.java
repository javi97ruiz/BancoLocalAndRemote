package org.example.storages.validators;


import org.example.models.TarjetaCredito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class csvValidator {
    private static final Logger logger = LoggerFactory.getLogger(csvValidator.class);
    public static boolean csvValidatorImport(File file){



        if(file.isDirectory()){
            logger.error("El archivo es un directorio.", file.getAbsolutePath());
            return false;
        }

        if(!file.toString().endsWith(".csv")){
            logger.error("El archivo no es un csv.", file.getAbsolutePath());
            return false;
        }

        if(!file.canRead()){
            logger.error("El archivo no permite la lectura.", file.getAbsolutePath());
            return false;
        }

        return true;

    }

}

