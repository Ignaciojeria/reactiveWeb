package com.example.ReactiveWebSpring5.ReactiveController;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

//Ejemplo extraido de : https://windoctor7.github.io/Programacion-Reactiva-Spring5.html
@RestController
public class ReactiveController {

	public static void multiplicar (Integer n) {
		System.out.println(n *2);
	}
	
	 @GetMapping(path = "/numeros", produces = "text/event-stream")
	    public Flux<Integer> all () {
	        Flux<Integer> flux = Flux.range(1,30)
	                .delayElements(Duration.ofSeconds(1))
	                .filter(n-> n%2==0) // solo números divisibles entre 2
	                .map(n -> n*2); // a cada elemento que ha sido filtrado, lo multiplicamos por 2
	        flux.subscribe(System.out::println); // suscriptor 1
	        flux.subscribe(ReactiveController::multiplicar); // suscriptor 2
	        return flux; // retornamos el elemento. Sería como el suscriptor 3
	    }
}
