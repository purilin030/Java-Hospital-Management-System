package Ass1;
import java.util.Scanner;

public class Controller {
	private Scanner input;
    private Initialization init;

    public Controller(Scanner input, Initialization init) {
        this.input = input;
        this.init = init;
    }
    
    public void run(Menu menu, SubMenu submenu) {
    	int choice;
    	do {
    	    choice = menu.displayMainMenu();
    	    switch (choice) {
    	        case 1:
    	            handleDoctor(submenu);
    	            break;
    	        case 2:
    	            handlePatient(submenu);
    	            break;
    	        case 3:
    	            handleStaff(submenu);
    	            break;
    	        case 4:
    	            handleMedicine(submenu);
    	            break;
    	        case 5:
    	            handleLab(submenu);
    	            break;
    	        case 6:
    	            handleFacility(submenu);
    	            break;
    	        case 7:
    	            System.out.println("Exiting system.");
    	            break;
    	        default:
    	            System.out.println("Invalid selection. Try again.");
    	            break;
    	    }
    	} while (choice != 7);

    }
    
    private void handleDoctor(SubMenu submenu) {
        int action = submenu.displaySubMenu("Doctor");
        if (action == 1) {
        	do {
        		DoctorRegistrationHandler handler = new DoctorRegistrationHandler(input, init.doctors, init.docCount);
        		Doctor newDoctor = handler.registerNewDoctor();
        		if (newDoctor != null && init.docCount < init.doctors.length) {
        			init.doctors[init.docCount++] = newDoctor;
        		}
        	}while(Again("doctor","add"));
        } else if (action == 2) {
            ShowAllHandler.showAllDoctors(init.doctors, init.docCount);
        } else if (action == 3) {
        	do {
        	System.out.print("Enter Doctor ID to search: ");
            String id = input.nextLine();
            Doctor result = Searching.searchDoctorById(init.doctors, init.docCount, id);
            if (result != null) result.showDoctorInfo();
            else System.out.println("Doctor not found.");
            System.out.println();
            }while(Again("doctor", "search"));
        }else if (action == 4) {
            do {
                System.out.print("Enter Doctor ID to delete: ");
                String id = input.nextLine();
                boolean deleted = Deleting.deleteDoctorById(init.doctors, new int[]{init.docCount}, id);
                if (deleted) {
                    init.docCount--; 
                    System.out.println("Doctor deleted successfully.");
                } else {
                    System.out.println("No founds.");
                }
            } while (Again("doctor", "delete"));
        }
    }
    
    private void handlePatient(SubMenu submenu) {
        int action = submenu.displaySubMenu("Patient");
        if (action == 1) {
        	do {
        		PatientRegistrationHandler handler = new PatientRegistrationHandler(input, init.patients, init.patCount);
        		Patient newPatient = handler.registerNewPatient();
        		if (newPatient != null && init.patCount < init.patients.length) {
        			init.patients[init.patCount++] = newPatient;
        		}
        	}while(Again("patient", "add"));
        } else if (action == 2) {
            ShowAllHandler.showAllPatients(init.patients, init.patCount);
        }else if (action == 3) {
        	do {
        		System.out.print("Enter Patient ID to search: ");
                String id = input.nextLine();
                Patient result = Searching.searchPatientById(init.patients, init.patCount, id);
                if (result != null) result.showPatientInfo();
                else System.out.println("Patient not found.");
                System.out.println();
            }while(Again("patient", "search"));
        }else if (action == 4) {
            do {
                System.out.print("Enter Patient ID to delete: ");
                String id = input.nextLine();
                boolean deleted = Deleting.deletePatientById(init.patients, new int[]{init.patCount}, id);
                if (deleted) {
                    init.patCount--; 
                    System.out.println("Patient deleted successfully.");
                } else {
                    System.out.println("No founds.");
                }
            } while (Again("patient", "delete"));
        }
    }
    
    private void handleStaff(SubMenu submenu) {
        int action = submenu.displaySubMenu("Staff");
        if (action == 1) {
        	do {
        		StaffRegistrationHandler handler = new StaffRegistrationHandler(input, init.staff, init.staffCount);
        		Staff newStaff = handler.registerNewStaff();
        		if (newStaff != null && init.staffCount < init.staff.length) {
        			init.staff[init.staffCount++] = newStaff;
        		}
        	}while(Again("staff", "add"));
        } else if (action == 2) {
            ShowAllHandler.showAllStaff(init.staff, init.staffCount);
        }else if (action == 3) {
        	do {
        		System.out.print("Enter Staff ID to search: ");
                String id = input.nextLine();
                Staff result = Searching.searchStaffById(init.staff, init.staffCount, id);
                if (result != null) result.showStaffInfo();
                else System.out.println("Staff not found.");
                System.out.println();
            }while(Again("staff", "search"));
        }else if (action == 4) {
            do {
                System.out.print("Enter Staff ID to delete: ");
                String id = input.nextLine();
                boolean deleted = Deleting.deleteStaffById(init.staff, new int[]{init.staffCount}, id);
                if (deleted) {
                    init.staffCount--; 
                    System.out.println("Staff deleted successfully.");
                } else {
                    System.out.println("No founds.");
                }
            } while (Again("staff", "delete"));
        }
    }
    
