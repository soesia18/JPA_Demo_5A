package at.kaindorf.console;

import at.kaindorf.pojo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<SchoolClass> list;


        try {
            list = Files.lines(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.csv"), StandardCharsets.UTF_8)
                    .skip(1)
                    .map(SchoolClass::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        list.forEach(System.out::println);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_testdb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        list.forEach(em::persist);

        em.getTransaction().commit();

        System.out.println(em.find(SchoolClass.class, 3));

        Department dept = em.find(Department.class, new DepartmentPK("IT", 42));
        System.out.println(dept);

        /*em.remove(list.get(0));
        list.get(13).setLastname("Valesi");*/

        /*em.getTransaction().begin();

        Department dept = new Department("IT", 42, LocalDate.now());
        em.persist(dept);

        em.getTransaction().commit();*/

        em.getTransaction().begin();
        Driver driver = new Driver("Paulus", 4.2);
        Car car = new Car("Mercedes", 666);

        car.setDriver(driver);
        driver.setCar(car);

        em.persist(driver);
        em.persist(car);

        em.getTransaction().commit();


        System.out.println(car);

        /*TypedQuery<SchoolClass> tq =
                em.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.studentAmount > 30", SchoolClass.class);*/
        TypedQuery<SchoolClass> tq =
                em.createNamedQuery("SchoolClass.findByStudentAmount", SchoolClass.class)
                        .setParameter("studentAmount", 30);

        List<SchoolClass> schoolClasses = tq.getResultList();
        schoolClasses.forEach(System.out::println);

        em.close();
        emf.close();
    }
}
