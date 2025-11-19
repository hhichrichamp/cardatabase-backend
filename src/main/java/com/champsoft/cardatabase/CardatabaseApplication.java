package com.champsoft.cardatabase;

import com.champsoft.cardatabase.DataAccessLayer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    public CardatabaseApplication(CarRepository carRepository, OwnerRepository ownerRepository) {

        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

        // Add owner objects and save these to db
        Owner owner1 = new Owner("John", "Johnson", "john.johnson@example.com", "555-0101", "jjohnson", "pass1234");
        Owner owner2 = new Owner("Mary", "Robinson", "mary.robinson@example.com", "555-0202", "mrobinson", "pass5678");
        Owner owner3 = new Owner("Xavier", "Chad", "xavier.chad@example.com", "555-0303", "xchad", "pass9012");
        Owner owner4 = new Owner("Luca", "Claude", "luca.claude@example.com", "555-0404", "lclaude", "pass3456");
        Owner owner5 = new Owner("Thierry", "Versailles", "thierry.versailles@example.com", "555-0505", "tversailles", "pass7890");
        Owner owner6 = new Owner("Thomas", "Lucas", "thomas.lucas@example.com", "555-0505", "tlucas", "pass9876");
        ownerRepository.saveAll(Arrays.asList(owner1, owner2, owner3,owner4,owner5, owner6));


        // Place your code here
        this.carRepository.save(new Car("Ford", "Mustang", "Red",
                "ADF-1121", 2023, 59000, "1FA6P8TH4P5123456", owner1));
        this.carRepository.save(new Car("Nissan", "Leaf", "White",
                "SSJ-3002", 2020, 29000, "1N4AZ1CP8LC301234", owner2));
        this.carRepository.save(new Car("Toyota", "Prius",
                "Silver", "KKO-0212", 2022, 39000, "JTDKARFP5N3123456", owner2));
        this.carRepository.save(new Car("Honda", "Civic", "Blue",
                "XYZ-7890", 2021, 25000, "2HGFC2F59MH512345", owner1));
        this.carRepository.save(new Car("Tesla", "Model 3", "Black",
                "ELC-2024", 2024, 45000, "5YJ3E1EA9PF123456", owner1));
        this.carRepository.save(new Car("Chevrolet", "Camaro", "Yellow",
                "SPD-5566", 2022, 52000, "1G1FE1R77N0123456", owner3));
        this.carRepository.save(new Car("BMW", "X5", "Gray",
                "LUX-8899", 2023, 68000, "5UXCR6C09P9R12345", owner4));
        this.carRepository.save(new Car("Mazda", "CX-5", "White",
                "MZD-4433", 2021, 32000, "JM3KFBDM5M0123456", owner5));
        this.carRepository.save(new Car("Volkswagen", "Golf", "Green",
                "VWG-1122", 2020, 24000, "3VW2B7AJ9LM123456", owner2));
        this.carRepository.save(new Car("Hyundai", "Tucson", "Brown",
                "HYU-9988", 2023, 35000, "KM8J3CA49PU123456", owner2));
        // Fetch all cars and log to console
        for (Car car : this.carRepository.findAll()) {
            logger.info("brand: {}, model: {}",
                    car.getBrand(), car.getModel());
        }
    }
}
