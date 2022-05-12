package com.app;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestStudent {

	public static void main(String[] args) {
		List<String> cityList1=Arrays.asList("JGL","KNR","JBPR");
		Student s1=new Student(1,"Madhu",cityList1);
		List<String> cityList2=Arrays.asList("JGL","BBRPLY","GPLLy","PGDPLY","JGL");
		Student s2=new Student(2,"abc",cityList2);
		List<String> cityList3=Arrays.asList("KNR","HYD","NJBD","JGL");
		Student s3=new Student(3,"xyz",cityList3);
		
		List<Student> stds=Arrays.asList(s1,s2,s3);
		System.out.println(stds);
		List<String> names=stds.stream().map(s->s.getName()).collect(Collectors.toList());
		String nams=names.stream().collect(Collectors.joining(",  "));
		System.out.println(nams);  // Madhu,  abc,  xyz
		
		List<List<String>> cities=stds.stream().map(s->s.getCities()).collect(Collectors.toList());
		System.out.println(cities); //[[JGL, KNR, JBPR], [JGL, BBRPLY, GPLLy, PGDPLY, JGL], [KNR, HYD, NJBD, JGL]]
		Set<List<String>> citis=stds.stream().map(s->s.getCities()).collect(Collectors.toSet());
		System.out.println(citis);// Same
		List<String> list=stds.stream().flatMap(s->s.getCities().stream()).collect(Collectors.toList());
		System.out.println(list); //[JGL, KNR, JBPR, JGL, BBRPLY, GPLLy, PGDPLY, JGL, KNR, HYD, NJBD, JGL]
		Set<String> lists=stds.stream().flatMap(s->s.getCities().stream()).collect(Collectors.toSet());
		System.out.println(lists); // [HYD, BBRPLY, JBPR, NJBD, PGDPLY, JGL, KNR, GPLLy]

	}
}
