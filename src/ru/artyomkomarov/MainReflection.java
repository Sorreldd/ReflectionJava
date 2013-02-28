package ru.artyomkomarov;

import java.lang.reflect.Method;

public class MainReflection {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("carpack.Car"); //Загружаем 2 класса по имени
			Class wl = Class.forName("carpack.Car$Wheel");
			
			Object maserati = c.newInstance(); //Создаем экземпляр класса Car, делаем все это динамически с помощью newInstance
			/*
			 	Так мы можем находить имена методов у класса
			 	и создать массив методов
				Method[] m = c.getMethods();
				for(int i = 0; i < m.length; i++)
					System.out.println(m[i].getName());
			*/
			Method isPumpedUp = wl.getMethod("isPumpedUp"); // Зная имена методов класса, можем получить их по отдельности
			Method pumpUp = wl.getMethod("pumpUp");
			Method getWheels = c.getMethod("getWheels");
			Method isLightsOn = c.getMethod("isLightsOn");
			Method switchLights = c.getMethod("switchLights");
			Method startDriving = c.getMethod("startDriving", int.class);
			Object[] wheels = (Object[]) getWheels.invoke(maserati); // Создадим массив колес, чтобы проверить их и накачать
			if((boolean)isLightsOn.invoke(maserati) == false) 
				switchLights.invoke(maserati);
			for(int i = 0; i < wheels.length; i++) { //циклом проходим каждое колесо
				if((boolean)isPumpedUp.invoke(wheels[i]) == false) { // проверяем накачано ли колесо
					pumpUp.invoke(wheels[i]); // накачачиваем
				}
			}
			startDriving.invoke(maserati, 60); // пытаемся поехать!
		} catch (Exception e) {
			System.out.print("NOOO"); //обрабатываем исключения, если в ходе работы программы появился exception сработает catch и выведется "NOOO"
		}
	}
}
