package ru.artyomkomarov;

import java.lang.reflect.Method;

public class MainReflection {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("carpack.Car"); //��������� 2 ������ �� �����
			Class wl = Class.forName("carpack.Car$Wheel");
			
			Object maserati = c.newInstance(); //������� ��������� ������ Car, ������ ��� ��� ����������� � ������� newInstance
			/*
			 	��� �� ����� �������� ����� ������� � ������
			 	� ������� ������ �������
				Method[] m = c.getMethods();
				for(int i = 0; i < m.length; i++)
					System.out.println(m[i].getName());
			*/
			Method isPumpedUp = wl.getMethod("isPumpedUp"); // ���� ����� ������� ������, ����� �������� �� �� �����������
			Method pumpUp = wl.getMethod("pumpUp");
			Method getWheels = c.getMethod("getWheels");
			Method isLightsOn = c.getMethod("isLightsOn");
			Method switchLights = c.getMethod("switchLights");
			Method startDriving = c.getMethod("startDriving", int.class);
			Object[] wheels = (Object[]) getWheels.invoke(maserati); // �������� ������ �����, ����� ��������� �� � ��������
			if((boolean)isLightsOn.invoke(maserati) == false) 
				switchLights.invoke(maserati);
			for(int i = 0; i < wheels.length; i++) { //������ �������� ������ ������
				if((boolean)isPumpedUp.invoke(wheels[i]) == false) { // ��������� �������� �� ������
					pumpUp.invoke(wheels[i]); // ������������
				}
			}
			startDriving.invoke(maserati, 60); // �������� �������!
		} catch (Exception e) {
			System.out.print("NOOO"); //������������ ����������, ���� � ���� ������ ��������� �������� exception ��������� catch � ��������� "NOOO"
		}
	}
}
