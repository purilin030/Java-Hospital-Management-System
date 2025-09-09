package Ass1;

public class ShowAllHandler {
	public static void showAllDoctors(Doctor[] doctors, int count) {
        System.out.println("\n--- Doctor List ---");
        for (int i = 0; i < count; i++) {
            doctors[i].showDoctorInfo();
        }
    }
	
	public static void showAllStaff(Staff[] staff, int count) {
		System.out.println("\n--- Staff List ---");
        for (int i = 0; i < count; i++) {
            staff[i].showStaffInfo();
        }
	}
	
	public static void showAllPatients(Patient[] patients, int count) {
        System.out.println("\n--- Patient List ---");
        for (int i = 0; i < count; i++) {
            patients[i].showPatientInfo();
        }
    }
	
	public static void showAllLabs(Lab[] labs, int count) {
        System.out.println("\n--- Lab List ---");
        for (int i = 0; i < count; i++) {
            labs[i].labList();
        }
    }
	
	public static void showAllMedicines(Medicine[] medicines, int count) {
        System.out.println("\n--- Medicine List ---");
        for (int i = 0; i < count; i++) {
            medicines[i].findMedicine();
        }
    }
	
	public static void showAllFacilities(facility[] facility, int count) {
		System.out.println("\n--- Facility List ---");
        for (int i = 0; i < count; i++) {
            facility[i].showFacility();
        }
	}
}
