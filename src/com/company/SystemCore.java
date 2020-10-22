package com.company;

import java.util.ArrayList;
import java.util.Random;

public class SystemCore {
    private ArrayList<SystemProcess> systemProcesses = new ArrayList<>();
    private Random random = new Random();
    private int circle = 1;
    private int[] priorityTime = {30, 40, 50};

    private void createProcesses(int sizeOfProcess) {
        for (int i = 0; i < sizeOfProcess; i++) {
            int priority = getRandomNumber();
            SystemProcess systemProcess = new SystemProcess(systemProcesses.size(), priority);
            System.out.println(systemProcesses.size() + " процесс создан. Его приоритет: " + priority);
            systemProcesses.add(systemProcess);
        }
    }


    private void planning() {
        while (!systemProcesses.isEmpty()) {
            System.out.println(circle + " цикл запущен");
            for (int i = 0; i < systemProcesses.size(); i++) {
                SystemProcess systemProcess = systemProcesses.get(i);
                while (systemProcess.getPriority() > 0) {
                    systemProcess.load(priorityTime[systemProcess.getPriority()]);
                    systemProcess.setPriority(systemProcess.getPriority() - 1);
                    if (systemProcess.getIsEmpty()) {
                        systemProcesses.remove(i);
                        i--;
                        break;
                    }
                }
                if (!systemProcess.getIsEmpty()) {
                    systemProcess.load(priorityTime[0]);
                    if (systemProcess.getIsEmpty()) {
                        systemProcesses.remove(i);
                        i--;
                    }
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


    private int getRandomNumber() {
        return random.nextInt(3);
    }

}
