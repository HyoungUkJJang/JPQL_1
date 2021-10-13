package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
            List<Member> resultList = em.createQuery("select new jpql.Member(m.username, m.age) from Member m", Member.class).getResultList();
            Member member1 = resultList.get(0);
            System.out.println("member1.getUsername() = " + member1.getUsername());
            System.out.println("member1.getAge() = " + member1.getAge());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

    }
}
