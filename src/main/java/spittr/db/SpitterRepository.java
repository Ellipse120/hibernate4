package spittr.db;

import spittr.domain.Spitter;

import java.util.List;

/**
 * Repository interface with operations for {@link Spitter} persistence.
 * Created by Lusai on 8/31/16.
 */
public interface SpitterRepository {

    long count();

    Spitter save(Spitter spitter);

    Spitter findOne(long id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();

}
