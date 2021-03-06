package ru.artyomkomarov;

import java.lang.reflect.Method;

public class MainReflection {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("carpack.Car"); //Загружаем два класса по имени
			Class wl = Class.forName("carpack.Car$Wheel");
			Object maserati = c.newInstance(); //Создаем экземпляр класса Car с помощью newInstance
			/*
			 	так можем вывести названия методов у класса c
				Method[] m = c.getMethods();
				for(int i = 0; i < m.length; i++)
					System.out.println(m[i].getName());
			*/
			Method isPumpedUp = wl.getMethod("isPumpedUp"); // Получим методы класса по отдельности, используя имена методов
			Method pumpUp = wl.getMethod("pumpUp");
			Method getWheels = c.getMethod("getWheels");
			Method isLightsOn = c.getMethod("isLightsOn");
			Method switchLights = c.getMethod("switchLights");
			Method startDriving = c.getMethod("startDriving", int.class);
			Object[] wheels = (Object[]) getWheels.invoke(maserati); // Создадим массив колес
			if((boolean)isLightsOn.invoke(maserati) == false) // проверяем включены ли фары
				switchLights.invoke(maserati); // включаем
			for(int i = 0; i < wheels.length; i++) { // Пройдем наш массив колес циклом
				if((boolean)isPumpedUp.invoke(wheels[i]) == false) { // проверяем накачано ли колесо
					pumpUp.invoke(wheels[i]); // накачиваем
				}
			}
			startDriving.invoke(maserati, 60); // Пытаемся поехать со скоростью 60 mph
		} catch (Exception e) {
			System.out.print("NOOO"); // Если на этапе выполнения программы получим Exception, то выведется NOOO
		}
	}
}
