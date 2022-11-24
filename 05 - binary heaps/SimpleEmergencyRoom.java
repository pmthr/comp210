package comp210.assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    public Patient dequeue() {
        int smallest = 0;
        if(patients.size() == 0) {
            return null;
        }
        else {
            for(int i = 1; i < patients.size(); i++) {
                if(patients.get(smallest).getPriority().compareTo(patients.get(i).getPriority()) > 0){
                    smallest = i;
                }
            }
        }
        
        if(patients.size() <= 0) {
            return null;
        }
        else {
            Patient minelement = patients.get(smallest);
            patients.set(smallest, patients.get(patients.size()-1));
            patients.remove(patients.size()-1);
            return minelement;
        }
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
