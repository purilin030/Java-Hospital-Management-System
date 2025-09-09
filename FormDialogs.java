package Ass1;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * One-stop forms holder (Java 8).
 * Usage examples:
 *   Optional<Doctor> d = FormDialogs.DoctorForm.show();
 *   Optional<Patient> p = FormDialogs.PatientForm.show();
 *   Optional<Staff>   s = FormDialogs.StaffForm.show();
 *   Optional<Medicine> m = FormDialogs.MedicineForm.show();
 *   Optional<Lab>       l = FormDialogs.LabForm.show();
 *   Optional<facility>  f = FormDialogs.FacilityForm.show();
 */
public final class FormDialogs {

    // ===== common helpers =====
    private static void mark(TextField tf) { tf.setStyle("-fx-border-color: #d00000; -fx-border-width: 1.2;"); }
    private static void clear(TextField... tfs) { for (TextField tf : tfs) tf.setStyle(null); }
    private static void markCombo(ComboBox<?> cb) { cb.setStyle("-fx-border-color: #d00000;"); }
    private static void clearCombo(ComboBox<?>... cbs) { for (ComboBox<?> c : cbs) c.setStyle(null); }

    private static void attach(GridPane gp, int row, String label, Control field) {
        gp.addRow(row, new Label(label), field);
    }

    private static ChangeListener<String> onText(Runnable r) {
        return (o, ov, nv) -> r.run();
    }

    // ===================== Doctor =====================
    public static class DoctorForm {
        public static Optional<Doctor> show() {
            Dialog<Doctor> dialog = new Dialog<Doctor>();
            dialog.setTitle("Add New Doctor");
            dialog.setHeaderText("Fill in the details");

            ButtonType okType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

            final TextField id = new TextField();
            final TextField name = new TextField();
            final TextField specialist = new TextField();
            final TextField workTime = new TextField();
            final TextField qualification = new TextField();
            final TextField room = new TextField();

            id.setPromptText("e.g., D251");
            name.setPromptText("Name");
            specialist.setPromptText("e.g., Cardiologist");
            workTime.setPromptText("e.g., 9AM-5PM");
            qualification.setPromptText("e.g., MBBS");
            room.setPromptText("e.g., 101");

            final Label error = new Label();
            error.setStyle("-fx-text-fill:#d00000; -fx-font-size:12px;");

            GridPane gp = new GridPane();
            gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(10));
            attach(gp, 0, "ID:", id);
            attach(gp, 1, "Name:", name);
            attach(gp, 2, "Specialist:", specialist);
            attach(gp, 3, "Work Time:", workTime);
            attach(gp, 4, "Qualification:", qualification);
            attach(gp, 5, "Room No.:", room);
            gp.add(error, 0, 6, 2, 1);

            final Node addBtn = dialog.getDialogPane().lookupButton(okType);
            addBtn.setDisable(true);

            Runnable val = new Runnable() {
                @Override public void run() {
                    clear(id, name, room);
                    String err = null;
                    if (id.getText().trim().isEmpty()) { err = "ID is required."; mark(id); }
                    else if (name.getText().trim().isEmpty()) { err = "Name is required."; mark(name); }
                    else if (room.getText().trim().isEmpty()) { err = "Room No. is required."; mark(room); }
                    else {
                        try {
                            int r = Integer.parseInt(room.getText().trim());
                            if (r <= 0) { err = "Room No. must be a positive integer."; mark(room); }
                        } catch (NumberFormatException e) { err = "Room No. must be a number."; mark(room); }
                    }
                    error.setText(err == null ? "" : err);
                    addBtn.setDisable(err != null);
                }
            };

            id.textProperty().addListener(onText(val));
            name.textProperty().addListener(onText(val));
            room.textProperty().addListener(onText(val));

