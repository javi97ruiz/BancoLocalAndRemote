package org.example.creditcard.cache;

import java.util.*;

import org.example.cache.Cache;
import org.example.models.TarjetaCredito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheTarjetaImpl implements Cache<UUID, TarjetaCredito> {
    private static final Logger logger = LoggerFactory.getLogger(CacheTarjetaImpl.class);
    private final LinkedHashMap<UUID, TarjetaCredito> cache;
    private final int maxCapacity;

    /**
     * Constructor de la clase.
     * @autor Ra�l Fern�ndez, Javier Hern�ndez, Javier Ru�z, Samuel Cort�s, Yahya El Hadri, Alvaro Herrero.
     * @since 1.0
     * @param maxCapacity
     * @param logger
     * @return Tama�o de la c�che.
     */

    public CacheTarjetaImpl(int maxCapacity, Logger logger) {
        this.maxCapacity = maxCapacity;
        this.cache = new LinkedHashMap<UUID, TarjetaCredito>(maxCapacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<UUID, TarjetaCredito> eldest) {
                return size() > maxCapacity;
            }
        };
    }

    /**
     * Obtiene el valor asociado a la clave especificada.
     * @author Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     * @param key Clave a buscar
     * @return Valor asociado a la clave
     */

    @Override
    public TarjetaCredito get(UUID key) {
        logger.debug("Obteniendo el valor de la clave: {}", key);
        return cache.get(key);
    }

    public List<TarjetaCredito> buscarPorIdUsuario(long idBuscado) {
        List<TarjetaCredito> tarjetas = new ArrayList<>();
        for (Map.Entry<UUID, TarjetaCredito> entry : cache.entrySet()) {
            if (entry.getValue().getClientID() == idBuscado) {
                tarjetas.add(entry.getValue());
            }
        }
        return tarjetas;
    }


    /**
     * A�ade un valor a la c�che con la clave especificada.
     * @autor Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     * @param key Clave a asociar
     * @param value Valor a almacenar
     */

    @Override
    public void put(UUID key, TarjetaCredito value) {
        logger.debug("A�adiendo a cache el valor de la clave: {}", key);
        cache.put(key, value);
    }

    /**
     * Elimina el valor asociado a la clave especificada de la cache.
     * @autor Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     * @param key Clave a eliminar
     */

    @Override
    public void remove(UUID key) {
        logger.debug("Eliminando de cache el valor de la clave: {}", key);
        cache.remove(key);
    }

    /**
     * Limpia la cache.
     * @autor Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     */

    @Override
    public void clear() {
        logger.debug("Limpiando la cache");
        cache.clear();
    }

    /**
     * Obtiene el n�mero de valores almacenados en la c�che.
     * @autor Ra�l Fern�ndez, Samuel Cort�s, Javier Hern�ndez, Alvaro Herrero, Javier Ru�z, Yahya El Hadri.
     * @since 1.0
     * @return N�mero de valores almacenados
     */

    @Override
    public int size() {
        logger.debug("Obteniendo el tama�o de la cache");
        return cache.size();
    }

    /**
     * Obtiene un conjunto de claves almacenadas en la c�che.
     * @autor Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @return Conjunto de claves almacenadas.
     */

    @Override
    public Set<UUID> keys() {
        logger.debug("Obteniendo las claves de la cache");
        return cache.keySet();
    }

    /**
     * Obtiene una colecci�n de valores almacenados en la c�che.
     * @autor Ra�l Fern�ndez, Samuel Cort�s, Javier Hern�ndez, Alvaro Herrero, Javier Ru�z, Yahya El Hadri.
     * @since 1.0
     * @return Colecci�n de valores almacenados.
     */

    @Override
    public Collection<TarjetaCredito> values() {
        logger.debug("Obteniendo los valores de la cache");
        return cache.values();
    }

    /**
     * Comprueba si la c�che contiene un valor asociado a la clave especificada.
     * @autor Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @param key Clave a buscar
     * @return True si la cache contiene el valor, false en caso contrario
     */

    @Override
    public boolean containsKey(UUID key) {
        logger.debug("Comprobando si existe la clave en la cache: {}", key);
        return cache.containsKey(key);
    }

    /**
     * Comprueba si la cache contiene un valor espec�fico.
     * @autor Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @param value Valor a buscar
     * @return True si la cache contiene el valor, false en caso contrario
     */

    @Override
    public boolean containsValue(TarjetaCredito value) {
        logger.debug("Comprobando si existe el valor en la cache: {}", value);
        return cache.containsValue(value);
    }

    /**
     * Comprueba si la cache est� vac�a.
     * @autor Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @return True si la cache est� vac�a, false en caso contrario.
     */

    @Override
    public boolean isEmpty() {
        logger.debug("Comprobando si la cache est� vac�a");
        return cache.isEmpty();
    }

    /**
     * Comprueba si la cache no est� vac�a.
     * @autor Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @return True si la cache no est� vac�a, false en caso contrario.
     */

    @Override
    public boolean isNotEmpty() {
        logger.debug("Comprobando si la cache no est� vac�a");
        return !isEmpty();
    }
}
