package Application;
import Model.Department;
import Model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class App {

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		while (true) {
			System.out.println("=====================");
			System.out.println(" MANTENIMENT EMPLEATS");
			System.out.println("=====================");
			System.out.println("1. Consultar empleats");
			System.out.println("2. Inserir nou empleat");
			System.out.print("Escolliu quina acció voleu fer (o escribiu 'exit' per sortir): ");
			String opcioSeleccionada = bufferedReader.readLine();

			if (opcioSeleccionada.equalsIgnoreCase("exit")) {
				break;
			}

			try {
				int numSeleccionat = Integer.parseInt(opcioSeleccionada);
				if (numSeleccionat == 1) {
					System.out.print("Introduir el nom del departament (o escribiu 'exit' per sortir): ");
					String nomDepartament = bufferedReader.readLine();
					if (nomDepartament.equalsIgnoreCase("exit")) {
						break;
					}
					obtenirEmpleats(nomDepartament);
				} else if (numSeleccionat == 2) {
					inserirEmpleat();
				} else {
					System.out.println("L'operació seleccionada no existeix");
				}
			} catch (NumberFormatException e) {
				System.out.println("No se ha introduït un numero");
			}
		}
	}

	private static void obtenirEmpleats(String nomDepartament) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		String hql = "FROM Employee WHERE deptnoFk.deptname = :nom";
		Query<Employee> query = session.createQuery(hql, Employee.class);
		query.setParameter("nom", nomDepartament);

		List<Employee> llistaEmpleats = query.getResultList();
		if (!llistaEmpleats.isEmpty()) {
			System.out.println("| EMP_NO | NOM | COGNOMS | DATA_NAIX | ROL | SALARI |");
			for (Employee empleat : llistaEmpleats) {
				System.out.println("| " + empleat.getId() + " | " + empleat.getFirstName() + " | " + empleat.getLastName() + " | " + empleat.getBirthDate() + " | " + empleat.getRole() + " | " + empleat.getSalary() + " |");
			}
		} else {
			System.out.println("El departament no existeix.");
		}
		session.getTransaction().commit();
		session.close();
	}

	private static void inserirEmpleat() throws IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		System.out.println("=============");
		System.out.println(" NOU EMPLEAT");
		System.out.println("=============");
		System.out.print("Nom: ");
		String nom = bufferedReader.readLine();
		System.out.print("Cognom: ");
		String cognom = bufferedReader.readLine();
		System.out.print("Data de naixement: ");
		LocalDate dataNaixement = LocalDate.parse(bufferedReader.readLine(), formatter);
		System.out.print("Gènere: ");
		String genere = bufferedReader.readLine();
		System.out.print("Data d'incorporació: ");
		LocalDate dataIncorporacio = LocalDate.parse(bufferedReader.readLine(), formatter);
		System.out.print("Salari: ");
		Float salari = Float.valueOf(bufferedReader.readLine());
		System.out.print("Rol: ");
		String rol = bufferedReader.readLine();
		System.out.print("Departament (ID): ");
		int idDepartament = Integer.parseInt(bufferedReader.readLine());

		Department department = session.get(Department.class, idDepartament);
		if (department != null) {
			Employee employee = new Employee(cognom, nom, dataNaixement, genere, dataIncorporacio, rol, salari, department);
			session.save(employee);
			System.out.println("Empleat registrat correctament.");
		} else {
			System.out.println("Operació no realitzada, el departament especificat no existeix.");
		}

		session.getTransaction().commit();
		session.close();
	}
}