            dialog.getDialogPane().setContent(gp);
            dialog.setResultConverter(btn -> {
                if (btn == okType) {
                    val.run();
                    if (addBtn.isDisabled()) return null;
                    int roomNo;
                    try { roomNo = Integer.parseInt(room.getText().trim()); }
                    catch (NumberFormatException e) { return null; }
                    return new Doctor(
                            id.getText().trim(),
                            name.getText().trim(),
                            specialist.getText().trim(),
                            workTime.getText().trim(),
                            qualification.getText().trim(),
                            roomNo
                    );
                }
                return null;
            });
            return dialog.showAndWait();
        }
    }

    // ===================== Patient =====================
    public static class PatientForm {
        public static Optional<Patient> show() {
            Dialog<Patient> dialog = new Dialog<Patient>();
            dialog.setTitle("Add New Patient");
            dialog.setHeaderText("Fill in the details");

            ButtonType okType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

            final TextField id = new TextField();
            final TextField name = new TextField();
            final TextField disease = new TextField();
            final ComboBox<String> sex = new ComboBox<String>();
            final ComboBox<String> admin = new ComboBox<String>();
            final TextField age = new TextField();

            id.setPromptText("e.g., P1001");
            name.setPromptText("Name");
            disease.setPromptText("Disease");
            sex.getItems().addAll("M", "F"); sex.setPromptText("Select");
            admin.getItems().addAll("Admitted", "Discharged"); admin.setPromptText("Select");
            age.setPromptText("e.g., 30");

            final Label error = new Label();
            error.setStyle("-fx-text-fill:#d00000; -fx-font-size:12px;");

            GridPane gp = new GridPane();
            gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(10));
            attach(gp, 0, "ID:", id);
            attach(gp, 1, "Name:", name);
            attach(gp, 2, "Disease:", disease);
            attach(gp, 3, "Sex:", sex);
            attach(gp, 4, "Admission Status:", admin);
            attach(gp, 5, "Age:", age);
            gp.add(error, 0, 6, 2, 1);

            final Node addBtn = dialog.getDialogPane().lookupButton(okType);
            addBtn.setDisable(true);

            Runnable val = new Runnable() {
                @Override public void run() {
                    clear(id, name, age); clearCombo(sex, admin);
                    String err = null;
                    if (id.getText().trim().isEmpty()) { err = "ID is required."; mark(id); }
                    else if (name.getText().trim().isEmpty()) { err = "Name is required."; mark(name); }
                    else if (sex.getValue() == null) { err = "Sex is required."; markCombo(sex); }
                    else if (admin.getValue() == null) { err = "Admission status is required."; markCombo(admin); }
                    else if (age.getText().trim().isEmpty()) { err = "Age is required."; mark(age); }
                    else {
                        try {
                            int a = Integer.parseInt(age.getText().trim());
                            if (a <= 0) { err = "Age must be a positive integer."; mark(age); }
                        } catch (NumberFormatException e) { err = "Age must be a number."; mark(age); }
                    }
                    error.setText(err == null ? "" : err);
                    addBtn.setDisable(err != null);
                }
            };

            id.textProperty().addListener(onText(val));
            name.textProperty().addListener(onText(val));
            age.textProperty().addListener(onText(val));
            sex.valueProperty().addListener((o, ov, nv) -> val.run());
            admin.valueProperty().addListener((o, ov, nv) -> val.run());

            dialog.getDialogPane().setContent(gp);
            dialog.setResultConverter(btn -> {
                if (btn == okType) {
                    val.run();
                    if (addBtn.isDisabled()) return null;
                    int ageVal;
                    try { ageVal = Integer.parseInt(age.getText().trim()); }
                    catch (NumberFormatException e) { return null; }
                    return new Patient(
                            id.getText().trim(),
                            name.getText().trim(),
                            disease.getText().trim(),
                            sex.getValue(),
                            admin.getValue(),
                            ageVal
                    );
                }
                return null;
            });
            return dialog.showAndWait();
        }
    }

    // ===================== Staff =====================
    public static class StaffForm {
        public static Optional<Staff> show() {
            Dialog<Staff> dialog = new Dialog<Staff>();
            dialog.setTitle("Add New Staff");
            dialog.setHeaderText("Fill in the details");

            ButtonType okType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

            final TextField id = new TextField();
            final TextField name = new TextField();
            final TextField designation = new TextField();
            final TextField sex = new TextField();
            final TextField salary = new TextField();

            id.setPromptText("e.g., S2001");
            name.setPromptText("Name");
            designation.setPromptText("Designation");
            sex.setPromptText("M/F");
            salary.setPromptText("e.g., 3500");

            final Label error = new Label();
            error.setStyle("-fx-text-fill:#d00000; -fx-font-size:12px;");

            GridPane gp = new GridPane();
            gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(10));
            attach(gp, 0, "ID:", id);
            attach(gp, 1, "Name:", name);
            attach(gp, 2, "Designation:", designation);
            attach(gp, 3, "Sex:", sex);
            attach(gp, 4, "Salary:", salary);
            gp.add(error, 0, 5, 2, 1);

            final Node addBtn = dialog.getDialogPane().lookupButton(okType);
            addBtn.setDisable(true);

            Runnable val = new Runnable() {
                @Override public void run() {
                    clear(id, name, salary);
                    String err = null;
                    if (id.getText().trim().isEmpty()) { err = "ID is required."; mark(id); }
                    else if (name.getText().trim().isEmpty()) { err = "Name is required."; mark(name); }
                    else if (salary.getText().trim().isEmpty()) { err = "Salary is required."; mark(salary); }
                    else {
                        try {
                            int s = Integer.parseInt(salary.getText().trim());
                            if (s <= 0) { err = "Salary must be a positive integer."; mark(salary); }
                        } catch (NumberFormatException e) { err = "Salary must be a number."; mark(salary); }
                    }
                    error.setText(err == null ? "" : err);
                    addBtn.setDisable(err != null);
                }
            };

            id.textProperty().addListener(onText(val));
            name.textProperty().addListener(onText(val));
            salary.textProperty().addListener(onText(val));

            dialog.getDialogPane().setContent(gp);
            dialog.setResultConverter(btn -> {
                if (btn == okType) {
                    val.run();
                    if (addBtn.isDisabled()) return null;
                    int sal;
                    try { sal = Integer.parseInt(salary.getText().trim()); }
                    catch (NumberFormatException e) { return null; }
                    return new Staff(
                            id.getText().trim(),
                            name.getText().trim(),
                            designation.getText().trim(),
                            sex.getText().trim(),
                            sal
                    );
                }
                return null;
            });
            return dialog.showAndWait();
        }
    }

    // ===================== Medicine =====================
    public static class MedicineForm {
        public static Optional<Medicine> show() {
            Dialog<Medicine> dialog = new Dialog<Medicine>();
            dialog.setTitle("Add New Medicine");
            dialog.setHeaderText("Fill in the details");

            ButtonType okType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

            final TextField name = new TextField();
            final TextField manufacturer = new TextField();
            final TextField expiry = new TextField();
            final TextField cost = new TextField();
            final TextField count = new TextField();

            name.setPromptText("e.g., Paracetamol");
            manufacturer.setPromptText("Manufacturer");
            expiry.setPromptText("YYYY-MM-DD");
            cost.setPromptText("e.g., 1");
            count.setPromptText("e.g., 100");

            final Label error = new Label();
            error.setStyle("-fx-text-fill:#d00000; -fx-font-size:12px;");

            GridPane gp = new GridPane();
            gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(10));
            attach(gp, 0, "Name:", name);
            attach(gp, 1, "Manufacturer:", manufacturer);
            attach(gp, 2, "Expiry Date:", expiry);
            attach(gp, 3, "Cost per unit:", cost);
            attach(gp, 4, "Count:", count);
            gp.add(error, 0, 5, 2, 1);

            final Node addBtn = dialog.getDialogPane().lookupButton(okType);
            addBtn.setDisable(true);

            Runnable val = new Runnable() {
                @Override public void run() {
                    clear(name, expiry, cost, count);
                    String err = null;
                    if (name.getText().trim().isEmpty()) { err = "Name is required."; mark(name); }
                    else if (expiry.getText().trim().isEmpty()) { err = "Expiry date is required."; mark(expiry); }
                    else {
                        try {
                            LocalDate.parse(expiry.getText().trim());
                            if (cost.getText().trim().isEmpty()) { err = "Cost is required."; mark(cost); }
                            else if (count.getText().trim().isEmpty()) { err = "Count is required."; mark(count); }
                            else {
                                try {
                                    int c = Integer.parseInt(cost.getText().trim());
                                    int n = Integer.parseInt(count.getText().trim());
                                    if (c <= 0) { err = "Cost must be a positive integer."; mark(cost); }
                                    else if (n <= 0) { err = "Count must be a positive integer."; mark(count); }
                                } catch (NumberFormatException e) {
                                    err = "Cost/Count must be numbers."; mark(cost); mark(count);
                                }
                            }
                        } catch (DateTimeParseException e) { err = "Expiry must be yyyy-MM-dd."; mark(expiry); }
                    }
                    error.setText(err == null ? "" : err);
                    addBtn.setDisable(err != null);
                }
            };

            name.textProperty().addListener(onText(val));
            expiry.textProperty().addListener(onText(val));
            cost.textProperty().addListener(onText(val));
            count.textProperty().addListener(onText(val));

            dialog.getDialogPane().setContent(gp);
            dialog.setResultConverter(btn -> {
                if (btn == okType) {
                    val.run();
                    if (addBtn.isDisabled()) return null;
                    try {
                        LocalDate.parse(expiry.getText().trim());
                        int costVal = Integer.parseInt(cost.getText().trim());
                        int countVal = Integer.parseInt(count.getText().trim());
                        return new Medicine(
                                name.getText().trim(),
                                manufacturer.getText().trim(),
                                expiry.getText().trim(),
                                costVal,
                                countVal
                        );
                    } catch (Exception ex) { return null; }
                }
                return null;
            });
            return dialog.showAndWait();
        }
    }

    // ===================== Lab =====================
    public static class LabForm {
        public static Optional<Lab> show() {
            Dialog<Lab> dialog = new Dialog<Lab>();
            dialog.setTitle("Add New Lab");
            dialog.setHeaderText("Fill in the details");

            ButtonType okType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

            final TextField lab = new TextField();
            final TextField cost = new TextField();

            lab.setPromptText("Lab name");
            cost.setPromptText("e.g., 500");

            final Label error = new Label();
            error.setStyle("-fx-text-fill:#d00000; -fx-font-size:12px;");

            GridPane gp = new GridPane();
            gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(10));
            attach(gp, 0, "Lab Name:", lab);
            attach(gp, 1, "Cost:", cost);
            gp.add(error, 0, 2, 2, 1);

            final Node addBtn = dialog.getDialogPane().lookupButton(okType);
            addBtn.setDisable(true);

            Runnable val = new Runnable() {
                @Override public void run() {
                    clear(lab, cost);
                    String err = null;
                    if (lab.getText().trim().isEmpty()) { err = "Lab name is required."; mark(lab); }
                    else if (cost.getText().trim().isEmpty()) { err = "Cost is required."; mark(cost); }
                    else {
                        try {
                            int v = Integer.parseInt(cost.getText().trim());
                            if (v <= 0) { err = "Cost must be a positive integer."; mark(cost); }
                        } catch (NumberFormatException e) { err = "Cost must be a number."; mark(cost); }
                    }
                    error.setText(err == null ? "" : err);
                    addBtn.setDisable(err != null);
                }
            };

            lab.textProperty().addListener(onText(val));
            cost.textProperty().addListener(onText(val));

            dialog.getDialogPane().setContent(gp);
            dialog.setResultConverter(btn -> {
                if (btn == okType) {
                    val.run();
                    if (addBtn.isDisabled()) return null;
                    try {
                        int c = Integer.parseInt(cost.getText().trim());
                        return new Lab(lab.getText().trim(), c);
                    } catch (Exception ex) { return null; }
                }
                return null;
            });
            return dialog.showAndWait();
        }
    }

    // ===================== Facility =====================
    public static class FacilityForm {
        public static Optional<facility> show() {
            Dialog<facility> dialog = new Dialog<facility>();
            dialog.setTitle("Add New Facility");
            dialog.setHeaderText("Fill in the details");

            ButtonType okType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

            final TextField name = new TextField();
            name.setPromptText("e.g., ICU");

            final Label error = new Label();
            error.setStyle("-fx-text-fill:#d00000; -fx-font-size:12px;");

            GridPane gp = new GridPane();
            gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(10));
            attach(gp, 0, "Facility Name:", name);
            gp.add(error, 0, 1, 2, 1);

            final Node addBtn = dialog.getDialogPane().lookupButton(okType);
            addBtn.setDisable(true);

            Runnable val = new Runnable() {
                @Override public void run() {
                    clear(name);
                    String err = null;
                    if (name.getText().trim().isEmpty()) { err = "Facility name is required."; mark(name); }
                    error.setText(err == null ? "" : err);
                    addBtn.setDisable(err != null);
                }
            };

            name.textProperty().addListener(onText(val));

            dialog.getDialogPane().setContent(gp);
            dialog.setResultConverter(btn -> {
                if (btn == okType) {
                    val.run();
                    if (addBtn.isDisabled()) return null;
                    return new facility(name.getText().trim());
                }
                return null;
            });
            return dialog.showAndWait();
        }
    }
}
