package Ass1;

public class Searching {
	public static Doctor searchDoctorById(Doctor[] doctors, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (doctors[i].getId().equalsIgnoreCase(id)) {
                return doctors[i];
            }
        }
        return null;
    }

    public static Patient searchPatientById(Patient[] patients, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (patients[i].getId().equalsIgnoreCase(id)) {
                return patients[i];
            }
        }
        return null;
    }

    public static Staff searchStaffById(Staff[] staff, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (staff[i].getId().equalsIgnoreCase(id)) {
                return staff[i];
            }
        }
        return null;
    }

    public static Medicine searchMedicineByName(Medicine[] medicines, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (medicines[i].getName().equalsIgnoreCase(name)) {
                return medicines[i];
            }
        }
        return null;
    }

    public static Lab searchLabByName(Lab[] labs, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (labs[i].getLab().equalsIgnoreCase(name)) {
                return labs[i];
            }
        }
        return null;
    }

    public static facility searchFacilityByName(facility[] facilities, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (facilities[i].getFacility().equalsIgnoreCase(name)) {
                return facilities[i];
            }
        }
        return null;
    }

}
