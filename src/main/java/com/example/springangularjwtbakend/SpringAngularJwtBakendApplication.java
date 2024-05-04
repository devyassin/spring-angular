package com.example.springangularjwtbakend;

import com.example.springangularjwtbakend.entities.Payement;
import com.example.springangularjwtbakend.entities.PayementStatus;
import com.example.springangularjwtbakend.entities.PayementType;
import com.example.springangularjwtbakend.entities.Student;
import com.example.springangularjwtbakend.repositery.PayementRepositery;
import com.example.springangularjwtbakend.repositery.StudentRepositery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularJwtBakendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularJwtBakendApplication.class, args);


    }

    @Bean
    CommandLineRunner start(StudentRepositery studentRepositery, PayementRepositery payementRepositery){
        return  args -> {
            studentRepositery.save(Student.builder()
                    .firstName("yassine").code("1123465").programId("info").build());
            studentRepositery.save(Student.builder()
                    .firstName("Omar").code("9876543").programId("Math").build());

            studentRepositery.save(Student.builder()
                    .firstName("Aïcha").code("3456789").programId("CS").build());

            studentRepositery.save(Student.builder()
                    .firstName("Mohamed").code("5678901").programId("Physics").build());


            studentRepositery.findAll().forEach(s->{
                PayementType[] payementTypes=PayementType.values();
                Random random=new Random();
                for (int i=0;i<10;i++){
                    int index=random.nextInt(payementTypes.length);
                    payementRepositery.
                            save(Payement.builder().
                                    amount(1000+(int)(Math.random()+2000))
                                    .status(PayementStatus.CREATED)
                                    .date(LocalDate.now())
                                    .student(s)
                                    .type(payementTypes[index]).build());
                }
            });
        };
    }
}
