package by.tms.d_project.service;

import by.tms.d_project.entity.Printing;
import by.tms.d_project.repository.PrintingRepository;
import org.springframework.stereotype.Service;

@Service
public class PrintingService {

    private final PrintingRepository printingRepository;

    public PrintingService(PrintingRepository printingRepository) {
        this.printingRepository = printingRepository;
    }

    public Printing create(Printing printing) {
        printingRepository.save(printing);
        return printing;
    }

    // todo show, deleteById


}