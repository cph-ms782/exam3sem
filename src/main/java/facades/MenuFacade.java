package facades;

import dto.DayPlanDTO;
import dto.MenuPlanInDTO;
import dto.MenuPlanOutDTO;
import entities.Ingredient;
import entities.MenuPlan;
import entities.DayPlan;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 */
public class MenuFacade {

    private static MenuFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MenuFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MenuFacade getMenuFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MenuFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<DayPlanDTO> getMenuPlan(int menuPlanID) {
        EntityManager em = emf.createEntityManager();
        try {
            MenuPlan menu = em.find(MenuPlan.class, menuPlanID);
            List<DayPlanDTO> dayPlans = new ArrayList();
            for (DayPlan dayPlan : menu.getDayPlans()) {
                dayPlans.add(new DayPlanDTO(dayPlan.getDayPlanID(), dayPlan.getRecipe()));
            }
            return dayPlans;
        } finally {
            em.close();
        }
    }

    public int newMenuPlan() {
        EntityManager em = emf.createEntityManager();
        try {
            MenuPlan menu = new MenuPlan(3);
            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();
            return menu.getMenuPlanID();
        } finally {
            em.close();
        }
    }

    public void addToMenuPlan(MenuPlanInDTO menuIn) {
        EntityManager em = emf.createEntityManager();
        try {
            MenuPlan menu = new MenuPlan(3);
            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();
            for (String dayPlan : menuIn.getDayPlans()) {
                em.getTransaction().begin();
                DayPlan day = new DayPlan(dayPlan, 0, menu);
                em.persist(day);
                em.getTransaction().commit();
            }

            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

//    public HobbyOutDTO editHobby(HobbyInDTO hobbyWithChanges) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            Hobby hobbyToEdit = em.find(Hobby.class, hobbyWithChanges.getHobbyID());
//            if (hobbyWithChanges.getName() != null && !hobbyWithChanges.getName().equals("")) { // .isEmpty() virkede ikke her
//                hobbyToEdit.setName(hobbyWithChanges.getName());
//            }
//            if (hobbyWithChanges.getDescription() != null && !hobbyWithChanges.getDescription().equals("")) {
//                hobbyToEdit.setDescription(hobbyWithChanges.getDescription());
//            }
//
//            em.getTransaction().begin();
//            em.merge(hobbyToEdit);
//            em.getTransaction().commit();
//            HobbyOutDTO hobOut = new HobbyOutDTO(hobbyToEdit.getName(), hobbyToEdit.getDescription());
//            return hobOut;
//        } finally {
//            em.close();
//        }
//    }
//    public String deleteHobby(int hobbyID) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            Hobby hobbyToDelete = em.find(Hobby.class, hobbyID);
//
//            em.getTransaction().begin();
//            em.remove(hobbyToDelete);
//            em.getTransaction().commit();
//            return "{\"msg\": \"Hobby #" +hobbyID + " deleted!\"}";
//        } finally {
//            em.close();
//        }
//    }
    // Get all hobbies
//    public List<Hobby> getHobbies() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            TypedQuery<Hobby> query = (TypedQuery<Hobby>) em.createQuery("SELECT h FROM Hobby h");
//            return query.getResultList();
//        } finally {
//            em.close();
//        }
//    }
    public String fillUp() {

//        [
//  "Slow cooker spicy chicken and bean soup",
//  "slow cooker beef stew",
//  "Smoked paprika goulash for the slow cooker",
//  "Pistachio chicken with pomegranate sauce",
//  "Cheesy leek and mustard soup",
//  "Christmas Stollen",
//  "Polly's eccles cakes",
//  "Braised beef in red wine",
//  "Moist garlic roasted chicken",
//  "Cheese and bacon stuffed pasta shells",
//  "Tofu vindaloo"
//]
        EntityManager em;
        DayPlan d1, d2;
        User u1;
        Ingredient i1, i2, i3;
        MenuPlan m1;

        em = emf.createEntityManager();

        try {
            m1 = new MenuPlan(2);
            em.getTransaction().begin();
            em.persist(m1);
            em.getTransaction().commit();

            User user = em.find(User.class, "user");
            m1.addUser(user);
            em.getTransaction().begin();
            em.merge(m1);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            d1 = new DayPlan("Tofu vindaloo", 0, m1);
            d2 = new DayPlan("Cheese and bacon stuffed pasta shells", 2, m1);
            em.persist(d1);
            em.persist(d2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            i1 = new Ingredient("900g (2 lb) braising or stewing beef, cubed", m1);
            i2 = new Ingredient("seasoned flour", m1);
            i3 = new Ingredient("olive oil", m1);
            em.persist(i1);
            em.persist(i2);
            em.persist(i3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            User user = em.find(User.class, "user");
            user.setMenuPlan(m1);
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return "{\"status\":\"filled\"}";
    }

    /**
     * empty production database
     *
     * @return
     */
    public String emptyDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Recipe.deleteAllRows").executeUpdate();
            em.createNamedQuery("DayPlan.deleteAllRows").executeUpdate();
            em.createNamedQuery("Ingredient.deleteAllRows").executeUpdate();
            em.createNamedQuery("MenuPlan.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return "{\"status\":\"emptied\"}";
    }

}
