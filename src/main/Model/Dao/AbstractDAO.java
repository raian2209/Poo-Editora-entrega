package main.Model.Dao;

import jakarta.persistence.EntityManager;
import main.Util.JPAUtil;

public abstract class AbstractDAO {

    protected final EntityManager em;

    public AbstractDAO(){
        this.em = JPAUtil.getEntityManager();
    }

}
