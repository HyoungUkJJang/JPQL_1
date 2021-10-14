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

                Team team = new Team();
                team.setName("teamA");
                em.persist(team);

                Team team2 = new Team();
                team2.setName("teamB");
                em.persist(team2);

                Team team3 = new Team();
                team3.setName("teamC");
                em.persist(team3);

                Member member = new Member();
                member.setUsername("member1");
                member.setAge(100);
                member.setTeam(team);
                em.persist(member);

                Member member2 = new Member();
                member2.setUsername("member2");
                member2.setAge(100);
                member2.setTeam(team);
                em.persist(member2);

                Member member3 = new Member();
                member3.setUsername("member3");
                member3.setAge(100);
                member3.setTeam(team2);
                em.persist(member3);
                
                em.flush();
                em.clear();


            List<Member> resultList = em.createQuery("select m from Member m JOIN FETCH m.team ", Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1.getTeam().getName() = " + member1.getTeam().getName());
            }
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
