package ec.edu.uce.payments.jpa.services;

import java.util.List;

public interface CrudService<T> {

    /**
     * Retrieves a list of all entities.
     *
     * @return a list of all entities of type T
     */
    List<T> getAll();

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity if found, null otherwise
     */
    T findById(Long id);

    /**
     * Saves a new entity.
     *
     * @param entity the entity to be saved
     */
    void save(T entity);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to be deleted
     */
    void deleteById(Long id);

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to be updated
     */
    void update(T entity);
}
