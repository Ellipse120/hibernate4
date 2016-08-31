package spittr.db.hibernate4;

import spittr.db.SpitterRepository;
import spittr.domain.Spitter;
import spittr.domain.Spittle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Lusai on 8/31/16.
 */
@Repository
public class HibernateSpitterRepository implements SpitterRepository {

    private SessionFactory sessionFactory;

    @Inject
    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }


    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public long count() {
        return findAll().size();
    }

    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);

        return new Spitter((Long) id, spitter.getUsername(),spitter.getPassword(),spitter.getFullname(),spitter.getEmail(),spitter.isUpdateByEmail());
    }

    public Spitter findOne(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    public List<Spittle> findBySpitterId(long spitterId) {
        return spittleCriteria().add(Restrictions.eq("spitter.id", spitterId)).list();
    }

    public Spitter findByUsername(String username) {
        return (Spitter) currentSession().createCriteria(Spitter.class).add(Restrictions.eq("username",username)).list().get(0);
    }

    public List<Spitter> findAll() {
        return (List<Spitter>) currentSession().createCriteria(Spitter.class).list();
    }

    private Criteria spittleCriteria(){
        return currentSession().createCriteria(Spittle.class).addOrder(Order.desc("postedTime"));
    }

}