    private void handleLab(SubMenu submenu) {
        int action = submenu.displaySubMenu("Lab");
        if (action == 1) {
        	do {
        		LabRegistrationHandler handler = new LabRegistrationHandler(input, init.labs, init.labCount);
        		Lab newLab = handler.registerNewLab();
        		if (newLab != null && init.labCount < init.labs.length) {
        			init.labs[init.labCount++] = newLab;
        		}
        	}while(Again("lab", "add"));
        } else if (action == 2) {
            ShowAllHandler.showAllLabs(init.labs, init.labCount);
        }else if (action == 3) {
        	do {
        		System.out.print("Enter Lab Name to search: ");
                String name = input.nextLine();
                Lab result = Searching.searchLabByName(init.labs, init.labCount, name);
                if (result != null) result.labList();
                else System.out.println("Lab not found.");
                System.out.println();
            }while(Again("lab", "search"));
        }else if (action == 4) {
            do {
                System.out.print("Enter Lab's name to delete: ");
                String id = input.nextLine();
                boolean deleted = Deleting.deleteLabByName(init.labs, new int[]{init.labCount}, id);
                if (deleted) {
                    init.labCount--; 
                    System.out.println("Lab deleted successfully.");
                } else {
                    System.out.println("No founds.");
                }
            } while (Again("lab", "delete"));
        }
    }
    
    private void handleFacility(SubMenu submenu) {
        int action = submenu.displaySubMenu("Facility");
        if (action == 1) {
        	do {
        		FacilityRegistrationHandler handler = new FacilityRegistrationHandler(input, init.facilities, init.facCount);
        		facility newFac = handler.registerNewFacility();
        		if (newFac != null && init.facCount < init.facilities.length) {
        			init.facilities[init.facCount++] = newFac;
        		}
        	}while(Again("facility", "add"));
        } else if (action == 2) {
            ShowAllHandler.showAllFacilities(init.facilities, init.facCount);
        }else if (action == 3) {
        	do {
        		System.out.print("Enter Facility Name to search: ");
                String name = input.nextLine();
                facility result = Searching.searchFacilityByName(init.facilities, init.facCount, name);
                if (result != null) result.showFacility();
                else System.out.println("Facility not found.");
                System.out.println();
            }while(Again("facility", "search"));
        }else if (action == 4) {
            do {
                System.out.print("Enter facility's name to delete: ");
                String id = input.nextLine();
                boolean deleted = Deleting.deleteFacilityByName(init.facilities, new int[]{init.facCount}, id);
                if (deleted) {
                    init.facCount--; 
                    System.out.println("Facility deleted successfully.");
                } else {
                    System.out.println("No founds.");
                }
            } while (Again("facility", "delete"));
        }
    }
    
    private void handleMedicine(SubMenu submenu) {
        int action = submenu.displaySubMenu("Medicine");
        if (action == 1) {
        	do {
        		MedicineRegistrationHandler handler = new MedicineRegistrationHandler(input, init.medicines, init.medCount);
        		Medicine newMed = handler.registerNewMedicine();
        		if (newMed != null && init.medCount < init.medicines.length) {
        			init.medicines[init.medCount++] = newMed;
        		}
        	}while(Again("medicine", "add"));
        } else if (action == 2) {
            ShowAllHandler.showAllMedicines(init.medicines, init.medCount);
        }else if (action == 3) {
        	do {
        		System.out.print("Enter Medicine Name to search: ");
                String name = input.nextLine();
                Medicine result = Searching.searchMedicineByName(init.medicines, init.medCount, name);
                if (result != null) result.findMedicine();
                else System.out.println("Medicine not found.");
                System.out.println();
            }while(Again("medicine", "search"));
        }else if (action == 4) {
            do {
                System.out.print("Enter medicine's name to delete: ");
                String id = input.nextLine();
                boolean deleted = Deleting.deleteMedicineByName(init.medicines, new int[]{init.medCount}, id);
                if (deleted) {
                    init.medCount--; 
                    System.out.println("Medicine deleted successfully.");
                } else {
                    System.out.println("No founds.");
                }
            } while (Again("medicine", "delete"));
        }
    }
    
    private boolean Again(String selection, String function) {
    	
    	System.out.println("Do you want to "+ function + " another " + selection + "? (1-Yes, 2-No)");
    	System.out.print("Enter your choice: ");
    	System.out.println();
    	int select = Integer.parseInt(input.nextLine());
    	while(select < 1 || select > 2) {//make sure between 1 - 2
  	       System.out.println("Invalid number!");
  	       System.out.print("Enter your choice: ");
  	       select = Integer.parseInt(input.nextLine());
  	       System.out.println();
  	    }
    	if(select == 1) return true;
    	else return false;
    }
    
}
