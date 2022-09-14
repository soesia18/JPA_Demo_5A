package at.kaindorf.console;

import at.kaindorf.pojo.Schoolclass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file =
                Paths
                        .get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.csv")
                        .toFile();


        List<Schoolclass> list;


//        try {
//            list = Files.lines( Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.csv"), StandardCharsets.UTF_8)
//                    .skip(1)
//                    .map(line -> {
//                        String[] lineData = line.split(";");
//                        return new Schoolclass(lineData[0], lineData[1], lineData[2], lineData[3], lineData[4], Integer.parseInt(lineData[5]), lineData[6]);
//                    })
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            list = br.lines().skip(1).map(line -> {
                String[] lineData = line.split(";");
                return new Schoolclass(lineData[0], lineData[1], lineData[2], lineData[3], lineData[4], Integer.parseInt(lineData[5]), lineData[6]);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        list.forEach(System.out::println);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_testdb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        list.forEach(em::persist);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
