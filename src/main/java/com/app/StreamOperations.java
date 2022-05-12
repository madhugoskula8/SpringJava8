package com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.app.model.Employee;

public class StreamOperations {

	public static void main(String[] args) {
		
		List<Employee> empList=new ArrayList<>();
		empList.add(new Employee(1,"Madhu",25));
		empList.add(new Employee(2,"Shekar",29));
		empList.add(new Employee(3,"Nihal",5));
		empList.add(new Employee(4,"Shannu",2));
		empList.add(new Employee(5,"Ramesh",35));
		
		List<Integer> intList=Arrays.asList(1,2,3,4,5,6);
		
		List<Integer> list=intList.stream().filter(a->a%2==0).collect(Collectors.toList());
		System.out.println(list); //[2, 4, 6]
		
		intList.stream().filter(a->a%2==0).forEach(System.out::println); //2 4 6
		intList.stream().filter(a->a%2==0).map(i->i+i).forEach(x->System.out.print(" "+x)); //4 8 12
		intList.stream().filter(a->a%2==0).map(i->i+i).filter(a->a>5).forEach(x->System.out.print(" "+x));//8 12
		
		System.out.println();
		System.out.println("==========count==============");
		long l=intList.stream().filter(a->a%2==0).map(i->i+i).filter(a->a>5).count();
		System.out.println(l);//2
		
		System.out.println("=========peek()==============");
		intList.stream().filter(a->a%2==0).map(i->i+i).peek(System.out::println).filter(a->a>5).forEach(x->System.out.print(" "+x));//4 8 12 & 8 12
		
		System.out.println();
		System.out.println("=========reduce()==============");
		long total=intList.stream().filter(a->a%2==0).reduce((a,b)->a+b).get();
		System.out.println(total); //12
		
		empList.stream().filter(e->e.getId()%2==0).forEach(System.out::println);
		//Employee [id=2, name=Shekar, age=29]
		//Employee [id=4, name=Shannu, age=2]
		System.out.println("=============");
		empList.stream().filter(e->e.getId()%2==0).map(e->{
				e.printName();
				return e.getName();
	}).forEach(System.out::println);
		/*
		 * In emp class : Shekar 
		 * Shekar
		 * In emp class : Shannu 
		 * Shannu
		 */
		
		empList.stream().filter(e->e.getId()%2!=0).map(e->e.getName().toUpperCase()).forEach(x->System.out.println(x));
		//MADHU NIHAL RAMESH
		
		boolean isContain=empList.stream().anyMatch(e->e.getName().equalsIgnoreCase("Madhu"));
		System.out.println(isContain); //true
		Employee emp=empList.stream().filter(e->e.getName().equalsIgnoreCase("Madhu")).findAny().get();
		System.out.println(emp);  // Employee [id=1, name=Madhu, age=25]
		
		System.out.println("==========min and max()========");
		List<Integer> ages=empList.stream().map(e->e.getAge()).collect(Collectors.toList());
		long minage=ages.stream().min((i1,i2)->i1.compareTo(i2)).get();
		System.out.println(minage); //2
		System.out.println(ages.stream().mapToInt(x->x).summaryStatistics().getMax()); //35
		System.out.println(ages.stream().mapToInt(x->x).summaryStatistics().getAverage()); //19.2
		System.out.println(ages.stream().mapToInt(x->x).summaryStatistics().getSum()); //96
		System.out.println(ages.stream().mapToInt(x->x).summaryStatistics().getCount()); //5
		
		System.out.println("==========skip()& limit()=========");
		Stream<Integer> minAge=empList.stream().map(e->e.getAge()).sorted().skip(1).limit(2);
		minAge.forEach(System.out::println);
		
		System.out.println("===========grouping()==============");
		empList.add(new Employee(6,"Madhu",25));
		List<String> nameList=empList.stream().map(e->e.getName()).collect(Collectors.toList());
		Map<String, Long> groupNames=nameList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		List<String> names= groupNames.entrySet().stream().filter(e->e.getValue()>1).map(e->e.getKey()).collect(Collectors.toList());
		System.out.println(groupNames); // {Shannu=1, Nihal=1, Shekar=1, Madhu=2, Ramesh=1}
		System.out.println(names); // [Madhu]
	//	Map<Integer, Long>= empList.stream().map(e->e.getAge()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		Set<String> nms=nameList.stream().filter(e->Collections.frequency(nameList, e)>1).collect(Collectors.toSet());
		System.out.println(nms); // [Madhu]
		
		Map<Integer,Set<Employee>> groupAge=empList.stream().collect(Collectors.groupingBy(e->e.getAge(),Collectors.toSet()));
		System.out.println(groupAge);
		/*{2=[Employee [id=4, name=Shannu, age=2]],
		 * 35=[Employee [id=5, name=Ramesh, age=35]], 
		 * 5=[Employee [id=3, name=Nihal, age=5]],
		 * 25=[Employee [id=1, name=Madhu, age=25]],
		 * 29=[Employee [id=2, name=Shekar, age=29]]}
		*/
		String s=empList.stream().map(m->m.getName().toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(s); // MADHU, SHEKAR, NIHAL, SHANNU, RAMESH, MADHU
		List<Employee> em=empList.stream().skip(1).limit(4).collect(Collectors.toList());
		System.out.println(em); // [Employee [id=2, name=Shekar, age=29], Employee [id=3, name=Nihal, age=5], Employee [id=4, name=Shannu, age=2], Employee [id=5, name=Ramesh, age=35]]
        
		Employee x=empList.stream().filter(e->e.getName().contains("Madhu")).findFirst().get();
		System.out.println(x); // Employee [id=1, name=Madhu, age=25]
		
		boolean y=empList.stream().anyMatch(m->m.getName().equalsIgnoreCase("Madhu"));
		System.out.println(y);  // true
	}

}
