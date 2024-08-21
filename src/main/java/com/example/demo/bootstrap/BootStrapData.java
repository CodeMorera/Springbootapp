package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;
    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, InhousePartRepository inhousePartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (outsourcedPartRepository.count() == 0) {

            OutsourcedPart Raider = new OutsourcedPart();
            Raider.setCompanyName("Yamaha");
            Raider.setName("Yamaha Raider 2008 Motor");
            Raider.setInv(5);
            Raider.setPrice(500.00);
            Raider.setId(1);
            outsourcedPartRepository.save(Raider);

            OutsourcedPart R125 = new OutsourcedPart();
            R125.setCompanyName("Yamaha");
            R125.setName("Yamaha R125 Handlebars");
            R125.setInv(10);
            R125.setPrice(125.87);
            R125.setId(2);
            outsourcedPartRepository.save(R125);

            OutsourcedPart Gearshift = new OutsourcedPart();
            Gearshift.setCompanyName("Honda");
            Gearshift.setName("Honda Cargo125 Gear Shift Shaft");
            Gearshift.setInv(5);
            Gearshift.setPrice(75.99);
            Gearshift.setId(3);
            outsourcedPartRepository.save(Gearshift);
        }

        if (inhousePartRepository.count() == 0) {

            InhousePart Seats = new InhousePart();
            Seats.setName("Platino Seats");
            Seats.setInv(7);
            Seats.setPrice(25.99);
            Seats.setId(4);
            inhousePartRepository.save(Seats);

            InhousePart Footpegs = new InhousePart();
            Footpegs.setName("Platino Footpegs");
            Footpegs.setInv(7);
            Footpegs.setPrice(14.99);
            Footpegs.setId(5);
            inhousePartRepository.save(Footpegs);

        }


        if (productRepository.count() == 0) {
            Product DRJacket = new Product("Dominican Flag Men's Leather Jacket", 89.00, 3);
            Product DRJacketF = new Product("Dominican Flag Women's Leather Jacket", 79.0, 5);
            Product DRHelmet = new Product("Dominican Themed Bike Helmet", 32.0, 5);
            Product DRHelmetC = new Product("Dominican Themed Bike Children's Helmet", 22.0, 5);
            Product DROil = new Product("Dominican Synthetic Oil", 27.0, 15);
            productRepository.save(DRJacket);
            productRepository.save(DRJacketF);
            productRepository.save(DRHelmet);
            productRepository.save(DRHelmetC);
            productRepository.save(DROil);
        }

    }
}

//        OutsourcedPart thePart=null;
//        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
//        for(OutsourcedPart part:outsourcedParts){
//            if(part.getName().equals("out test"))thePart=part;
//        }

//        System.out.println(thePart.getCompanyName());

//        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
//        for(OutsourcedPart part:outsourcedParts){
//            System.out.println(part.getName()+" "+part.getCompanyName());
//        }


//        System.out.println("Started in Bootstrap");
//        System.out.println("Number of Products"+productRepository.count());
//        System.out.println(productRepository.findAll());
//        System.out.println("Number of Parts"+partRepository.count());
//        System.out.println(partRepository.findAll());

//    }