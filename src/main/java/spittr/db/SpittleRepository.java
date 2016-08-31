package spittr.db;

import spittr.domain.Spittle;

import java.util.List;

/**
 * Repository interface with operations for {@link Spittle} persistence.
 * @author lusai
 * Created by Lusai on 8/31/16.
 */
public interface SpittleRepository {

    long count();

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

    Spittle findOne(long id);

    Spittle save(Spittle spittle);

    List<Spittle> findBySpitterId(long spitterId);

    void delete(long id);
}
