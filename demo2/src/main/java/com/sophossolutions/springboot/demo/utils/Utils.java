package com.sophossolutions.springboot.demo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {
	
	public static void main(String[] args) {
		List<String> lista = new ArrayList<String>();
		lista.add("hola");
		lista.add("Code");
		lista.add("Angular");
		lista.add("loles");
		
		int variable1 = 15;
		int variable2 = 1;
		
		for(int x = 1; x < variable1; x++) {
			if(variable2/2 == 1) {
				variable2 = 5;
			}
			if(x == 20) {
				variable2 = 0;
			}
		}
		
		//lista.forEach(x -> System.out.println(x));
		
		//lista.removeIf(x -> x.equalsIgnoreCase("hola"));
		
		//lista.sort((actual, anterior) -> actual.compareToIgnoreCase(anterior));
		
		//lista.forEach(x -> System.out.println(x));
		
		//streamOperations("L", lista);
		
		//optionalUses(null);
		
		//mapUsesOnJava8();
		
//		ordenar(lista);
//		ordenarLambda(lista);
		
//		calcular();
//		calcularLambda();
	}
	
	public static void mapUsesOnJava8() {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, "hola mundo");
		map.put(1, "hola mundo2");
		map.put(3, "hola mundo3");
		map.put(2, "hola mundo4");
		map.putIfAbsent(1, "hola mundo5");
		
		map.compute(1, (k,v) -> v.toString().concat(" he sido concatenado"));
		
		map.computeIfPresent(1, (k,v) -> v.toString().concat(" he sido concatenado if present"));
		
		map.getOrDefault(8, "estoy por defecto");
		
		Map<Integer, Object> map2 = map.entrySet().stream()
				.filter(mapData -> mapData.getValue().equals("hola mundo3"))
				.collect(Collectors.toMap(mapData -> mapData.getKey(), mapData -> mapData.getValue() + " desde map2"));
		
		map2.forEach((key, value) -> System.out.println("key: " + key + " - value: " + value));
		
		map.forEach((key, value) -> System.out.println("key: " + key + " - value: " + value));
	}
	
	public static void optionalUses(Object object) {
		Optional<Object> op = Optional.ofNullable(object);
		op.orElseThrow();
		Object data = op.orElse("");
		System.out.println(op.isEmpty());
		System.out.println(data);
		System.out.println(data);
	}
	
	public static void streamOperations(String oper, List<?> lista) {
		switch(oper) {
			case "F": lista.stream().filter(x -> x.toString().contains("ol")).forEach(x -> System.out.println(x));
				break;
				
			case "O": lista.stream().sorted((x,y) -> y.toString().compareToIgnoreCase(x.toString())).forEach(x -> System.out.println(x));
				break;
			
			case "M": lista.stream().filter(x -> x.toString().contains("A")).map(x -> x.toString().concat(" he sido concatenado")).forEach(x -> System.out.println(x));
				break;
			
			case "L": lista.stream().limit(2).forEach(x -> System.out.println(x));
				break;
				
			case "C": System.out.println(lista.stream().count());
		}
	}
	
	public static void ordenar(List<String> lista ) {
		Collections.sort(lista, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		for(String iterator: lista) {
			System.out.println(iterator);
		}
	}
	
	public static void ordenarLambda(List<String> lista ) {
		Collections.sort(lista, (String o1, String o2) -> o1.compareTo(o2));
		
		for(String iterator: lista) {
			System.out.println(iterator);
		}
	}
	
	public static void calcular() {
		Operacion opera = new Operacion() {
			@Override
			public Double calcularProm(double n1, double n2) {
				return (n1 + n2)/2;
			}

		};
		System.out.println(opera.calcularProm(1,2));
	}
	
	public static void calcularLambda() {
		//Operacion opera = (double x, double y) -> (x + y) / 2;
		//Operacion opera = (double x, double y) -> {return (x + y) / 2;};
		Operacion opera = (x, y) -> {return (x + y) / 2;};
		//Operacion opera = () -> 2.2;
		System.out.println(opera.calcularProm(1, 2));
	}

}

@FunctionalInterface
abstract interface Operacion {
	Double calcularProm(double n1, double n2);
	//Double calcularProm();
}