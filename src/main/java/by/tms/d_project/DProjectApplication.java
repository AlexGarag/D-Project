package by.tms.d_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * D-Project из набора титульных и физических параметров
 * (далее - начальные условия, НУ - initial conditions, IC. Смотри сущности IcOts+FormIcOts)
 * формирует набор титульных и физических параметров (далее - разовое решение,
 * РР - one-time solution, Ots. Смотри сущности Ots+FormOts), по которым будет
 * осуществляться процесс монтажа форм для печати тиража.
 */
@SpringBootApplication
public class DProjectApplication {
	public static void main(String[] args){
		SpringApplication.run(DProjectApplication.class, args);
	}
}