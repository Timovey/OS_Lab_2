package com.company;

import java.util.ArrayList;
import java.util.Random;

public class SystemCore {
    private ArrayList<SystemProcess> systemProcesses = new ArrayList<>();
    private Random random = new Random();
    private int maxTimeOfProcess = 50;
    private int circle = 1;

    private void createProcesses(int sizeOfProcess) {
        for (int i = 0; i < sizeOfProcess; i++) {
            SystemProcess systemProcess = new SystemProcess(systemProcesses.size());
            System.out.println(systemProcesses.size() + " процесс создан");
            systemProcesses.add(systemProcess);
        }
    }


    private void planning() {
        while (!systemProcesses.isEmpty()) {
            System.out.println(circle + " цикл запущен");
            for (int i = 0; i < systemProcesses.size(); i++) {
                SystemProcess systemProcess = systemProcesses.get(i);
                systemProcess.load(maxTimeOfProcess);
                if (systemProcess.getIsEmpty()) {
                    systemProcesses.remove(i);
                    i--;
                }
            }
            circle++;
        }
    }

    public void launch() {
        int sizeOfProcesses = 4 + random.nextInt(3);
        System.out.println(sizeOfProcesses + " процессов(а) требуется выполнить");
        createProcesses(sizeOfProcesses);
        planning();
    }

}
