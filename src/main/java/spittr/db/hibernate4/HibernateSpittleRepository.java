package spittr.db.hibernate4;

import spittr.db.SpittleRepository;
import spittr.domain.Spittle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import spittr.domain.Spitter;

/**
 * Created by Lusai on 8/31/16.
 */
@Repository
public class HibernateSpittleRepository implements SpittleRepository{

    private SessionFactory sessionFactory;

    @Inject
    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public long count(){
        return findAll().size();
    }

    private List<Spittle> findAll() {
        return null;
    }

    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    public List<Spittle> findRecent(int count) {
        return null;
    }

    public Spittle findOne(long id) {
        return (Spittle) currentSession().get(Spitter.class, id);
    }

    public Spittle save(Spittle spittle) {

        Serializable id = currentSession().save(spittle);

        return new Spittle((Long) id, spittle.getSpitter(), spittle.getMessage(), spittle.getPostedTime());
    }

    public List<Spittle> findBySpitterId(long spitterId) {
        return null;
    }

    public void delete(long id) {
        currentSession().delete(findOne(id));
    }

}
